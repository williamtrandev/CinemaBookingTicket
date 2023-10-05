package com.codingduo.cinemabookingticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowTimeDTO {
    private Long id;
    private Date dateShow;
    private Time timeShow;
    private boolean deleted;
    private Long idMovie;
    private Long idTicket;
    private Long idRoom;
    private double price;
}
