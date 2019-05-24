package com.jis.uv.service;

import com.jis.uv.model.Ticket;
import com.jis.uv.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private TicketValidator ticketValidator;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketValidator ticketValidator) {
        this.ticketRepository = ticketRepository;
        this.ticketValidator = ticketValidator;
    }

    public Ticket create(Ticket ticket) throws Exception {
        if (ticketValidator.validate(ticket)) {
            return ticketRepository.save(ticket);
        }
        throw new Exception("Invalid ticket");
    }

    public Ticket update(Ticket ticket, Long id) throws Exception {
        Optional<Ticket> existingTicket = this.findById(id);
        if (ticketValidator.validate(ticket) && existingTicket.isPresent()) {
            ticket.setId(id);
            return ticketRepository.save(ticket);
        }
        throw new Exception("Could not update ticket");
    }

    public void delete(Long id) throws Exception {
        Optional<Ticket> ticket = this.findById(id);
        if (ticket.isPresent()) {
            Ticket deletedTicket = ticket.get();
            deletedTicket.setIsDeleted(true);
            ticketRepository.save(deletedTicket);
        }
        throw new Exception("Ticket does not exist");
    }

    public List<Ticket> findAll(Pageable pageRequest) {
        Page<Ticket> pageableTickets = ticketRepository.findAllByIsDeletedFalse(pageRequest);
        return pageableTickets.getContent();
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> findAllDeleted(Pageable pageRequest) {
        Page<Ticket> pageableTickets = ticketRepository.findAllByIsDeletedTrue(pageRequest);
        return pageableTickets.getContent();
    }
}