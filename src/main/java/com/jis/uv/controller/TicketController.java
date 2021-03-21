package com.jis.uv.controller;

import com.jis.uv.model.Ticket;
import com.jis.uv.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final Logger logger = LoggerFactory.getLogger(TicketController.class);

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        try {
            Ticket insertedTicket = ticketService.create(ticket);
            logger.info("Ticket successfully created");
            return ResponseEntity.ok(insertedTicket);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to create ticket {}", ticket.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket, @PathVariable Long id) {
        try {
            Ticket updatedTicket = ticketService.update(ticket, id);
            logger.info("Ticket successfully updated");
            return ResponseEntity.ok(updatedTicket);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to update ticket with id {}", id.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            ticketService.delete(id);
            logger.info("Ticket successfully deleted");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to delete ticket with id {}", id.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Ticket>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        Page<Ticket> tickets = ticketService.findAll(PageRequest.of(page, size));
        if (!tickets.isEmpty()) {
            logger.info("Successfully found all tickets");
            return ResponseEntity.ok(tickets);
        }
        logger.error("Unable to find tickets");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable("id") Long id) {
        Optional<Ticket> existingTicket = ticketService.findById(id);
        if (existingTicket.isPresent()) {
            logger.info("Found ticket with id {}", id.toString());
            return ResponseEntity.ok(existingTicket.get());
        }
        logger.error("Unable to find ticket with id {}", id.toString());
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/deleted")
    public ResponseEntity<List<Ticket>> findAllDeleted(@RequestParam Integer page, @RequestParam Integer size) {
        List<Ticket> tickets = ticketService.findAllDeleted(PageRequest.of(page, size));
        if (!tickets.isEmpty()) {
            logger.info("Successfully found all deleted tickets");
            return ResponseEntity.ok(tickets);
        }
        logger.error("Unable to find deleted tickets");
        return ResponseEntity.notFound().build();
    }
}