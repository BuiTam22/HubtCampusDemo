package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LessonProgress")
public class LessonProgress {

    // ID TỰ ĐỘNG TĂNG CỦA TIẾN ĐỘ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgressID", nullable = false, updatable = false)
    private Long progressID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG COURSEENROLLMENTS
    @ManyToOne
    @JoinColumn(name = "EnrollmentID", nullable = false)
    private CourseEnrollment enrollmentID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG COURSELESSONS
    @ManyToOne
    @JoinColumn(name = "LessonID", nullable = false)
    private CourseLesson lessonID;

    // TRẠNG THÁI HỌC TẬP (KHÔNG DÙNG ENUM)
    @Column(name = "Status", length = 20)
    private String status;

    // THỜI ĐIỂM HOÀN THÀNH
    @Column(name = "CompletedAt")
    private LocalDateTime completedAt;

    // THỜI GIAN ĐÃ HỌC (GIÂY)
    @Column(name = "TimeSpent", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer timeSpent;

    // VỊ TRÍ XEM VIDEO GẦN NHẤT
    @Column(name = "LastPosition", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer lastPosition;

    // Constructor
    public LessonProgress() {
    }

    public LessonProgress(Long progressID, CourseEnrollment enrollmentID, CourseLesson lessonID, String status, LocalDateTime completedAt, Integer timeSpent, Integer lastPosition) {
        this.progressID = progressID;
        this.enrollmentID = enrollmentID;
        this.lessonID = lessonID;
        this.status = status;
        this.completedAt = completedAt;
        this.timeSpent = timeSpent;
        this.lastPosition = lastPosition;
    }

    public Long getProgressID() {
        return progressID;
    }

    public void setProgressID(Long progressID) {
        this.progressID = progressID;
    }

    public CourseEnrollment getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(CourseEnrollment enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public CourseLesson getLessonID() {
        return lessonID;
    }

    public void setLessonID(CourseLesson lessonID) {
        this.lessonID = lessonID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Integer getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Integer lastPosition) {
        this.lastPosition = lastPosition;
    }
}
