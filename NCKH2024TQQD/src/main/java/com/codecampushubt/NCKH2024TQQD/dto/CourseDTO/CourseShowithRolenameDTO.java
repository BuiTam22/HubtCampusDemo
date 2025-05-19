package com.codecampushubt.NCKH2024TQQD.dto.CourseDTO;

import java.math.BigDecimal;

public class CourseShowithRolenameDTO {
    private Long courseID;
    private String title;
    private String slug;
    private String description;
    private String shortDescription;
    private String instructorUserName; // ✅ đúng kiểu
    private BigDecimal rating;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String imageUrl;
    private String roleName;

    public CourseShowithRolenameDTO(Long courseID, String title, String slug, String description, String shortDescription, String instructorUserName, BigDecimal rating, BigDecimal price, BigDecimal discountPrice, String imageUrl, String roleName) {
        this.courseID = courseID;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.shortDescription = shortDescription;
        this.instructorUserName = instructorUserName;
        this.rating = rating;
        this.price = price;
        this.discountPrice = discountPrice;
        this.imageUrl = imageUrl;
        this.roleName = roleName;
    }

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

    public String getInstructorUserName() {
        return instructorUserName;
    }

    public void setInstructorUserName(String instructorUserName) {
        this.instructorUserName = instructorUserName;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "CourseShowithRonameDTO{" +
                "courseID=" + courseID +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", instructorUserName='" + instructorUserName + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", imageUrl='" + imageUrl + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
