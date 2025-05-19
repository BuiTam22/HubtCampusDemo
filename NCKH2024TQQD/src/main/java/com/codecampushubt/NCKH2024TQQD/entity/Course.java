package com.codecampushubt.NCKH2024TQQD.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.github.slugify.Slugify;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    private Long courseID;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    // Tạo slug trước khi lưu hoặc cập nhật
//    @PrePersist
//    @PreUpdate
//    public void generateSlug() {
//        // đảm bảo slug luôn giống với title, ngay cả khi title bị thay đổi
//        if (this.slug == null || !this.slug.equals(new Slugify().slugify(this.title))) {
//            this.slug = new Slugify().slugify(this.title);
//        }
//    }

    @Column(name = "Slug", unique = true, length = 255)
    private String slug;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "ShortDescription", length = 500)
    private String shortDescription;

    @ManyToOne()
    @JoinColumn(name = "InstructorID")
    private User instructor;

    @Column(name = "Level", length = 20)
    private String level;

    @Column(name = "Category", length = 50)
    private String category;

    @Column(name = "SubCategory", length = 50)
    private String subCategory;

    @Column(name = "Language", length = 20, nullable = false)
    private String language = "vi"; // Giá trị mặc định

    @Column(name = "Duration")
    private Integer duration;

    @Column(name = "Capacity")
    private Integer capacity;

    @Column(name = "EnrolledCount", nullable = false)
    private Integer enrolledCount = 0;

    @Column(name = "Rating", precision = 3, scale = 2, nullable = false)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "RatingCount", nullable = false)
    private Integer ratingCount = 0;

    @Column(name = "Price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "DiscountPrice", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "ImageUrl", length = 255)
    private String imageUrl;

    @Column(name = "VideoUrl", length = 255)
    private String videoUrl;

    @Column(name = "Requirements", columnDefinition = "NVARCHAR(MAX)")
    private String requirements;

    @Column(name = "Objectives", columnDefinition = "NVARCHAR(MAX)")
    private String objectives;

    @Column(name = "Syllabus", columnDefinition = "NVARCHAR(MAX)")
    private String syllabus;

    @Column(name = "Status", length = 20, nullable = false)
    private String status = "draft";

    @Column(name = "IsPublished", nullable = false)
    private Boolean isPublished = false;

    @Column(name = "PublishedAt")
    private LocalDateTime publishedAt;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UpdatedAt", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "DeletedAt")
    private LocalDateTime deletedAt;


    //Constructor
    public Course(){}

    public Course(String title, String description, String shortDescription, User instructor, String level, String category, String subCategory, String language, Integer duration, Integer capacity, Integer enrolledCount, BigDecimal rating, Integer ratingCount, BigDecimal price, BigDecimal discountPrice, String imageUrl, String videoUrl, String requirements, String objectives, String syllabus, String status, Boolean isPublished, LocalDateTime publishedAt, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
//        this.courseID = courseID;
        // this.slug = slug;
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
//        this.instructor = instructor;
        this.level = level;
        this.category = category;
        this.subCategory = subCategory;
        this.language = language;
        this.duration = duration;
        this.capacity = capacity;
        this.enrolledCount = enrolledCount;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.price = price;
        this.discountPrice = discountPrice;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.requirements = requirements;
        this.objectives = objectives;
        this.syllabus = syllabus;
        this.status = status;
        this.isPublished = isPublished;
        this.publishedAt = publishedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
//    geter and setter


    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

     public void setSlug(String slug) {
         this.slug = slug;
     }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getEnrolledCount() {
        return enrolledCount;
    }

    public void setEnrolledCount(Integer enrolledCount) {
        this.enrolledCount = enrolledCount;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", instructor=" + instructor +
                ", level='" + level + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", language='" + language + '\'' +
                ", duration=" + duration +
                ", capacity=" + capacity +
                ", enrolledCount=" + enrolledCount +
                ", rating=" + rating +
                ", ratingCount=" + ratingCount +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", requirements='" + requirements + '\'' +
                ", objectives='" + objectives + '\'' +
                ", syllabus='" + syllabus + '\'' +
                ", status='" + status + '\'' +
                ", isPublished=" + isPublished +
                ", publishedAt=" + publishedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}

