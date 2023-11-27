package com.codingduo.cinemabookingticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long id;

    @Column(name = "date_show")
    private Date dateShow;

    @Column(name = "time_show")
    private Time timeShow;

    @Column(columnDefinition = "TINYINT")
    private boolean deleted;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "showTime")
    private List<HistoryDetail> historyDetailList;
}
