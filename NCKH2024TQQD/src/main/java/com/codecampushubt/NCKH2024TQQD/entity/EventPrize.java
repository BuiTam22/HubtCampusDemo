

package com.codecampushubt.NCKH2024TQQD.entity;

import java.math.BigDecimal;

        import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EventPrizes")
public class EventPrize {

    // Khóa chính, tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrizeID", nullable = false, updatable = false)
    private Long prizeID;

    // Khóa ngoại tham chiếu Event
    @Column(name = "EventID")
    private Long eventID;

    // Xếp hạng (giải nhất, nhì, ba, ...)
    @Column(name = "Rank")
    private Integer rank;

    // Số tiền giải thưởng
    @Column(name = "PrizeAmount", precision = 10, scale = 2)
    private BigDecimal prizeAmount;

    // Mô tả giải thưởng
    @Column(name = "Description", length = 500)
    private String description;

    // Constructor không tham số
    public EventPrize() {}

    // Constructor đầy đủ
    public EventPrize(Long eventID, Integer rank, BigDecimal prizeAmount, String description) {
        this.eventID = eventID;
        this.rank = rank;
        this.prizeAmount = prizeAmount;
        this.description = description;
    }

    // Getter và Setter
    public Long getPrizeID() {
        return prizeID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public BigDecimal getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(BigDecimal prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString để dễ debug/log
    @Override
    public String toString() {
        return "Prize{" +
                "prizeID=" + prizeID +
                ", eventID=" + eventID +
                ", rank=" + rank +
                ", prizeAmount=" + prizeAmount +
                ", description='" + description + '\'' +
                '}';
    }
}
