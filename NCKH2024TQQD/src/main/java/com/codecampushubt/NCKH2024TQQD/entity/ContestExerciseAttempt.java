package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "ContestExerciseAttempts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ExerciseID", "UserID", "ExerciseType"})
)
public class ContestExerciseAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttemptID")
    private Long attemptID;

    @ManyToOne
    @JoinColumn(name = "ExerciseID", nullable = false)
    private CodingExercise exercise;

    @ManyToOne
    @JoinColumn(name = "LessonID", nullable = false)
    private CourseLesson lesson;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "SubmittedAt", nullable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(name = "Score")
    private Integer score;

    @Column(name = "ExerciseType")
    private String exerciseType;

    @Column(name = "AttemptNumber")
    private Integer attemptNumber;



    public ContestExerciseAttempt() {
    }

    public ContestExerciseAttempt(CodingExercise exercise, CourseLesson lesson, User user, LocalDateTime submittedAt, Integer score, String exerciseType, Integer attemptNumber) {
        this.exercise = exercise;
        this.lesson = lesson;
        this.user = user;
        this.submittedAt = submittedAt;
        this.score = score;
        this.exerciseType = exerciseType;
        this.attemptNumber = attemptNumber;
    }


    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(Integer attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public Long getAttemptID() {
        return attemptID;
    }

    public CodingExercise getExercise() {
        return exercise;
    }

    public void setExercise(CodingExercise exercise) {
        this.exercise = exercise;
    }

    public CourseLesson getLesson() {
        return lesson;
    }

    public void setLesson(CourseLesson lesson) {
        this.lesson = lesson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
