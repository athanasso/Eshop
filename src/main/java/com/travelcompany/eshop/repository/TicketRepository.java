package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.Ticket;
import java.util.List;

public interface TicketRepository {
     //CRUD

    boolean createTicket(Ticket ticket);

    Ticket readTicket(int id);

    List<Ticket> readTickets();

    boolean updateTicket(int id, String newPaymentMethod);

    boolean deleteTicket(int id);
}
