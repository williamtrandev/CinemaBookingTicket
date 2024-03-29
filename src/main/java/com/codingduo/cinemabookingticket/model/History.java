package com.codingduo.cinemabookingticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    private double total;

    private Date dateBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private UserSystem customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "history")
    private List<HistoryDetail> historyDetailList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "history")
    private List<ComboHistory> comboHistoryList;

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", total=" + total +
                ", dateBooking=" + dateBooking +
                ", customer=" + customer +
                ", historyDetailList=" + historyDetailList +
                ", comboHistoryList=" + comboHistoryList +
                '}';
    }
}
