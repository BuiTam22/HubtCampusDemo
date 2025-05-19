package com.codecampushubt.NCKH2024TQQD.dto.CourseModule;

public class CourseModuleFILLDTO {
    private String title;
    private String slug;

    public CourseModuleFILLDTO(String title, String slug) {
        this.title = title;
        this.slug = slug;
    }
    public CourseModuleFILLDTO() {}

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

    @Override
    public String toString() {
        return "CourseModuleFILLDTO{" +
                "title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
