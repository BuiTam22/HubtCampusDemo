package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserProfiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileID;

//    @OneToOne
//    @JoinColumn(name = "userID", unique = true, nullable = false)
//    private User user;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String education;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String workExperience;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String skills;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String interests;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String socialLinks; // JSON

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String achievements; // JSON

    @Column(length = 10, nullable = false)
    private String preferredLanguage = "vi";

    @Column(length = 50, nullable = false)
    private String timeZone = "Asia/Ho_Chi_Minh";

    private String notificationPreferences; // JSON

    private LocalDateTime updatedAt = LocalDateTime.now();
    public UserProfile() {}
    public UserProfile(Long profileID, String education, String workExperience, String skills, String interests, String socialLinks, String achievements, String preferredLanguage, String timeZone, String notificationPreferences, LocalDateTime updatedAt) {
        this.profileID = profileID;
        this.education = education;
        this.workExperience = workExperience;
        this.skills = skills;
        this.interests = interests;
        this.socialLinks = socialLinks;
        this.achievements = achievements;
        this.preferredLanguage = preferredLanguage;
        this.timeZone = timeZone;
        this.notificationPreferences = notificationPreferences;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNotificationPreferences() {
        return notificationPreferences;
    }

    public void setNotificationPreferences(String notificationPreferences) {
        this.notificationPreferences = notificationPreferences;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Long getProfileID() {
        return profileID;
    }

    public void setProfileID(Long profileID) {
        this.profileID = profileID;
    }
}

