package com.codecampushubt.NCKH2024TQQD.dto.UserDTO;

public class UserBasicInfoDTO {
    private String userName;
    private String fullName;
    private String image;

    public UserBasicInfoDTO(String userName, String fullName, String image){
        this.fullName = fullName;
        this.userName = userName;
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImage() {
        return image;
    }
}
