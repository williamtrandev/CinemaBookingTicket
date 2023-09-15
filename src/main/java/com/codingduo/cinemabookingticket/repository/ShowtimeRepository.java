package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<ShowTime, Long> {
    @Query("SELECT st FROM ShowTime st " +
            "INNER JOIN st.room r " +
            "INNER JOIN r.theater t " +
            "WHERE t.id = :theaterId")
    List<ShowTime> findShowTimesByTheaterId(Long theaterId);
}
