package com.codecampushubt.NCKH2024TQQD.entity;

import com.codecampushubt.NCKH2024TQQD._enum.Admin.User.AccountStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userID;

    @Column(name = "UserName", nullable = false, unique = true, length = 50)
    private String userName;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Column(name = "FullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "School", length = 255)
    private String school;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(name = "AccountStatus", nullable = false, length = 20)
    private AccountStatus accountStatus;

    @Column(name = "Image", length = 255)
    private String image;

    @Column(name = "Bio", length = 500)
    private String bio;

    @Column(name = "Provider", nullable = false, length = 20)
    private String provider;

    @Column(name = "ProviderID", length = 100)
    private String providerID;

    @Column(name = "EmailVerified", nullable = false)
    private Boolean emailVerified; // Hỗ trợ null

    @Column(name = "PhoneNumber", length = 15)
    private String phoneNumber;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "City", length = 100)
    private String city;

    @Column(name = "Country", length = 100)
    private String country;

    @Column(name = "LastLoginIP", length = 45)
    private String lastLoginIP;

    @Column(name = "CreatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime updatedAt;

    @Column(name = "LastLoginAt")
    private LocalDateTime lastLoginAt;

    @Column(name = "DeletedAt")
    private LocalDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();  // Gán thời gian hiện tại khi chưa có giá trị
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();  // Gán thời gian hiện tại khi chưa có giá trị
        }
    }

    // Constructor
    public User() {
    }

    public User(String userName, String email, String password, String fullName, LocalDate dateOfBirth, String school, String status, AccountStatus accountStatus, String image, String bio, String provider, String providerID, Boolean emailVerified, String phoneNumber, String address, String city, String country, String lastLoginIP, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLoginAt, LocalDateTime deletedAt) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.school = school;
//        this.role = role;
        this.status = status;
        this.accountStatus = accountStatus;
        this.image = image;
        this.bio = bio;
        this.provider = provider;
        this.providerID = providerID;
        this.emailVerified = emailVerified;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.lastLoginIP = lastLoginIP;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLoginAt = lastLoginAt;
        this.deletedAt = deletedAt;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
    // Getters & Setters...


    public Long getUserId() {
        return userID;
    }

    public String getuserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSchool() {
        return school;
    }

//    public String getRole() {
//        return role;
//    }

    public String getStatus() {
        return status;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public String getImage() {
        return image;
    }

    public String getBio() {
        return bio;
    }

    public String getProvider() {
        return provider;
    }

    public String getProviderID() {
        return providerID;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSchool(String school) {
        this.school = school;
    }

//    public void setRole(String role) {
//        this.role = role;
//    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", school='" + school + '\'' +
//                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", image='" + image + '\'' +
                ", bio='" + bio + '\'' +
                ", provider='" + provider + '\'' +
                ", providerID='" + providerID + '\'' +
                ", emailVerified=" + emailVerified +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", lastLoginIP='" + lastLoginIP + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", lastLoginAt=" + lastLoginAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}