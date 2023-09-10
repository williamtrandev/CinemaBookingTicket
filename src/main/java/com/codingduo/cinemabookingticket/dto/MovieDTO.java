package com.codingduo.cinemabookingticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long id;
    private String name;
    private String director;
    private String actors;
    private Date releaseDate;
    private int duration;
    private String description;
    @JsonProperty("isComing")
    private boolean isComing;
    private String imgPath;
    private String trailerPath;
    private Long tagId;
    private boolean deleted;
    private List<String> genres;
}
