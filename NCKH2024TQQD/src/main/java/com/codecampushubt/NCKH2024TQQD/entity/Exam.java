package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examID;
    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course courseID;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    @Column(name = "Duration", nullable = false)
    private int duration;

    @Column(name = "TotalPoints", columnDefinition = "INT DEFAULT 100")
    private int totalPoints = 100;

    @Column(name = "PassingScore", columnDefinition = "INT DEFAULT 60")
    private int passingScore = 60;

    @Column(name = "StartTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "EndTime", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "Instructions")
    private String instructions;

    @Column(name = "AllowReview", columnDefinition = "BIT DEFAULT 1")
    private boolean allowReview = true;

    @Column(name = "ShuffleQuestions", columnDefinition = "BIT DEFAULT 1")
    private boolean shuffleQuestions = true;

    @Column(name = "Status", length = 20, nullable = false)
    private String status = "upcoming";


    @ManyToOne
    @JoinColumn(name = "CreatedBy", nullable = false) // Liên kết đến UserID trong bảng Users
    private User createdBy;

    @Column(name = "CreatedAt", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
// Constructor

    public Exam() {
    }

    public Exam(Long examID, Course courseID, String title, String description, String type, int duration, int totalPoints, int passingScore, LocalDateTime startTime, LocalDateTime endTime, String instructions, boolean allowReview, boolean shuffleQuestions, String status, User createdBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.examID = examID;
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.type = type;
        this.duration = duration;
        this.totalPoints = totalPoints;
        this.passingScore = passingScore;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructions = instructions;
        this.allowReview = allowReview;
        this.shuffleQuestions = shuffleQuestions;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getExamID() {
        return examID;
    }

    public void setExamID(Long examID) {
        this.examID = examID;
    }

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean isAllowReview() {
        return allowReview;
    }

    public void setAllowReview(boolean allowReview) {
        this.allowReview = allowReview;
    }

    public boolean isShuffleQuestions() {
        return shuffleQuestions;
    }

    public void setShuffleQuestions(boolean shuffleQuestions) {
        this.shuffleQuestions = shuffleQuestions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

