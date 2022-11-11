package com.travelcompany.eshop.dtos;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import static com.travelcompany.eshop.services.TicketService.collectionOfTicketsWithCustomers;
import static com.travelcompany.eshop.services.TicketService.collectionOfTicketsWithPaymentAmount;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReports {

    /**
     * TotalCostAndNumberOfTicketsToFile() method prints to file a report for
     * the total cost of tickets and total number of tickets.
     *
     * @param tickets all tickets of TravelCompany.
     */
    public static void totalCostAndNumberOfTicketsToFile(TicketRepository tickets) {
        File file = new File("ΤotalCostAndNumberOfTickets.csv");

        // Creating Report.
        System.out.println("List of the total number and cost of tickets for all customers are being saves into file 'ΤotalCostAndNumberOfTickets.csv'");

        try ( PrintWriter pw = new PrintWriter(file)) {
            // Calculating the total cost of all tickets.
            int totalCost = 0;
            for (Ticket ticket : tickets.readTickets()) {
                totalCost += ticket.getPaymentAmount();
            }
            pw.println("The total number of tickets are " + tickets.readTickets().size());
            pw.println("The total cost of tickets are " + totalCost);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * TotalItinerariesPerDestinationAndDepartureToFile() method prints to file
     * the total itineraries per destination and per departure.
     *
     * @param itineraries all itineraries of TravelCompany.
     */
    public static void totalItinerariesPerDestinationAndDepartureToFile(ItineraryRepository itineraries) {
        File file = new File("TotalItinerariesPerDestinationAndDeparture.csv");

        // Creating Report.
        System.out.println("List of the total offered itineraries per destination and departure are being saves into file 'TotalItinerariesPerDestinationAndDeparture.csv'");

        // Getting the departure airport code and tickets with hashtmap.
        HashMap<String, Integer> hashtmap = new HashMap<>();
        for (Itinerary itinerary : itineraries.readItineraries()) {
            if (hashtmap.containsKey(itinerary.getDepartureAirportCode())) {
                int value = hashtmap.get(itinerary.getDepartureAirportCode());
                hashtmap.put(itinerary.getDepartureAirportCode(), value + 1);
            } else {
                hashtmap.put(itinerary.getDepartureAirportCode(), 1);
            }
        }
        try ( PrintWriter pw = new PrintWriter(file)) {
            // Printing all Departure Airport Code with total of itineraries each.
            hashtmap.forEach((k, v) -> pw.println("Itinerary per Departures is '" + k + "' has total of " + v + " itineraries."));

            HashMap<String, Integer> hashtmap2 = new HashMap<>();
            for (Itinerary itinerary : itineraries.readItineraries()) {
                if (hashtmap2.containsKey(itinerary.getDestinationAirportCode())) {
                    int value = hashtmap2.get(itinerary.getDestinationAirportCode());
                    hashtmap2.put(itinerary.getDestinationAirportCode(), value + 1);
                } else {
                    hashtmap2.put(itinerary.getDestinationAirportCode(), 1);
                }
            }

            // Printing all Departure Airport Code with total of itineraries each.
            hashtmap2.forEach((k, v) -> pw.println("Itinerary per Destination is '" + k + "' has total of " + v + " itineraries."));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * CustomersWithMostTicketsAndCostOfPurchasesToFile() method prints to file
     * the customers with most tickets sold and the customers with the most cost
     * of purchases.
     *
     * @param tickets all the tickets of TravelCompany.
     * @param customers all the customers of TravelCompany.
     */
    public static void customersWithMostTicketsAndCostOfPurchasesToFile(TicketRepository tickets, CustomerRepository customers) {
        File file = new File("CustomersWithMostTicketsAndCostOfPurchases.csv");

        // Creating Report.
        System.out.println("List of the customers who purchased the most tickets and the cost of purchases are being saves into file 'CustomersWithMostTicketsAndCostOfPurchases.csv'");

        // Getting the customer and tickets with hashtmap.
        HashMap<Integer, Integer> hashmap = collectionOfTicketsWithCustomers(tickets);

        try ( PrintWriter pw = new PrintWriter(file)) {
            // Finding maximum tickets sold from hashmap. And then iterating through hashtmap and printing all customers
            // with maximum amount of tickets sold to them.
            int maxValueInMap = (Collections.max(hashmap.values()));
            for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
                if (entry.getValue() == maxValueInMap) {
                    for (Customer customer : customers.readCustomers()) {
                        if (customer.getId() == entry.getKey()) {
                            pw.println("Customer with most tickets purchased is '" + customer.getName()
                                    + "' with total tickets of " + maxValueInMap + ".");
                        }
                    }
                }
            }

            // Getting the payment amount and tickets with hashmap2.
            HashMap<Integer, Integer> hashmap2 = collectionOfTicketsWithPaymentAmount(tickets);

            // Finding the maximum cost of tickets purchased by the customers. And then iterating through hashtmap2 and printing
            // all customers with maximum amount of cost of purchased of tickets.
            maxValueInMap = (Collections.max(hashmap2.values()));
            for (Map.Entry<Integer, Integer> entry : hashmap2.entrySet()) {
                if (entry.getValue() == maxValueInMap) {
                    for (Customer customer : customers.readCustomers()) {
                        if (customer.getId() == entry.getKey()) {
                            pw.println("Customer with most cost of purchased tickets is '" + customer.getName()
                                    + "' with total cost of " + maxValueInMap + ".");
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * CustomersWithNoPurchasedToFile() method prints to file a report of
     * the customers that made no purchased.
     *
     * @param tickets all the tickets of TravelCompany.
     * @param customers all the customers of TravelCompany.
     */
    public static void customersWithNoPurchasesToFile(TicketRepository tickets, CustomerRepository customers) {
        File file = new File("CustomersWithNoPurchases.csv");

        // Creating Report.
        System.out.println("List of the customers who have not purchased any tickets are being saves into file 'CustomersWithNoPurchases.csv'");

        try ( PrintWriter pw = new PrintWriter(file)){
            // Getting the customer and tickets with hashmap.
            HashMap<Integer, Integer> hashmap = collectionOfTicketsWithCustomers(tickets);

            // Checking which customerId is missing from our hashmap, and then we insert him on our list.
            for (Customer customer : customers.readCustomers()) {
                if (!hashmap.containsKey(customer.getId())) {
                    pw.println(customer.getName());
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
