package com.codecampushubt.NCKH2024TQQD.dto.CodingSubmission;

public class CodingSubmissionResponseDTO {
    private Long exerciseID;
    private Long userID;
    private String code;
    private String language;
    private String status;
    private Integer testCasesPassed;
    private Integer totalTestCases;
    private Integer score;

    public CodingSubmissionResponseDTO() {
    }

    public CodingSubmissionResponseDTO(Long exerciseID, Long userID, String code, String language, String status, Integer testCasesPassed, Integer totalTestCases, Integer score) {
        this.exerciseID = exerciseID;
        this.userID = userID;
        this.code = code;
        this.language = language;
        this.status = status;
        this.testCasesPassed = testCasesPassed;
        this.totalTestCases = totalTestCases;
        this.score = score;
    }


    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    @Override
    public String toString() {
        return "CodingSubmissionResponseDTO{" +
                "exerciseID=" + exerciseID +
                ", userID=" + userID +
                ", code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", status='" + status + '\'' +
                ", testCasesPassed=" + testCasesPassed +
                ", totalTestCases=" + totalTestCases +
                ", score=" + score +
                '}';
    }
}
