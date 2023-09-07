package com.codingduo.cinemabookingticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "num_row")
    private int numRow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", referencedColumnName = "theater_id")
    private Theater theater;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<ShowTime> showTimeList;

}
