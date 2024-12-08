package com.evolvingsystem.Assignment.Services;

import com.evolvingsystem.Assignment.Entity.AuditLog;
import com.evolvingsystem.Assignment.Entity.PhoneNumber;
import com.evolvingsystem.Assignment.Entity.PhoneNumberStatus;
import com.evolvingsystem.Assignment.Repositories.AuditLogRepository;
import com.evolvingsystem.Assignment.Repositories.PhoneNumberRepository;
import com.evolvingsystem.Assignment.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReservationService {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Transactional
    public void reserveNumber(String number, String userId, String ipAddress) {
        PhoneNumber phoneNumber = phoneNumberRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNotFoundException("Number not found"));

        if (!phoneNumber.getStatus().equals(PhoneNumberStatus.AVAILABLE)) {
            throw new IllegalStateException("Number is not available for reservation");
        }

        phoneNumber.setStatus(PhoneNumberStatus.RESERVED);
        phoneNumberRepository.save(phoneNumber);

        logStateTransition(phoneNumber, PhoneNumberStatus.AVAILABLE.name(), PhoneNumberStatus.RESERVED.name(), userId, ipAddress, "Reserved the number");
    }

    private void logStateTransition(PhoneNumber phoneNumber, String fromState, String toState, String userId, String ipAddress, String description) {
        AuditLog log = new AuditLog();
        log.setPhoneNumber(phoneNumber.getNumber());
        log.setPreviousState(fromState);
        log.setCurrentState(toState);
        log.setUserId(userId);
        log.setIpAddress(ipAddress);
        log.setDescription(description);
        auditLogRepository.save(log);
    }
}

