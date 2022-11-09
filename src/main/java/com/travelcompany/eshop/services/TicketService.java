package com.travelcompany.eshop.services;

import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import java.math.BigDecimal;

public class TicketService {

    /**
     * Returns the ticket that has been made.
     *
     * @param ticketId takes the ticket id.
     * @param customer takes the customer.
     * @param itinerary takes the itinerary.
     * @param paymentMethod takes the payment method.
     * @return the ticket with the appropriate parameters.
     */
    public static Ticket DiscountCounter(int ticketId, Customer customer, Itinerary itinerary, PaymentMethod paymentMethod) {
        // Creating Ticket.
        String categoryOfCustomer = customer.getCategory().toString();
        BigDecimal basicPriceOfTicket = new BigDecimal(itinerary.getBasicPrice());

        //  Checking for Ordering and Discount policy based on Customer Category and Payment Method.
        BigDecimal discount = new BigDecimal(1);
        if (categoryOfCustomer.equals("Business")) {
            discount = discount.add(new BigDecimal("-0.1"));
        }
        if (categoryOfCustomer.equals("Individual")) {
            discount = discount.add(new BigDecimal("0.2"));
        }
        if (paymentMethod.equals("Credit Card")) {
            discount = discount.add(new BigDecimal("-0.1"));
        }

        return new Ticket(ticketId, customer.getId(), itinerary.getId(), paymentMethod, basicPriceOfTicket.multiply(discount).intValue());
    }
}
