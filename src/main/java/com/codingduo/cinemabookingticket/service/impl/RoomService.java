package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.model.Room;
import com.codingduo.cinemabookingticket.repository.RoomRepository;
import com.codingduo.cinemabookingticket.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Room getOne(Long id) {
        return roomRepository.getReferenceById(id);
    }
}
