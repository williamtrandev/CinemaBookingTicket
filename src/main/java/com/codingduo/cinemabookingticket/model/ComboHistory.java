package com.codingduo.cinemabookingticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComboHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_history_id")
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "combo_id", referencedColumnName = "combo_id")
    private Combo combo;

    @ManyToOne
    @JoinColumn(name = "history_id", referencedColumnName = "history_id")
    private History history;
}
