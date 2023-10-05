package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.model.Seat;

import java.util.List;

public interface ISeatService {
    List<Seat> getAllByRoomId(Long room_id);
}
