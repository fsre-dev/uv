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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping
    public Ticket insert(@RequestBody Ticket ticket) {
        return ticketService.insert(ticket);
    }

    @DeleteMapping
    public void delete(@RequestBody Ticket ticket) {
        ticketService.delete(ticket);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Ticket>> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Ticket> tickets = ticketService.findAll(PageRequest.of(page, size));
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/deleted", params = {"page", "size"})
    public ResponseEntity<Page<Ticket>> findAllDeleted(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<Ticket> tickets = ticketService.findAllDeleted(PageRequest.of(page, size));
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}