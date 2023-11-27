package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.model.Room;

import java.util.List;

public interface IRoomService {
    Room getOne(Long id);

    List<Room> getAll();
}
