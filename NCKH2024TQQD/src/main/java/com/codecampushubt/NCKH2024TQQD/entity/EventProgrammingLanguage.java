
package com.codecampushubt.NCKH2024TQQD.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EventProgrammingLanguages")
public class EventProgrammingLanguage {

    // Cột EventID kiểu bigint, bắt buộc (nullable = false)
    @Id
    @Column(name = "EventID", nullable = false)
    private Long eventID;

    // Cột Language kiểu varchar(50), bắt buộc (nullable = false)
    @Column(name = "Language", nullable = false, length = 50)
    private String language;

    // Constructor mặc định
    public EventProgrammingLanguage() {
    }

    // Constructor đầy đủ
    public EventProgrammingLanguage(Long eventID, String language) {
        this.eventID = eventID;
        this.language = language;
    }

    // Getter và Setter
    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // toString() để dễ dàng in ra khi cần
    @Override
    public String toString() {
        return "EventLanguage{" +
                "eventID=" + eventID +
                ", language='" + language + '\'' +
                '}';
    }
}
