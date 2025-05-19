package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AchievementID", nullable = false)
    private Integer achievementID;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "Type", length = 50)
    private String type;

    @Column(name = "Icon", length = 255)
    private String icon;

    @Column(name = "Points")
    private Integer points;

    @Column(name = "Criteria", columnDefinition = "nvarchar(MAX)")
    private String criteria;

    @Column(name = "CreatedAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Constructor mặc định
    public Achievement() {
    }

    // Getters và Setters

    public Integer getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(Integer achievementID) {
        this.achievementID = achievementID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // toString method để in thông tin dễ dàng
    @Override
    public String toString() {
        return "Achievement{" +
                "achievementID=" + achievementID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", points=" + points +
                ", criteria='" + criteria + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
