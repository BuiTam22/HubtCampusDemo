package com.codecampushubt.NCKH2024TQQD.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CourseEnrollments")
public class CourseEnrollment {

    // ID TỰ ĐỘNG TĂNG CỦA ĐĂNG KÝ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentID", nullable = false, updatable = false)
    private Long enrollmentID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG COURSES
    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course courseID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG USERS
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User userID;

    // TIẾN ĐỘ HỌC TẬP (%)
    @Column(name = "Progress", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer progress;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG COURSELESSONS (BÀI HỌC TRUY CẬP GẦN NHẤT)
    @ManyToOne
    @JoinColumn(name = "LastAccessedLessonID")
    private CourseLesson lastAccessedLessonID;

    // THỜI ĐIỂM ĐĂNG KÝ
    @Column(name = "EnrolledAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime enrolledAt;

    // THỜI ĐIỂM HOÀN THÀNH
    @Column(name = "CompletedAt")
    private LocalDateTime completedAt;

    // ĐÃ CẤP CHỨNG CHỈ CHƯA (TRUE/FALSE)
    @Column(name = "CertificateIssued", nullable = false, columnDefinition = "BIT DEFAULT 0")
    private Boolean certificateIssued;

    // Constructor
    public CourseEnrollment() {
    }

    public CourseEnrollment(Long enrollmentID, Course courseID, User userID, Integer progress, CourseLesson lastAccessedLessonID, LocalDateTime enrolledAt, LocalDateTime completedAt, Boolean certificateIssued) {
        this.enrollmentID = enrollmentID;
        this.courseID = courseID;
        this.userID = userID;
        this.progress = progress;
        this.lastAccessedLessonID = lastAccessedLessonID;
        this.enrolledAt = enrolledAt;
        this.completedAt = completedAt;
        this.certificateIssued = certificateIssued;
    }

    public Long getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(Long enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public CourseLesson getLastAccessedLessonID() {
        return lastAccessedLessonID;
    }

    public void setLastAccessedLessonID(CourseLesson lastAccessedLessonID) {
        this.lastAccessedLessonID = lastAccessedLessonID;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Boolean getCertificateIssued() {
        return certificateIssued;
    }

    public void setCertificateIssued(Boolean certificateIssued) {
        this.certificateIssued = certificateIssued;
    }
}
