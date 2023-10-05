package com.codingduo.cinemabookingticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    private Long id;
    private Long idRoom;
    private Long idShowtime;
    private boolean isSold;
}
