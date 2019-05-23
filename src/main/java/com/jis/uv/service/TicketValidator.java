package com.jis.uv.service;

import com.jis.uv.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketValidator {
    Boolean validate(Ticket ticket) {
        Date date = new Date(System.currentTimeMillis());
        return (ticket != null &&
                ticket.getReservationDate() != null &&
                ticket.getReservationDate().compareTo(date) < 0 &&
                ticket.getGameDate() != null &&
                ticket.getOpponent() != null &&
                ticket.getSector() != null &&
                ticket.getRow() != null &&
                ticket.getSeat() != null &&
                ticket.getPrice() != null);
    }
}