package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowTime, Long> {

    List<ShowTime> findShowTimesByMovieIdAndDateShow(Long movieId, Date date);
    ShowTime findShowTimeByIdAndRoomId(Long showtimeId, Long roomId);
}
