package com.codecampushubt.NCKH2024TQQD.dto.EssayExerciseDTO;

public class EssayExerciseDetailShowDTO {
    private Long exerciseID;
    private String title;
    private String description;
    private Integer timeLimit;
    private String slug;

    public EssayExerciseDetailShowDTO(Long exerciseID, String title, String description, Integer timeLimit, String slug) {
        this.exerciseID = exerciseID;
        this.title = title;
        this.description = description;
        this.timeLimit = timeLimit;
        this.slug = slug;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public String getSlug() {
        return slug;
    }
}
