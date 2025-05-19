package com.codecampushubt.NCKH2024TQQD.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;

public class UserUpdateDTO {
    private Long UserId;
    private String userName;
    private String email;
    private String password;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private String image;       // trùng tên với entity
    private List<String> roleName;
    public UserUpdateDTO() {}

    public UserUpdateDTO(Long userId, String userName, String email, String password, String fullName, LocalDate dateOfBirth, String phoneNumber, String address, String image, List<String> roleName) {
        UserId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.image = image;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getRoleName() {
        return roleName;
    }

    public void setRoleName(List<String> roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
                "UserID=" + UserId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", roleName=" + roleName +
                '}';
    }
}


