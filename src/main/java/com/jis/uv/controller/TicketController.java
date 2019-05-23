package com.jis.uv.controller;

import com.jis.uv.model.Ticket;
import com.jis.uv.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        try {
            Ticket insertedTicket = ticketService.create(ticket);
            return ResponseEntity.ok(insertedTicket);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket, @PathVariable Long id) {
        try {
            Ticket updatedTicket = ticketService.update(ticket, id);
            return ResponseEntity.ok(updatedTicket);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            ticketService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        List<Ticket> tickets = ticketService.findAll(PageRequest.of(page, size));
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable("id") Long id) {
        Optional<Ticket> existingTicket = ticketService.findById(id);
        if (existingTicket.isPresent()) {
            Ticket ticket = existingTicket.get();
            return ResponseEntity.ok(ticket);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/deleted")
    public ResponseEntity<List<Ticket>> findAllDeleted(@RequestParam Integer page, @RequestParam Integer size) {
        List<Ticket> tickets = ticketService.findAllDeleted(PageRequest.of(page, size));
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}