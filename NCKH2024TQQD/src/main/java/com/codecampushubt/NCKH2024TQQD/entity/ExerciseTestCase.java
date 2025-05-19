package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ExerciseTestCases")
public class ExerciseTestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TestCaseID", nullable = false, updatable = false)
    private Long testCaseID;

    @ManyToOne
    @JoinColumn(name = "ExerciseID")
    private CodingExercise codingExercise;

    @Column(name = "Input")
    private String input;

    @Column(name = "ExpectedOutput")
    private String expectedOutput;

    @Column(name = "IsPublic")
    private Boolean isPublic;

    @Column(name = "Score")
    private Integer score;

    public ExerciseTestCase() {
    }

    public ExerciseTestCase(CodingExercise codingExercise, String input, String expectedOutput, Boolean isPublic, Integer score) {
        this.codingExercise = codingExercise;
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.isPublic = isPublic;
        this.score = score;
    }

    public Long getTestCaseID() {
        return testCaseID;
    }

    public CodingExercise getCodingExercise() {
        return codingExercise;
    }

    public void setCodingExercise(CodingExercise codingExercise) {
        this.codingExercise = codingExercise;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public Boolean getIsPublic() { return isPublic; }

    public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ExcerciseTestCase{" +
                "testCaseID=" + testCaseID +
                ", codingExercise=" + codingExercise +
                ", input='" + input + '\'' +
                ", expectedOutput='" + expectedOutput + '\'' +
                ", isPublic=" + isPublic +
                ", score=" + score +
                '}';
    }
}