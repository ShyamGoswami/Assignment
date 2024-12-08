package com.evolvingsystem.Assignment.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "upload_progress")
public class UploadProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fileName;

    private long lastProcessedLine;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime uploadTime;

    public UploadProgress() {
    }

    public UploadProgress(String fileName, long lastProcessedLine) {
        this.fileName = fileName;
        this.lastProcessedLine = lastProcessedLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getLastProcessedLine() {
        return lastProcessedLine;
    }

    public void setLastProcessedLine(long lastProcessedLine) {
        this.lastProcessedLine = lastProcessedLine;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}