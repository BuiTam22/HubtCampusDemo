package com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO;

public class JudgeRequestDTO {
    private String sourceCode;
    private String language;
    private Long exerciseID;

    public JudgeRequestDTO() {
    }

    public JudgeRequestDTO(String sourceCode, String language, Long exerciseID) {
        this.sourceCode = sourceCode;
        this.language = language;
        this.exerciseID = exerciseID;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }
}
