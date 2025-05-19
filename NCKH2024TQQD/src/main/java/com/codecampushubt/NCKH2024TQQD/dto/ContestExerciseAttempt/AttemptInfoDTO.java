package com.codecampushubt.NCKH2024TQQD.dto.ContestExerciseAttempt;



public class AttemptInfoDTO {
    private Long lessonID;
    private String exerciseType;
    private Integer attemptNumber;

    public AttemptInfoDTO() {
    }

    public AttemptInfoDTO(Long lessonID, String exerciseType, Integer attemptNumber) {
        this.lessonID = lessonID;
        this.exerciseType = exerciseType;
        this.attemptNumber = attemptNumber;
    }

    public Long getlessonID() {
        return lessonID;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    public void setLessonID(Long lessonID) {
        this.lessonID = lessonID;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public void setAttemptNumber(Integer attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    @Override
    public String toString() {
        return "AttemptInfoDTO{" +
                "lessonID=" + lessonID +
                ", exerciseType='" + exerciseType + '\'' +
                ", attemptNumber=" + attemptNumber +
                '}';
    }
}
