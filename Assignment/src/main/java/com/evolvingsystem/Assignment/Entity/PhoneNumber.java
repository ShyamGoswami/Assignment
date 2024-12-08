package com.evolvingsystem.Assignment.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "phone_number")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneNumberStatus status;

    private String areaCode;
    private String state;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private PhoneNumberCategory category;

    @Version
    private Integer version;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneNumberStatus getStatus() {
        return status;
    }

    public void setStatus(PhoneNumberStatus status) {
        this.status = status;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public PhoneNumberCategory getCategory() {
        return category;
    }

    public void setCategory(PhoneNumberCategory category) {
        this.category = category;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


}
