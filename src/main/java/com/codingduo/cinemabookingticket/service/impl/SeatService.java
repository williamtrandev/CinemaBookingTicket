package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.model.Seat;
import com.codingduo.cinemabookingticket.repository.SeatRepository;
import com.codingduo.cinemabookingticket.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements ISeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAllByRoomId(Long room_id) {
        return seatRepository.findAllByRoomId(room_id);
    }
}
