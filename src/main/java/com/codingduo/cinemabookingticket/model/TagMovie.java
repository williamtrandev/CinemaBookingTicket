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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name_tag"}))
public class TagMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "name_tag")
    private String nameTag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tagMovie")
    private List<Movie> movieList;
}
