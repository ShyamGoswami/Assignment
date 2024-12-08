package com.evolvingsystem.Assignment.Services;

import com.evolvingsystem.Assignment.Entity.PhoneNumber;
import com.evolvingsystem.Assignment.Entity.PhoneNumberCategory;
import com.evolvingsystem.Assignment.Entity.PhoneNumberStatus;
import com.evolvingsystem.Assignment.Entity.UploadProgress;
import com.evolvingsystem.Assignment.Repositories.PhoneNumberRepository;
import com.evolvingsystem.Assignment.Repositories.UploadProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessingService {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private UploadProgressRepository uploadProgressRepository;

    public void processFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        long lastProcessedLine = getLastProcessedLine(fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<PhoneNumber> batch = new ArrayList<>();
            int batchSize = 10000;
            long currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine <= lastProcessedLine) continue;

                String[] parts = line.split(",");
                if (isValidNumber(parts)) {
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setNumber(parts[0]);
                    phoneNumber.setStatus(PhoneNumberStatus.AVAILABLE);
                    phoneNumber.setAreaCode(parts[1]);
                    phoneNumber.setState(parts[2]);
                    phoneNumber.setCreationDate();
                    phoneNumber.setCategory(PhoneNumberCategory.valueOf(parts[3]));
                    batch.add(phoneNumber);

                    if (batch.size() == batchSize) {
                        saveBatch(batch, fileName, currentLine);
                        batch.clear();
                    }
                } else {
                    logValidationError(line);
                }
            }

            if (!batch.isEmpty()) saveBatch(batch, fileName, currentLine);
        }
    }

    private long getLastProcessedLine(String fileName) {
        return uploadProgressRepository.findByFileName(fileName)
                .map(UploadProgress::getLastProcessedLine)
                .orElse(0L);
    }

    private void saveBatch(List<PhoneNumber> batch, String fileName, long currentLine) {
        phoneNumberRepository.saveAll(batch);
        uploadProgressRepository.save(new UploadProgress(fileName, currentLine));
    }

    private boolean isValidNumber(String[] parts) {
        return parts.length == 4 && parts[0].matches("\\d{10}");
    }

    private void logValidationError(String record) {
        System.out.println("Invalid Record :: " + record);
    }
}
