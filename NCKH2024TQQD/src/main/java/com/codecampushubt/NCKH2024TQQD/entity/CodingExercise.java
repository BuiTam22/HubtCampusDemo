package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "CodingExercises")
public class CodingExercise {

    // ID TỰ ĐỘNG TĂNG CỦA BÀI TẬP
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExerciseID", nullable = false, updatable = false)
    private Long exerciseID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG COURSELESSONS
    @ManyToOne
    @JoinColumn(name = "LessonID", nullable = false)
    private CourseLesson lesson;

    //Khóa link sang list<ExerciseTestcases>
    @OneToMany(mappedBy = "codingExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExerciseTestCase> exerciseTestCases;

    // TIÊU ĐỀ BÀI TẬP
    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    // MÔ TẢ CHI TIẾT BÀI TẬP
    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    // NGÔN NGỮ LẬP TRÌNH ĐƯỢC SỬ DỤNG
    @Column(name = "ProgrammingLanguage", length = 50)
    private String programmingLanguage;

    // CODE MẪU BAN ĐẦU
    @Column(name = "InitialCode", columnDefinition = "NVARCHAR(MAX)")
    private String initialCode;

    // CODE LỜI GIẢI
    @Column(name = "SolutionCode", columnDefinition = "NVARCHAR(MAX)")
    private String solutionCode;

    // GIỚI HẠN THỜI GIAN CHẠY (MILI GIÂY)
    @Column(name = "TimeLimit", nullable = false, columnDefinition = "INT DEFAULT 1000")
    private Integer timeLimit;

    // GIỚI HẠN BỘ NHỚ SỬ DỤNG (MB)
    @Column(name = "MemoryLimit", nullable = false, columnDefinition = "INT DEFAULT 256")
    private Integer memoryLimit;

    @Column(name = "Difficulty", nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'medium'")
    private String difficulty;

    // ĐIỂM CHO BÀI TẬP
    @Column(name = "Points", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer points;

    // THỜI ĐIỂM TẠO
    @Column(name = "CreatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime createdAt;

    // THỜI ĐIỂM CẬP NHẬT
    @Column(name = "UpdatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime updatedAt;

    @Column(name = "Slug", nullable = true)
    private String slug;

    // ĐỊNH DẠNG ĐẦU VÀO
    @Column(name = "InputFormat", columnDefinition = "NVARCHAR(MAX)")
    private String inputFormat;

    // ĐỊNH DẠNG ĐẦU RA
    @Column(name = "OutputFormat", columnDefinition = "NVARCHAR(MAX)")
    private String outputFormat;

    // TÊN RÀNG BUỘC
    @Column(name = "ConstraintName", length = 50)
    private String constraintName;


    // Constructor
    public CodingExercise() {
    }

    public CodingExercise(CourseLesson lesson, String title, String description, String programmingLanguage, String initialCode, String solutionCode, Integer timeLimit, Integer memoryLimit, String difficulty, Integer points, LocalDateTime createdAt, LocalDateTime updatedAt, String slug, String inputFormat, String outputFormat, String constraintName) {
        this.lesson = lesson;
        this.title = title;
        this.description = description;
        this.programmingLanguage = programmingLanguage;
        this.initialCode = initialCode;
        this.solutionCode = solutionCode;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.difficulty = difficulty;
        this.points = points;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.slug = slug;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.constraintName = constraintName;
    }

    public Set<ExerciseTestCase> getExerciseTestCases() {
        return exerciseTestCases;
    }

    public void setExerciseTestCases(Set<ExerciseTestCase> exerciseTestCases) {
        this.exerciseTestCases = exerciseTestCases;
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public CourseLesson getLesson() {
        return lesson;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getInitialCode() {
        return initialCode;
    }

    public void setInitialCode(String initialCode) {
        this.initialCode = initialCode;
    }

    public String getSolutionCode() {
        return solutionCode;
    }

    public void setSolutionCode(String solutionCode) {
        this.solutionCode = solutionCode;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setLesson(CourseLesson lesson) {
        this.lesson = lesson;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    @Override
    public String toString() {
        return "CodingExercise{" +
                "exerciseID=" + exerciseID +
                ", lesson=" + lesson +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", initialCode='" + initialCode + '\'' +
                ", solutionCode='" + solutionCode + '\'' +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", difficulty='" + difficulty + '\'' +
                ", points=" + points +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
