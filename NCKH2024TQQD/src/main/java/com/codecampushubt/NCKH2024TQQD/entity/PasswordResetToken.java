package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String otp;
    private LocalDateTime expiryTime;
    public PasswordResetToken() {}

    public PasswordResetToken(Long id, String email, String otp, LocalDateTime expiryTime) {
        this.id = id;
        this.email = email;
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
