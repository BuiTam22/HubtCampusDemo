package com.codecampushubt.NCKH2024TQQD.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID", nullable = false)
    private Long notificationID;

    @Column(name = "UserID")
    private Long userID;

    @Column(name = "Type", length = 50, nullable = false)
    private String type;

    @Column(name = "Title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "RelatedID")
    private Long relatedID;

    @Column(name = "RelatedType", length = 50)
    private String relatedType;

    @Column(name = "IsRead", nullable = false)
    private Boolean isRead = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt", nullable = false)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ExpiresAt")
    private Date expiresAt;

    @Column(name = "Priority", length = 20, nullable = false)
    private String priority = "normal";

    public Notification() {
    }

    public Long getNotificationID() {
        return notificationID;
    }

    public Long getUserID() {
        return userID;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getRelatedID() {
        return relatedID;
    }

    public String getRelatedType() {
        return relatedType;
    }

    public Boolean getRead() {
        return isRead;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public String getPriority() {
        return priority;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRelatedID(Long relatedID) {
        this.relatedID = relatedID;
    }

    public void setRelatedType(String relatedType) {
        this.relatedType = relatedType;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationID=" + notificationID +
                ", userID=" + userID +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", relatedID=" + relatedID +
                ", relatedType='" + relatedType + '\'' +
                ", isRead=" + isRead +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                ", priority='" + priority + '\'' +
                '}';
    }
}
