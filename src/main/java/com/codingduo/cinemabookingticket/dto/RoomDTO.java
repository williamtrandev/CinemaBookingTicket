package com.codingduo.cinemabookingticket.dto;

import com.codingduo.cinemabookingticket.model.ShowTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private int numRow;
    private List<ShowTimeDTO> showTimeList;
}
