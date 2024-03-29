package com.codingduo.cinemabookingticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    private String name;

    private String director;

    private String actors;

    @Column(name = "release_date")
    private Date releaseDate;

    private int duration;

    @Column(length = 2000)
    private String description;

    @Column(columnDefinition = "TINYINT")
    private boolean deleted;

    @Column(name = "is_coming", columnDefinition = "TINYINT")
    private boolean coming;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "trailer_path")
    private String trailerPath;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    private TagMovie tagMovie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<ShowTime> showTimeList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "genre_id"))
    private Collection<Genre> genres;


    @Transient
    public String getImagePathAtLocal() {
        if(imagePath == null) return null;
        return "/public/movie/" + imagePath;
    }
}
