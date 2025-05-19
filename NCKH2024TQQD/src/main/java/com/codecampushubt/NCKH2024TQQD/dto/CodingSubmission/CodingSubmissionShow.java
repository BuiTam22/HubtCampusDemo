package com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission;

import java.time.LocalDateTime;

public class CodingSubmissionShow {
    private String exerciseName;
    private String userName;
    private String code;
    private String language;
    private String status;
    private Integer testCasesPassed;
    private Integer totalTestCases;
    private Integer score;
    private LocalDateTime submittedAt;

    public CodingSubmissionShow(String exerciseName, String userName, String code, String language, String status, Integer testCasesPassed, Integer totalTestCases, Integer score, LocalDateTime submittedAt) {
        this.exerciseName = exerciseName;
        this.userName = userName;
        this.code = code;
        this.language = language;
        this.status = status;
        this.testCasesPassed = testCasesPassed;
        this.totalTestCases = totalTestCases;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
