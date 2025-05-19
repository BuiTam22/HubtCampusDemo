package com.codecampushubt.NCKH2024TQQD.dto.LoginDTO;

public class LoginBasicDTO {
    private Long userId;
    private String userName;
    private String email;
    private String password;

    public LoginBasicDTO(String userName, String email, String password, Long userId) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userId =  userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginBasicDTO(Long userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginBasicDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
