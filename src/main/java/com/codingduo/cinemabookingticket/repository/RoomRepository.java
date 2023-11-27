package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
