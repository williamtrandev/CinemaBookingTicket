package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query("SELECT DISTINCT t " +
            "FROM Theater t " +
            "JOIN FETCH t.roomList r " +
            "JOIN r.showTimeList s " +
            "WHERE s.dateShow = :date " +
            "AND s.movie.id = :movieId")
    List<Theater> findTheatersByDateAndMovieId(Date date, Long movieId);
}
