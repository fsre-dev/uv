package com.jis.uv.service;

import com.jis.uv.model.Ticket;
import com.jis.uv.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    TicketRepository ticketRepository;

    public void insert(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void delete(Ticket ticket) {
        ticket.setIsDeleted(true);
        ticketRepository.save(ticket);
    }

    public Page<Ticket> findAll(Pageable pageRequest) {
        return ticketRepository.findAll(pageRequest);
    }
}
