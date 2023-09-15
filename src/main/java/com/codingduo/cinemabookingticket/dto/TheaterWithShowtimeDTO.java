package com.codingduo.cinemabookingticket.dto;

import com.codingduo.cinemabookingticket.model.Room;
import com.codingduo.cinemabookingticket.model.ShowTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterWithShowtimeDTO {
    private Long id;
    private String address;
    private String name;
    List<ShowTimeDTO> showTimeList;
}
