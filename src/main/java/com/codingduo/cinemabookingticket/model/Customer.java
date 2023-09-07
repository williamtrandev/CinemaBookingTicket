package com.codingduo.cinemabookingticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    @Column(name = "avatar_path")
    private String avatarPath;
    private int point;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<History> historyList;
}
