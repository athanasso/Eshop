/**
 * The UseCase class generates Customers, Itineraries, Tickets and lists of reports for the user.
 *
 * @author Emmanouil Athanasopoulos
 */

package com.travelcompany.eshop.usecases;

import static com.travelcompany.eshop.dtos.ConsoleReports.customersWithMostTicketsAndCostOfPurchases;
import static com.travelcompany.eshop.dtos.ConsoleReports.customersWithNoPurchases;
import static com.travelcompany.eshop.dtos.ConsoleReports.totalCostAndNumberOfTickets;
import static com.travelcompany.eshop.dtos.ConsoleReports.totalItinerariesPerDestinationAndDeparture;
import static com.travelcompany.eshop.dtos.FileReports.customersWithMostTicketsAndCostOfPurchasesToFile;
import static com.travelcompany.eshop.dtos.FileReports.customersWithNoPurchasesToFile;
import static com.travelcompany.eshop.dtos.FileReports.totalCostAndNumberOfTicketsToFile;
import static com.travelcompany.eshop.dtos.FileReports.totalItinerariesPerDestinationAndDepartureToFile;
import com.travelcompany.eshop.model.*;
import com.travelcompany.eshop.repository.*;
import com.travelcompany.eshop.util.DataImport;

import java.util.*;

public class UseCase {
    
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * BusinessCase() will generate Customers from generateCustomers() method, Itineraries from
     * generateItineraries() method and Tickets from ticketsService() method all located in DataImport class.
     * After the generation of Customers, Itineraries and Tickets. The BusinessCase() method will continue with
     * the Reporting part or exit the program.
     * 1 ->  To get a list of total number and cost of tickets for all customers.
     * 2 ->  To get a list of the total offered itineraries per destination and departure.
     * 3 ->  To get a list of the customers who purchased the most tickets and number of purchases.
     * 4 ->  To get a list of the customers who have not purchased any tickets.
     * 5 ->  To get all the above lists.
     * 0 ->  To exit the program.
     * This method check for exceptions for a bad user input, and it handles it.
     */
    private static void BusinessCase() {
        ItineraryRepository itineraries = DataImport.generateItineraries();
        CustomerRepository customers = DataImport.generateCustomers();
        TicketRepository tickets = DataImport.generateTickets();
        // Printing the Tickets.
        for (Ticket ticket : tickets.readTickets())
            System.out.println("Ticket" + ticket.getId() + " with PassengerId:'" + ticket.getPassengerId() + "' with ItineraryId:'"
                    + ticket.getItineraryId() + "' with PaymentMethod:'" + ticket.getPaymentMethod()
                    + "' the Total Cost is:'" + ticket.getPaymentAmount() + "'.");

        // We check how the user want to continue. Handling the user's input if he doesn't give the correct input.
        int userChoice = Integer.MIN_VALUE;
        do {
            System.out.println("\nChoose how you want to continue.");
            System.out.println("\tPress 1: To get a list of total number and cost of tickets for all customers.");
            System.out.println("\tPress 2: To get a list of the total offered itineraries per destination and departure.");
            System.out.println("\tPress 3: To get a list of the customers who purchased the most tickets and number of purchases.");
            System.out.println("\tPress 4: To get a list of the customers who have not purchased any tickets.");
            System.out.println("\tPress 5: To get all the above lists.");
            System.out.println("\tPress 6: To get all the above lists in files.");
            System.out.println("\tPress 0: To exit.");
            try {
                userChoice = scanner.nextInt();
            }
            catch (InputMismatchException ex) {
                String badInput = scanner.next();
                System.out.println("Bad input: '" + badInput + "' Please try again.\n");
            }
        } while (userChoice < 0 || userChoice > 6);

        // Based on the user choose we report back or exit the program.
        switch (userChoice) {

            case 1 -> totalCostAndNumberOfTickets(tickets);
            case 2 -> totalItinerariesPerDestinationAndDeparture(itineraries);
            case 3 -> customersWithMostTicketsAndCostOfPurchases(tickets, customers);
            case 4 -> customersWithNoPurchases(tickets, customers);
            case 5 -> {
                totalCostAndNumberOfTickets(tickets);
                totalItinerariesPerDestinationAndDeparture(itineraries);
                customersWithMostTicketsAndCostOfPurchases(tickets, customers);
                customersWithNoPurchases(tickets, customers);
            }
            case 6 -> {
                totalCostAndNumberOfTicketsToFile(tickets);
                totalItinerariesPerDestinationAndDepartureToFile(itineraries);
                customersWithMostTicketsAndCostOfPurchasesToFile(tickets, customers);
                customersWithNoPurchasesToFile(tickets, customers);
            }
            case 0 -> System.out.println("Exiting...");
        }
    }

    /**
     * menuInterface() will start our program with a welcome note of the TravelCompany, and then
     * it will wait for the user to select preferred action or exit the program.
     * Actions of selection are:
     *  1  ->  To generate Customers, Itineraries and Tickets.
     *  0  ->  To exit the program.
     *  This method check for exceptions for a bad user input, and it handles it.
     */
    public static void menuInterface() {
        // Starting the program.
        System.out.println("\n\t\t\t--- Welcome to TravelCompany ---");

        // We check how the user want to start. Handling the user's input if he doesn't give the correct input.
        int userChoice = Integer.MIN_VALUE;
        do {
            System.out.println("\t\t ___________________________________________");
            System.out.println("\t\t|                  Menu                     |");
            System.out.println("\t\t|                                           |");
            System.out.println("\t\t|       Press 1: To Generate Tickets.       |");
            System.out.println("\t\t|       Press 0: To exit.                   |");
            System.out.println("\t\t|___________________________________________|");
            try {
                userChoice = scanner.nextInt();
            }
            catch (InputMismatchException ex) {
                String badInput = scanner.next();
                System.out.println("Bad input: '" + badInput + "' Please try again.\n");
            }
        } while (userChoice < 0 || userChoice > 1);

        // Based on the user choose we start generating or exiting the program.
        switch (userChoice) {

            case 1 -> BusinessCase();
            case 0 -> System.out.println("Exiting...");
        }
    }
}