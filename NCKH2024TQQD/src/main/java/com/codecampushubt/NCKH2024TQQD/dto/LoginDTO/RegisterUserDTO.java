package com.codecampushubt.NCKH2024TQQD.dto.LoginDTO;

public class RegisterUserDTO {
    private String email;
    private String fullName;
    private String userName;
    private String password;
    private String roleName;
    public RegisterUserDTO() {}

    public RegisterUserDTO(String email, String fullName, String userName, String password, String roleName) {
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RegisterUserDTO{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
