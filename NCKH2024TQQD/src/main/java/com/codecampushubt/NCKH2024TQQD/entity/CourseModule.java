package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CourseModules")
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ModuleID")
    private Long moduleID;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseLesson> lessons = new ArrayList<>();



    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "OrderIndex", nullable = false)
    private Integer orderIndex;

    @Column(name = "Duration")
    private Integer duration;

    @Column(name = "IsPublished", nullable = false)
    private Boolean isPublished = false;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "Slug", nullable = true)
    private String slug;

    // Constructor
    public CourseModule() {
    }

    public CourseModule(Long moduleID, Course course, List<CourseLesson> lessons, String title, String description, Integer orderIndex, Integer duration, Boolean isPublished, LocalDateTime createdAt, LocalDateTime updatedAt, String slug) {
        this.moduleID = moduleID;
        this.course = course;
        this.lessons = lessons;
        this.title = title;
        this.description = description;
        this.orderIndex = orderIndex;
        this.duration = duration;
        this.isPublished = isPublished;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.slug = slug;
    }

    public Long getModuleID() {
        return moduleID;
    }

    public void setModuleID(Long moduleID) {
        this.moduleID = moduleID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<CourseLesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<CourseLesson> lessons) {
        this.lessons = lessons;
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

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
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

    @Override
    public String toString() {
        return "CourseModule{" +
                "moduleID=" + moduleID +
                ", course=" + course +
                ", lessons=" + lessons +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", orderIndex=" + orderIndex +
                ", duration=" + duration +
                ", isPublished=" + isPublished +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", slug='" + slug + '\'' +
                '}';
    }
}

