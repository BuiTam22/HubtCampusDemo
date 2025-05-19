package com.codecampushubt.NCKH2024TQQD.dto.UserDTO;

import org.springframework.context.annotation.Primary;

public class veriOtp {
    private String email;
    private String otp;

    public veriOtp(){}

    public veriOtp(String email, String otp) {
        this.email = email;
        this.otp = otp;
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

    @Override
    public String toString() {
        return "veriOtp{" +
                "email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
