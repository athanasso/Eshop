# Individual Project for Java Bootcamp _"TravelCompany"_ by Codehub

---
### Description: 
The TravelCompany program implements the below:
* User Interaction - Simple form of reading and writing at the console.
* Data Modeling - Implementation of necessary domain classes for the core modeling of the system.
* Ordering and discount policy - Based on the system’s requirements, customers are categorized into individual and business. They can buy tickets by paying either with cash or by using a credit card.
  * You need to make sure that the system distinguishes between the purchase methods and customer categories, because the following discounts will apply upon the basic price when buying a ticket:
    * Business customers get a discount of 10%.
    * Individual customers are subject to surcharge of 20% for all services.
    * There is a 10% discount when the customer pays by credit card.
    * There is no discount when the customer pays by cash. \
  * The discounts are cumulative. For example, a business customer purchasing a ticket using his/her credit card will receive a price reduction of 20% (10% as a business customer discount + 10% for paying with a credit card).
* Exception Handling - Design custom exceptions:
  * when creating a customer: the email of the customer is <whatever>@travelcompany.com.
  * when issuing a ticket: 
    * the requested itinerary does not exist.
    * the given customer code does not exist.
  * when creating an itinerary: the airport code does not exist.
* Reporting - Based on the purchases of each customer, the system must support the following reporting:
  * List of the total number and cost of tickets for all customers.
  * List of the total offered itineraries per destination and departure.
  * List of the customers who purchased the most tickets and the number of purchases.
  * List of the customers who have not purchased any tickets.
* Extra:
  * Collection reports saved in files.

---
### Requirements
TravelCompany requires the following to run:
* Maven
* JDK 17 or newer.
