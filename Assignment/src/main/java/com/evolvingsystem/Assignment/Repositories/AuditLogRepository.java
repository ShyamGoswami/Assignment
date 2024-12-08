package com.evolvingsystem.Assignment.Repositories;

import com.evolvingsystem.Assignment.Entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByPhoneNumber(String phoneNumber);
}

