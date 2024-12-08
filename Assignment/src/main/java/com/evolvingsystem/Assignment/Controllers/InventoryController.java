package com.evolvingsystem.Assignment.Controllers;

import com.evolvingsystem.Assignment.Entity.PhoneNumber;
import com.evolvingsystem.Assignment.Services.FileProcessingService;
import com.evolvingsystem.Assignment.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private FileProcessingService fileProcessingService;

    @Autowired
    private SearchService searchService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileProcessingService.processFile(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<PhoneNumber>> searchNumbers(
            @RequestParam(required = false) String areaCode,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String category) {
        List<PhoneNumber> results = searchService.search(areaCode, state, category);
        return ResponseEntity.ok(results);
    }
}

