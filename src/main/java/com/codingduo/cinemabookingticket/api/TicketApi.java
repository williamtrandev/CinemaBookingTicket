package com.codingduo.cinemabookingticket.api;

import com.codingduo.cinemabookingticket.model.Ticket;
import com.codingduo.cinemabookingticket.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketApi {
    @Autowired
    private ITicketService ticketService;

    @PutMapping("/{id}")
    public ResponseEntity<Double> changePrice(@PathVariable("id") Long id, @RequestParam("newPrice") double newPrice) {
        Ticket ticket = ticketService.changePrice(id, newPrice);
        return new ResponseEntity<>(ticket.getPrice(), HttpStatus.OK);
    }
}
