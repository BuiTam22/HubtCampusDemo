package com.codecampushubt.NCKH2024TQQD.dto.CodingExerciseDTO;

import com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO.ExerciseTestCasesDTO;
import com.codecampushubt.NCKH2024TQQD.entity.ExerciseTestCase;

import java.util.Set;

public class CodingExerciseDetailDTO {
    private Long exerciseID;
    private Set<ExerciseTestCasesDTO> exerciseTestCases;
    private String title;
    private String description;
    private String programmingLanguage;
    private String initialCode;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String difficulty;
    private Integer points;
    private String slug;
    private String inputFormat;
    private String outputFormat;
    private String constraintName;

    public CodingExerciseDetailDTO(Long exerciseID, Set<ExerciseTestCasesDTO> exerciseTestCases, String title, String description, String programmingLanguage, String initialCode, Integer timeLimit, Integer memoryLimit, String difficulty, Integer points, String slug, String inputFormat, String outputFormat, String constraintName) {
        this.exerciseID = exerciseID;
        this.exerciseTestCases = exerciseTestCases;
        this.title = title;
        this.description = description;
        this.programmingLanguage = programmingLanguage;
        this.initialCode = initialCode;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.difficulty = difficulty;
        this.points = points;
        this.slug = slug;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.constraintName = constraintName;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public Set<ExerciseTestCasesDTO> getExerciseTestCases() {
        return exerciseTestCases;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getInitialCode() {
        return initialCode;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public String getSlug() {
        return slug;
    }

    public void setExerciseTestCases(Set<ExerciseTestCasesDTO> exerciseTestCases) {
        this.exerciseTestCases = exerciseTestCases;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public String getConstraintName() {
        return constraintName;
    }
}
