package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EventAchievements")
public class EventAchievement {

    // Khóa chính, tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AchievementID", nullable = false, updatable = false)
    private Long achievementID;

    // Khóa ngoại tham chiếu Event
    @Column(name = "EventID")
    private Long eventID;

    // Khóa ngoại tham chiếu User
    @Column(name = "UserID")
    private Long userID;

    // Vị trí đạt được
    @Column(name = "Position")
    private Integer position;

    // Điểm số đạt được
    @Column(name = "Points")
    private Integer points;

    // Loại huy hiệu đạt được
    @Column(name = "BadgeType", length = 50)
    private String badgeType;

    // Ngày giờ được trao thưởng
    @Column(name = "AwardedAt", nullable = false)
    private LocalDateTime awardedAt;

    // Constructor không tham số
    public EventAchievement() {}

    // Constructor đầy đủ
    public EventAchievement(Long eventID, Long userID, Integer position, Integer points, String badgeType, LocalDateTime awardedAt) {
        this.eventID = eventID;
        this.userID = userID;
        this.position = position;
        this.points = points;
        this.badgeType = badgeType;
        this.awardedAt = awardedAt;
    }

    // Getter và Setter
    public Long getAchievementID() {
        return achievementID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    public LocalDateTime getAwardedAt() {
        return awardedAt;
    }

    public void setAwardedAt(LocalDateTime awardedAt) {
        this.awardedAt = awardedAt;
    }

    // toString để debug
    @Override
    public String toString() {
        return "Achievement{" +
                "achievementID=" + achievementID +
                ", eventID=" + eventID +
                ", userID=" + userID +
                ", position=" + position +
                ", points=" + points +
                ", badgeType='" + badgeType + '\'' +
                ", awardedAt=" + awardedAt +
                '}';
    }
}
