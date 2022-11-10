package com.travelcompany.eshop.services;

import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;


public interface TicketService {

    /**
     *
     * @param ticketId
     * @param customer
     * @param itinerary
     * @param paymentMethod
     * @return the price after discount
     */
    public Ticket DiscountCounter(int ticketId, Customer customer, Itinerary itinerary, PaymentMethod paymentMethod);
}
