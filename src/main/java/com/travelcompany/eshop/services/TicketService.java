package com.travelcompany.eshop.services;

import com.travelcompany.eshop.enums.Category;
import static com.travelcompany.eshop.enums.Category.Business;
import static com.travelcompany.eshop.enums.Category.Individual;
import com.travelcompany.eshop.enums.PaymentMethod;
import static com.travelcompany.eshop.enums.PaymentMethod.CreditCard;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.TicketRepository;
import java.math.BigDecimal;
import java.util.HashMap;

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
        Category categoryOfCustomer = customer.getCategory();
        BigDecimal basicPriceOfTicket = new BigDecimal(itinerary.getBasicPrice());

        //  Checking for Ordering and Discount policy based on Customer Category and Payment Method.
        BigDecimal discount = new BigDecimal(1);
        if (categoryOfCustomer.equals(Business)) {
            discount = discount.add(new BigDecimal("-0.1"));
        }
        if (categoryOfCustomer.equals(Individual)) {
            discount = discount.add(new BigDecimal("0.2"));
        }
        if (paymentMethod.equals(CreditCard)) {
            discount = discount.add(new BigDecimal("-0.1"));
        }

        return new Ticket(ticketId, customer.getId(), itinerary.getId(), paymentMethod, basicPriceOfTicket.multiply(discount).intValue());
    }
    
    /**
     * Returns a HashMap with key the customer id and value the cost of tickets that they purchased.
     *
     * @param tickets all the tickets of TravelCompany.
     * @return a HashMap with customerId and cost of tickets that purchased each.
     */
    public static HashMap<Integer, Integer> collectionOfTicketsWithPaymentAmount(TicketRepository tickets) {
        // Creating collection of HashMap.
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (Ticket ticket : tickets.readTickets()) {
            if (hashmap.containsKey(ticket.getPassengerId())){
                int value = hashmap.get(ticket.getPassengerId());
                hashmap.put(ticket.getPassengerId(), value + ticket.getPaymentAmount());
            } else {
                hashmap.put(ticket.getPassengerId(), ticket.getPaymentAmount());
            }
        }
        return hashmap;
    }

    /**
     * Returns a HashMap with key the customer id and value the number of tickets that they purchased.
     *
     * @param tickets all the tickets of TravelCompany.
     * @return a HashMap with customerId and number of tickets that purchased each.
     */
    public static HashMap<Integer, Integer> collectionOfTicketsWithCustomers(TicketRepository tickets) {
        // Creating collection of HashMap.
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (Ticket ticket : tickets.readTickets()) {
            if (hashmap.containsKey(ticket.getPassengerId())){
                int value = hashmap.get(ticket.getPassengerId());
                hashmap.put(ticket.getPassengerId(), value + 1);
            } else {
                hashmap.put(ticket.getPassengerId(), 1);
            }
        }
        return hashmap;
    }
}