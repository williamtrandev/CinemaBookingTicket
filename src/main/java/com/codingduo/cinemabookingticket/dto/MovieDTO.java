package com.codingduo.cinemabookingticket.dto;

import lombok.*;

import java.sql.Date;

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
    private boolean isComing;
    private String imgPath;
    private String trailerPath;
    private Long tagId;
}
