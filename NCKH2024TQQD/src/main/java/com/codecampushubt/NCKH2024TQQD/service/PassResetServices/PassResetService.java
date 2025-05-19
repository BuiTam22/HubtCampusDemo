package com.codecampushubt.NCKH2024TQQD.service.PassResetServices;


public interface PassResetService {
    void sendOtpToEmail(String email);
    String resetPassword(String email, String otp, String newPassword);
    String verifyOtp(String email, String otp);

}
