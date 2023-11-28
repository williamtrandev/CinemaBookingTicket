package com.codingduo.cinemabookingticket.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeRequest {
    private Long roomId;
    private Long movieId;
    private int duration;
    private Long ticketId;
    private Date dateShow;
    private Time timeShow;
}
