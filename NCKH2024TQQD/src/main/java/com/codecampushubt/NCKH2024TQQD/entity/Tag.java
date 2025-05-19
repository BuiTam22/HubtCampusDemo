package com.codecampushubt.NCKH2024TQQD.entity;


import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "Tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TagID")
    private Integer tagId;

    @Column(name = "Name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "Description", length = 255)
    private String description;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UsageCount")
    private Integer usageCount = 0;
    public Tag(){

    };
    public Tag(Integer tagId, String name, String description, LocalDateTime createdAt, Integer usageCount) {
        this.tagId = tagId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.usageCount = usageCount;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }




}
