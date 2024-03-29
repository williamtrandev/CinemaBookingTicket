package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.ShowTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowTime, Long> {

    @Query("SELECT s FROM ShowTime s JOIN FETCH s.ticket t WHERE s.movie.id = :movieId AND s.dateShow = :date")
    List<ShowTime> findShowTimesByMovieIdAndDateShow(Long movieId, Date date);
    ShowTime findShowTimeByIdAndRoomId(Long showtimeId, Long roomId);

    @Query("SELECT s FROM ShowTime s WHERE s.movie.id = :id AND s.dateShow >= CURRENT_DATE ORDER BY s.dateShow ASC, s.timeShow ASC")
    List<ShowTime> findShowTimesByMovieId(Long id);

    List<ShowTime> findShowTimesByDateShowAndRoomId(Date dateShow, Long roomId);
}
