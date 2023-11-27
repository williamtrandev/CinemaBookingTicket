package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.model.Ticket;
import com.codingduo.cinemabookingticket.repository.TicketRepository;
import com.codingduo.cinemabookingticket.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket changePrice(Long id, double price) {
        Ticket ticket = ticketRepository.getReferenceById(id);
        ticket.setPrice(price);
        return ticketRepository.save(ticket);
    }
}
