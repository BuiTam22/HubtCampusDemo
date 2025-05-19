package com.codecampushubt.NCKH2024TQQD.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "CourseAchievements")
public class CourseAchievement {

    // ID TỰ ĐỘNG TĂNG CỦA THÀNH TÍCH
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AchievementID", nullable = false, updatable = false)
    private Long achievementID;

    // Khóa ngoại tham chiếu đến bảng Courses (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

    // Khóa ngoại tham chiếu đến bảng Users (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

//    @Column(name = "UserID", nullable = false)
//    public Long userID;

    // THỜI GIAN HOÀN THÀNH KHÓA HỌC (PHÚT)
    @Column(name = "CompletionTime")
    public Integer completionTime;

    // SỐ CÂU TRẢ LỜI ĐÚNG
    @Column(name = "CorrectAnswers")
    public Integer correctAnswers;

    // TỔNG SỐ CÂU HỎI
    @Column(name = "TotalQuestions")
    public Integer totalQuestions;

    // ĐIỂM SỐ ĐẠT ĐƯỢC
    @Column(name = "Score", precision = 5, scale = 2)
    public BigDecimal score;

    // LOẠI HUY HIỆU ĐẠT ĐƯỢC (KHÔNG DÙNG ENUM)
    @Column(name = "BadgeType", length = 50)
    public String badgeType;

    // THỜI ĐIỂM ĐẠT THÀNH TÍCH
    @Column(name = "AwardedAt", nullable = false)
    public LocalDateTime awardedAt;

    public CourseAchievement() {
    }

    public CourseAchievement(Long achievementID, Course course, User user, Integer completionTime, Integer correctAnswers, Integer totalQuestions, BigDecimal score, String badgeType, LocalDateTime awardedAt) {
        this.achievementID = achievementID;
        this.course = course;
        this.user = user;
        this.completionTime = completionTime;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.score = score;
        this.badgeType = badgeType;
        this.awardedAt = awardedAt;
    }

    public Long getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(Long achievementID) {
        this.achievementID = achievementID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Integer completionTime) {
        this.completionTime = completionTime;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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
}
