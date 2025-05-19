package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ExamParticipants")
public class ExamParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ParticipantID", nullable = false, unique = true)
    private Long participantID;

    @ManyToOne
    @JoinColumn(name = "ExamID", nullable = false)
    private Exam examID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    @Column(name = "StartedAt")
    private Timestamp startedAt;

    @Column(name = "CompletedAt")
    private Timestamp completedAt;

    @Column(name = "TimeSpent")
    private Integer timeSpent;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "Status", nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'registered'")
    private String status;

    @Column(name = "Feedback", columnDefinition = "NVARCHAR(MAX)")
    private String feedback;
    @ManyToOne
    @JoinColumn(name = "ReviewedBy")
    private User reviewedBy;

    @Column(name = "ReviewedAt")
    private Timestamp reviewedAt;

    // Constructor

    public ExamParticipant() {
    }

    public ExamParticipant(Exam examID, User userID, Timestamp startedAt, Timestamp completedAt, Integer timeSpent, Integer score, String status, String feedback, User reviewedBy, Timestamp reviewedAt) {
        this.examID = examID;
        this.userID = userID;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.timeSpent = timeSpent;
        this.score = score;
        this.status = status;
        this.feedback = feedback;
        this.reviewedBy = reviewedBy;
        this.reviewedAt = reviewedAt;
    }

    // getter setter

    public Long getParticipantID() {
        return participantID;
    }

    public Exam getExamID() {
        return examID;
    }

    public void setExamID(Exam examID) {
        this.examID = examID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Timestamp getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Timestamp completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(User reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public Timestamp getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(Timestamp reviewedAt) {
        this.reviewedAt = reviewedAt;
    }
}
