package com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO;

public class EssayExerciseSubmissionRequest {
    private Long exerciseID;
    private String content;

    public EssayExerciseSubmissionRequest() {
    }

    public EssayExerciseSubmissionRequest(Long exerciseID, String content) {
        this.exerciseID = exerciseID;
        this.content = content;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
