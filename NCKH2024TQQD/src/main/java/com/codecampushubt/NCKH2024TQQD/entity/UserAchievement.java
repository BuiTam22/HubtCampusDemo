package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserAchievements")
public class UserAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserAchievementID", nullable = false)
    private Long userAchievementID;

    @Column(name = "UserID")
    private Long userID;

    @Column(name = "AchievementID")
    private Integer achievementID;

    @Column(name = "EarnedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date earnedAt;

    @Column(name = "Progress")
    private Integer progress;

    // Constructor mặc định
    public UserAchievement() {
    }

    // Getters và Setters

    public Long getUserAchievementID() {
        return userAchievementID;
    }

    public void setUserAchievementID(Long userAchievementID) {
        this.userAchievementID = userAchievementID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(Integer achievementID) {
        this.achievementID = achievementID;
    }

    public Date getEarnedAt() {
        return earnedAt;
    }

    public void setEarnedAt(Date earnedAt) {
        this.earnedAt = earnedAt;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    // toString method để dễ dàng in ra thông tin đối tượng
    @Override
    public String toString() {
        return "UserAchievement{" +
                "userAchievementID=" + userAchievementID +
                ", userID=" + userID +
                ", achievementID=" + achievementID +
                ", earnedAt=" + earnedAt +
                ", progress=" + progress +
                '}';
    }
}
