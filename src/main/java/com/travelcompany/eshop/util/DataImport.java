package com.travelcompany.eshop.util;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import com.travelcompany.eshop.repository.impl.CustomerRepositoryImpl;
import com.travelcompany.eshop.repository.impl.ItineraryRepositoryImpl;
import com.travelcompany.eshop.repository.impl.TicketRepositoryImpl;
import static com.travelcompany.eshop.util.DiscountCounter.DiscountCounter;

public class DataImport {

    private final static String[] CUSTOMERS = {
        "1, Maria Iordanou, miordanou@mail.com, Athens, Greek, Individual",
        "2, Dimitriou Dimitrios, ddimitriou@mail.com, Athens, Greek, Individual",
        "3, Ioannis Ioannou, iioannou@mail.com, Athens, Greek, Business",
        "4, Antonio Molinari, amolinari@mail.com, Milan, Italian, Individual",
        "5, Frederico Rossi, frossi@mail.com, Milan, Italian, Individual",
        "6, Mario Conti, mconti@mail.com, Rome, Italian, Business",
        "7, Nathan Martin, nmartin@mail.com, Lyon, French, Business",
        "8, Enzo Collin, ecollin@mail.com, Lyon, French, Individual",
        "9, Frederic Michel, fmichel@mail.com, Athens, French, Individual"

    };

    private final static String[] ITINERARIES = {
        "1, ATH, PAR, 2022-02-22 13:35:00, SkyLines, 300",
        "2, ATH, LON, 2022-02-22 13:40:00, SkyLines, 420",
        "3, ATH, AMS, 2022-02-22 13:45:00, SkyLines, 280",
        "4, ATH, PAR, 2022-02-22 14:20:00, SkyLines, 310",
        "7, ATH, DUB, 2022-02-22 14:35:00, SkyLines, 880",
        "8, ATH, FRA, 2022-02-22 14:55:00, SkyLines, 380",
        "9, ATH, FRA, 2022-02-22 15:35:00, SkyLines, 350",
        "10, ATH, MEX, 2022-02-22 16:00:00, SkyLines, 1020",
        "11, ATH, DUB, 2022-02-22 16:35:00, SkyLines, 770"
    };

    /**
     * Returns all the Customers of TravelCompany. We handle customer email error and if the customer id error.
     *
     * @return the customers of TravelCompany
     */
    public static CustomerRepository generateCustomers() {

        CustomerRepository customers = new CustomerRepositoryImpl();
        for (String customerString : CUSTOMERS) {
            try {
                String[] words = customerString.split(",");
                Customer customer = new Customer();
                customer.setId(Integer.parseInt(words[0]));
                customer.setName(words[1]);
                customer.setEmail(words[2].trim());
                customer.setAddress(words[3].trim());
                customer.setNationality(words[4].trim());
                customer.setCategory(words[5].trim());

                customers.createCustomer(customer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return customers;
    }
    
    /**
     * Returns all the Itineraries of TravelCompany. We handle airport code missing and airport id missing.
     *
     * @return the itineraries of TravelCompany
     */
    public static ItineraryRepository generateItineraries() {
        
        ItineraryRepository itineraries = new ItineraryRepositoryImpl();
        for (String itineraryString : ITINERARIES) {
            try {
                String[] words = itineraryString.split(",");
                Itinerary itinerary = new Itinerary();
                itinerary.setId(Integer.parseInt(words[0]));
                itinerary.setDepartureAirportCode(words[1].trim());
                itinerary.setDestinationAirportCode(words[2].trim());
                itinerary.setDepartureDate(words[3]);
                itinerary.setAirline(words[4].trim());
                itinerary.setBasicPrice(Integer.parseInt(words[5].trim()));

                itineraries.createItinerary(itinerary);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return itineraries;
    }

    /**
     * Returns all the Tickets issued of TravelCompany.
     *
     * @return the Tickets of TravelCompany
     */
    public static TicketRepository generateTickets() {
        // Generating the Customers and Itineraries.
        CustomerRepository customers = generateCustomers();
        ItineraryRepository itineraries = generateItineraries();
        System.out.println("Customers and Itineraries Generated...");
        System.out.println("Tickets Generated...\n");
        
        TicketRepository tickets = new TicketRepositoryImpl();
        // Generating the Tickets.
        // We consider that the below code for generating tickets, we have taken into account that the customers exists, and we
        // go by hand and add the customer ids. If customers did not been created due to mail error, then we have to implement our code differently.
        // Since we go by hand there is no need for checking for customers ids.
        try {
            // Ticket1
            tickets.createTicket(DiscountCounter(1, customers.readCustomer(1), itineraries.readItinerary(2), "Cash"));
            // Ticket2
            tickets.createTicket(DiscountCounter(2, customers.readCustomer(2), itineraries.readItinerary(3), "Cash"));
            // Ticket3
            tickets.createTicket(DiscountCounter(3, customers.readCustomer(3), itineraries.readItinerary(3), "Credit Cart"));
            // Ticket4
            tickets.createTicket(DiscountCounter(4, customers.readCustomer(2), itineraries.readItinerary(4), "Credit Cart"));
            // Ticket5
            tickets.createTicket(DiscountCounter(5, customers.readCustomer(3), itineraries.readItinerary(4), "Cash"));
            // Ticket6
            tickets.createTicket(DiscountCounter(6, customers.readCustomer(4), itineraries.readItinerary(7), "Credit Cart"));
            // Ticket7
            tickets.createTicket(DiscountCounter(7, customers.readCustomer(5), itineraries.readItinerary(7), "Credit Cart"));
            // Ticket8
            tickets.createTicket(DiscountCounter(8, customers.readCustomer(2), itineraries.readItinerary(10), "Cash"));
            // Ticket9
            tickets.createTicket(DiscountCounter(9, customers.readCustomer(1), itineraries.readItinerary(3), "Cash"));
        }
        catch (Exception e) {
            System.out.println("Received the message, '" + e.getMessage() + "'.\n");
        }
        
        return tickets;
    }
}
