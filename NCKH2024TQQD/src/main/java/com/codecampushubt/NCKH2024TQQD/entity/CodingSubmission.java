package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CodingSubmissions")
public class CodingSubmission {

    // ID TỰ ĐỘNG TĂNG CỦA BÀI NỘP
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubmissionID", nullable = false, updatable = false)
    private Long submissionID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG CODINGEXERCISES
    @ManyToOne
    @JoinColumn(name = "ExerciseID", nullable = false)
    private CodingExercise exercise;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG USERS
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    // CODE ĐÃ NỘP
    @Column(name = "Code", columnDefinition = "NVARCHAR(MAX)")
    private String code;

    // NGÔN NGỮ LẬP TRÌNH SỬ DỤNG
    @Column(name = "Language", length = 50)
    private String language;

    // TRẠNG THÁI CHẤM BÀI (CHỈ NHẬN GIÁ TRỊ HỢP LỆ)
    @Column(name = "Status", length = 20)
    private String status;

    // THỜI GIAN CHẠY (MILI GIÂY)
    @Column(name = "ExecutionTime")
    private Integer executionTime;

    // BỘ NHỚ SỬ DỤNG (KB)
    @Column(name = "MemoryUsed")
    private Integer memoryUsed;

    // SỐ TEST CASE ĐÃ PASS
    @Column(name = "TestCasesPassed", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer testCasesPassed;

    // TỔNG SỐ TEST CASE
    @Column(name = "TotalTestCases", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer totalTestCases;

    // ĐIỂM ĐẠT ĐƯỢC
    @Column(name = "Score", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer score;

    // THỜI ĐIỂM NỘP BÀI
    @Column(name = "SubmittedAt", nullable = true, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime submittedAt;


    // Constructor
    public CodingSubmission() {
    }

    public CodingSubmission(Long submissionID, CodingExercise exercise, User user, String code, String language, String status, Integer executionTime, Integer memoryUsed, Integer testCasesPassed, Integer totalTestCases, Integer score, LocalDateTime submittedAt) {
        this.submissionID = submissionID;
        this.exercise = exercise;
        this.user = user;
        this.code = code;
        this.language = language;
        this.status = status;
        this.executionTime = executionTime;
        this.memoryUsed = memoryUsed;
        this.testCasesPassed = testCasesPassed;
        this.totalTestCases = totalTestCases;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public Long getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(Long submissionID) {
        this.submissionID = submissionID;
    }

    public CodingExercise getExercise() {
        return exercise;
    }

    public void setExercise(CodingExercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    public Integer getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Integer memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Integer getTestCasesPassed() {
        return testCasesPassed;
    }

    public void setTestCasesPassed(Integer testCasesPassed) {
        this.testCasesPassed = testCasesPassed;
    }

    public Integer getTotalTestCases() {
        return totalTestCases;
    }

    public void setTotalTestCases(Integer totalTestCases) {
        this.totalTestCases = totalTestCases;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Override
    public String toString() {
        return "CodingSubmission{" +
                "submissionID=" + submissionID +
                ", exercise=" + exercise +
                ", user=" + user +
                ", code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", status='" + status + '\'' +
                ", executionTime=" + executionTime +
                ", memoryUsed=" + memoryUsed +
                ", testCasesPassed=" + testCasesPassed +
                ", totalTestCases=" + totalTestCases +
                ", score=" + score +
                ", submittedAt=" + submittedAt +
                '}';
    }
}
