package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "StoryViews", uniqueConstraints = @UniqueConstraint(columnNames = {"storyID", "viewerID"}))
public class StoryView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long viewID;

//    @ManyToOne
//    @JoinColumn(name = "storyID", nullable = false)
//    private Story story;

//    @ManyToOne
//    @JoinColumn(name = "viewerID", nullable = false)
//    private User viewer;

    private LocalDateTime viewedAt = LocalDateTime.now();

    public Long getViewID() {
        return viewID;
    }

    public void setViewID(Long viewID) {
        this.viewID = viewID;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
    public StoryView() {}
    public StoryView(Long viewID, LocalDateTime viewedAt) {
        this.viewID = viewID;
        this.viewedAt = viewedAt;
    }
}
