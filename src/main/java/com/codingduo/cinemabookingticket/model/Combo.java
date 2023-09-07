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
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combo_id")
    private Long id;

    @Column(name = "name_combo")
    private String nameCombo;

    private String detail;

    private double price;

    @Column(name = "image_path")
    private String imagePath;

    @Column(columnDefinition = "TINYINT")
    private boolean deleted;

    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL)
    private List<ComboHistory> comboHistoryList;

}
