package com.jis.uv.service;

import com.jis.uv.model.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketValidator {
    Boolean validate(Ticket ticket) {
        return (ticket != null &&
                ticket.getReservationDate() != null &&
                ticket.getGameDate() != null &&
                ticket.getOpponent() != null &&
                ticket.getSector() != null &&
                ticket.getRow() != null &&
                ticket.getSeat() != null &&
                ticket.getPrice() != null);
    }
}