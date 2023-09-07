package com.codingduo.cinemabookingticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    private String name;

    private double price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private List<ShowTime> showTimeList;
}
