package com.codingduo.cinemabookingticket.service;

import com.codingduo.cinemabookingticket.model.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> getAll();
    Ticket changePrice(Long id, double price);
}
