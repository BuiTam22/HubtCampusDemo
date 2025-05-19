package com.codecampushubt.NCKH2024TQQD.dto.ExerciseTestCasesDTO;

public class    ExerciseTestCasesDTO {
    private String input;
    private String expectedOutput;
    private Boolean isPublic;
    private Integer score;

    public ExerciseTestCasesDTO(String input, String expectedOutput, Boolean isPublic, Integer score) {
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.isPublic = isPublic;
        this.score = score;
    }

    public String getInput() {
        return input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public Integer getScore() {
        return score;
    }
}
