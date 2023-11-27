package com.codingduo.cinemabookingticket.repository;

import com.codingduo.cinemabookingticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
