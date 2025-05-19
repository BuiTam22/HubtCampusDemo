package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CourseLessons")
public class CourseLesson {

    // ID TỰ ĐỘNG TĂNG CỦA BÀI HỌC
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LessonID", nullable = false, updatable = false)
    private Long lessonID;

    // KHÓA NGOẠI THAM CHIẾU ĐẾN BẢNG COURSEMODULES
    @ManyToOne
    @JoinColumn(name = "moduleID", nullable = false)
    private CourseModule module;

    @ManyToOne
    @JoinColumn(name = "CreatorID", nullable = false)
    private User creator;

    // TIÊU ĐỀ BÀI HỌC
    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    // MÔ TẢ BÀI HỌC
    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    // LOẠI BÀI HỌC (VIDEO, TEXT, QUIZ, ASSIGNMENT, CODING)
    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    // NỘI DUNG CHO BÀI HỌC DẠNG VĂN BẢN
    @Column(name = "Content", columnDefinition = "NVARCHAR(MAX)")
    private String content;

    // ĐƯỜNG DẪN image
    @Column(name = "Image", length = 255)
    private String image;

    // THỜI LƯỢNG (PHÚT)
    @Column(name = "Duration")
    private Integer duration;

    // THỨ TỰ SẮP XẾP TRONG MODULE
    @Column(name = "OrderIndex", nullable = true)
    private Integer orderIndex;

    // CÓ CHO PHÉP XEM THỬ KHÔNG (TRUE/FALSE)
    @Column(name = "IsPreview", nullable = false, columnDefinition = "BIT DEFAULT 0")
    private boolean isPreview;

    // BÀI HỌC ĐÃ XUẤT BẢN CHƯA (TRUE/FALSE)
    @Column(name = "IsPublished", nullable = false, columnDefinition = "BIT DEFAULT 0")
    private boolean isPublished;

    // THỜI ĐIỂM TẠO BÀI HỌC
    @Column(name = "CreatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime createdAt;

    // THỜI ĐIỂM CẬP NHẬT BÀI HỌC
    @Column(name = "UpdatedAt", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime updatedAt;

    @Column(name = "Slug", nullable = true)
    private String slug;

    @Column(name = "IsContest", nullable = true, columnDefinition = "BIT DEFAULT 0")
    private Boolean isContest;

    @Column(name = "ContestStartTime")
    private LocalDateTime contestStartTime;

    @Column(name = "ContestEndTime")
    private LocalDateTime contestEndTime;

    @Column(name = "TotalPoints", columnDefinition = "INT DEFAULT 0")
    private Integer totalPoints;

    // Constructor
    public CourseLesson() {
    }

//    public CourseLesson(Long lessonID, CourseModule module, String title, String description, String type, String content, String image, Integer duration, Integer orderIndex, Boolean isPreview, Boolean isPublished, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.lessonID = lessonID;
//        this.module = module;
//        this.title = title;
//        this.description = description;
//        this.type = type;
//        this.content = content;
//        this.videoUrl = videoUrl;
//        this.duration = duration;
//        this.orderIndex = orderIndex;
//        this.isPreview = isPreview;
//        this.isPublished = isPublished;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }


    public CourseLesson(Long lessonID, CourseModule module, String title, String description, String type, String content, String image, Integer duration, Integer orderIndex, Boolean isPreview, Boolean isPublished, LocalDateTime createdAt, LocalDateTime updatedAt, String slug, Boolean isContest, LocalDateTime contestStartTime, LocalDateTime contestEndTime, Integer totalPoints, User creator) {
        this.lessonID = lessonID;
        this.module = module;
        this.title = title;
        this.description = description;
        this.type = type;
        this.content = content;
        this.image = image;
        this.duration = duration;
        this.orderIndex = orderIndex;
        this.isPreview = false;
        this.isPublished = false;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.slug = slug;
        this.isContest = isContest;
        this.contestStartTime = contestStartTime;
        this.contestEndTime = contestEndTime;
        this.totalPoints = totalPoints;
        this.creator = creator;
    }
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // Gán thời gian hiện tại khi đối tượng được tạo mới
        this.updatedAt = LocalDateTime.now(); // Gán thời gian hiện tại cho trường updatedAt khi tạo mới
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now(); // Gán thời gian hiện tại khi đối tượng được cập nhật
    }


    public Long getLessonID() {
        return lessonID;
    }

    public void setLessonID(Long lessonID) {
        this.lessonID = lessonID;
    }

    public CourseModule getModule() {
        return module;
    }

    public void setModule(CourseModule module) {
        this.module = module;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Boolean getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(boolean isPreview) {
        this.isPreview = isPreview;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        this.isPublished = published;
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

    public Boolean getIsContest() {
        return isContest;
    }

    public void setIsContest(Boolean isContest) {
        this.isContest = isContest;
    }

    public LocalDateTime getContestStartTime() {
        return contestStartTime;
    }

    public void setContestStartTime(LocalDateTime contestStartTime) {
        this.contestStartTime = contestStartTime;
    }

    public LocalDateTime getContestEndTime() {
        return contestEndTime;
    }

    public void setContestEndTime(LocalDateTime contestEndTime) {
        this.contestEndTime = contestEndTime;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "CourseLesson{" +
                "lessonID=" + lessonID +
                ", module=" + module.getModuleID() +
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", duration=" + duration +
                ", orderIndex=" + orderIndex +
                ", isPreview=" + isPreview +
                ", isPublished=" + isPublished +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", slug='" + slug + '\'' +
                ", isContest=" + isContest +
                ", contestStartTime=" + contestStartTime +
                ", contestEndTime=" + contestEndTime +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
