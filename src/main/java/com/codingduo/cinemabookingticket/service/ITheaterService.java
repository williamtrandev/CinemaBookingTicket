package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.dto.TheaterWithShowtimeDTO;
import com.codingduo.cinemabookingticket.model.Theater;

import java.sql.Date;
import java.util.List;
//import java.util.Date;

public interface ITheaterService {
    Theater save(Theater theater);

    List<Theater> getAll();

    List<TheaterWithShowtimeDTO> getAllByDateAndMovieId(Date date, Long id);
//    List<TheaterWithShowtimeDTO> test(Date date, Long id);
}
