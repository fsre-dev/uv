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
    private TicketRepository ticketRepository;

    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket update(Ticket ticket, Long id) {
        ticket.setId(id);
        return ticketRepository.save(ticket);
    }

    public Boolean delete(Long id) {
        Ticket ticket = this.findById(id);
        ticket.setIsDeleted(true);
        ticketRepository.save(ticket);
        return true;
    }

    public Page<Ticket> findAll(Pageable pageRequest) {
        return ticketRepository.findAllByIsDeletedFalse(pageRequest);
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Page<Ticket> findAllDeleted(Pageable pageRequest) {
        return ticketRepository.findAllByIsDeletedTrue(pageRequest);
    }
}