package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByRoomId(Long id);
}
