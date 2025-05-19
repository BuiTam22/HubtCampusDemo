package com.codecampushubt.NCKH2024TQQD.dto.CourseDTO;

import java.math.BigDecimal;

public class CourseShowDTO {
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

    public CourseShowDTO(Long courseID, String title, String slug, String description, String shortDescription, String instructorUserName, BigDecimal rating, BigDecimal price, BigDecimal discountPrice, String imageUrl) {
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
    }

    public Long getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getInstructorUserName() { // ✅ viết đúng tên getter
        return instructorUserName;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
