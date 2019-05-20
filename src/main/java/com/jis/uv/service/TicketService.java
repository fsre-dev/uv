package com.jis.uv.service;

import com.jis.uv.model.Ticket;
import com.jis.uv.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    public Ticket insert(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void delete(Ticket ticket) {
        ticket.setIsDeleted(true);
        ticketRepository.save(ticket);
    }

    public Page<Ticket> findAll(Pageable pageRequest) {
        return ticketRepository.findAll(pageRequest);
    }

    public Ticket findById(Long id) {
        return ticketRepository.getOne(id);
    }

    public Page<Ticket> findAllDeleted(Pageable pageRequest) {
        return ticketRepository.findAllByIsDeleted(true, pageRequest);
    }
}
