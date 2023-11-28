package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.dto.RoomDTO;
import com.codingduo.cinemabookingticket.model.Room;
import com.codingduo.cinemabookingticket.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/room")
public class RoomAPI {
    @Autowired
    private IRoomService roomService;

    @PostMapping({"", "/"})
    public ResponseEntity<?> newRoom(@RequestBody RoomDTO request) {
        Room room = roomService.save(request);
        room.setShowTimeList(null);
        return ResponseEntity.ok(room);
    }
}
