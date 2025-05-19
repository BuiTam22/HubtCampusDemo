package com.codecampushubt.NCKH2024TQQD.dto.LoginDTO;

public class LoginRequestDTO {
    private String username;
    private String password;

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
