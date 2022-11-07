package com.travelcompany.eshop.model;

import com.travelcompany.eshop.exception.ItineraryMissingAirportCodeException;

public class Itinerary {
    private int id;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String airline;
    private int basicPrice;

    public Itinerary(int id, String departureAirportCode, String destinationAirportCode, String departureDate, String airline, int basicPrice) throws ItineraryMissingAirportCodeException{
        this.id = id;
        setDepartureAirportCode(departureAirportCode);
        setDestinationAirportCode(destinationAirportCode);
        this.departureDate = departureDate;
        this.airline = airline;
        this.basicPrice = basicPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) throws ItineraryMissingAirportCodeException{
        if (departureAirportCode == null)
            throw new ItineraryMissingAirportCodeException("The departure airport code is missing!");
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) throws ItineraryMissingAirportCodeException{
        if (destinationAirportCode == null)
            throw new ItineraryMissingAirportCodeException("The destination airport code is missing!");
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(int basicPrice) {
        this.basicPrice = basicPrice;
    }
    
    
}
