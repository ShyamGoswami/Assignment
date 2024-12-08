package com.evolvingsystem.Assignment.Repositories;

import com.evolvingsystem.Assignment.Entity.UploadProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UploadProgressRepository extends JpaRepository<UploadProgress, Long> {
    Optional<UploadProgress> findByFileName(String fileName);
}

