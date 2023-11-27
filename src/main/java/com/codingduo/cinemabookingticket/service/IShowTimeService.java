package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.ShowTimeDTO;
import com.codingduo.cinemabookingticket.model.ShowTime;

import java.sql.Date;
import java.util.List;

public interface IShowTimeService {
    List<ShowTimeDTO> getShowTimeByMovieAndDateShow(Long movieId, Date date);
    ShowTime getShowTimeByIdAndRoomId(Long showtimeId, Long roomId);
}
