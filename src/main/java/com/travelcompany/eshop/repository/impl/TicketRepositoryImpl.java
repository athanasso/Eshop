package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.exception.TicketException;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.TicketRepository;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository{
    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public boolean createTicket(Ticket ticket) throws TicketException{
        if (ticket == null || ticket.getPaymentMethod() == null || ticket.getPassengerId() <= 0 || ticket.getPassengerId() >= 10
                || ticket.getItineraryId() <= 0 || ticket.getItineraryId() >= 12 || ticket.getPaymentAmount() <= 0)
            throw new TicketException("Some Ticket data are missing!");
        tickets.add(ticket);
        return true;
    }

    @Override
    public Ticket readTicket(int id) throws TicketException {
        for (Ticket ticket : tickets)
            if (ticket.getId() == id)
                return ticket;
        throw new TicketException("The ticket doesn't exist!");
    }

    @Override
    public List<Ticket> readTickets() {
        List<Ticket> returnTickets = new ArrayList<>();
        returnTickets.addAll(tickets);
        return returnTickets;
    }

    @Override
    public boolean updateTicket(int id, String newPaymentMethod) throws TicketException {
        Ticket ticket = readTicket(id);
        if (ticket == null)
            throw new TicketException("The ticket doesn't exist!");
        ticket.getPaymentMethod();
        return true;
    }

    @Override
    public boolean deleteTicket(int id) throws TicketException {
        Ticket ticket = readTicket(id);
        if (ticket == null)
            throw new TicketException("The ticket doesn't exist!");
        tickets.remove(ticket);
        return true;
    }
}
