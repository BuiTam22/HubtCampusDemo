package com.codecampushubt.NCKH2024TQQD.dto.UserDTO;

import java.util.List;

public class UserShowDTO {
    private Long UserID ;
    private String UserName ;
    private String Email ;
    private List<String> UserRole ;
    public UserShowDTO() {}
    public UserShowDTO(Long userID, String userName, String email, List<String> userRole) {
        UserID = userID;
        UserName = userName;
        Email = email;
        UserRole = userRole;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public List<String> getUserRole() {
        return UserRole;
    }

    public void setUserRole(List<String> userRole) {
        UserRole = userRole;
    }

    @Override
    public String toString() {
        return "UserShowDTO{" +
                "UserID=" + UserID +
                ", UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                ", UserRole=" + UserRole +
                '}';
    }
}
