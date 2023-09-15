package com.codingduo.cinemabookingticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long id;

    private String name;

    private String address;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theater")
    private List<Room> roomList;

    @OneToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "theater_id")
    private Admin admin;
}
