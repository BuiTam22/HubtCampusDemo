package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EssaySubmissions")
public class EssaySubmission {
    // ID TỰ ĐỘNG TĂNG CỦA BÀI NỘP
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubmissionID", nullable = false, updatable = false)
    private Long submissionID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG CODINGEXERCISES
    @ManyToOne
    @JoinColumn(name = "ExerciseID", nullable = false)
    private EssayExercise exercise;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG USERS
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    // ĐÁP ÁN CỦA HỌC SINH
    @Column(name = "AnswerText", nullable = true)
    private String answerText;

    // THỜI ĐIỂM NỘP BÀI
    @Column(name = "SubmittedAt", nullable = true, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime submittedAt;

    // ĐIỂM CỦA BÀI ĐÓ
    @Column(name = "Score", nullable = true)
    private Double score = 0.0;

    // FEEDBACK CỦA GIẢNG VIÊN
    @Column(name = "Feedback", nullable = true)
    private String feedback;

    public EssaySubmission() {
    }

    public EssaySubmission(EssayExercise exercise, User user, String answerText, LocalDateTime submittedAt, Double score, String feedback) {
        this.exercise = exercise;
        this.user = user;
        this.answerText = answerText;
        this.submittedAt = submittedAt;
        this.score = score;
        this.feedback = feedback;
    }

    public Long getSubmissionID() {
        return submissionID;
    }

    public EssayExercise getExercise() {
        return exercise;
    }

    public void setExercise(EssayExercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "EssaySubmission{" +
                "submissionID=" + submissionID +
                ", exercise=" + exercise +
                ", user=" + user +
                ", answerText='" + answerText + '\'' +
                ", submittedAt=" + submittedAt +
                ", score=" + score +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
