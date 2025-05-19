package com.codecampushubt.NCKH2024TQQD.dto.LoginDTO;

public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
