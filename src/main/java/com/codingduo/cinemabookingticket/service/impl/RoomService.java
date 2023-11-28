package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.dto.RoomDTO;
import com.codingduo.cinemabookingticket.model.Room;
import com.codingduo.cinemabookingticket.model.Seat;
import com.codingduo.cinemabookingticket.repository.RoomRepository;
import com.codingduo.cinemabookingticket.repository.SeatRepository;
import com.codingduo.cinemabookingticket.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Override
    public Room getOne(Long id) {
        return roomRepository.getReferenceById(id);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(RoomDTO request) {
        Room room = new Room();
        room.setNumRow(request.getNumRow());
        Room savedRoom = roomRepository.save(room);
        List<Seat> seatsToAdd = new ArrayList<>();
        for(int i = 0; i < request.getNumRow(); i++) {
            for(int j = 0; j < 14; j++) {
                Seat seat = new Seat();
                seat.setRoom(room);
                seatsToAdd.add(seat);
            }
        }
        seatRepository.saveAll(seatsToAdd);
        return savedRoom;
    }
}
