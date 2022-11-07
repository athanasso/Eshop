package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.exception.ItineraryException;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.repository.ItineraryRepository;
import java.util.ArrayList;
import java.util.List;

public class ItineraryRepositoryImpl implements ItineraryRepository{
    private final List<Itinerary> itineraries = new ArrayList<>();

    @Override
    public boolean createItinerary(Itinerary itinerary) throws ItineraryException {
        if (itinerary == null || itinerary.getDepartureAirportCode() == null
                || itinerary.getDestinationAirportCode() == null || itinerary.getDepartureDate() == null
                || itinerary.getAirline() == null || itinerary.getBasicPrice() <= 0)
            throw new ItineraryException("Some Itinerary data are missing!");
        itineraries.add(itinerary);
        return true;
    }

    @Override
    public Itinerary readItinerary(int id) throws ItineraryException {
        for (Itinerary itinerary : itineraries)
            if (itinerary.getId() == id)
                return itinerary;
        throw new ItineraryException("Itinerary doesn't exist!");
    }

    @Override
    public List<Itinerary> readItineraries() {
        List<Itinerary> returnItineraries = new ArrayList<>();
        returnItineraries.addAll(itineraries);
        return returnItineraries;
    }

    @Override
    public boolean updateItinerary(int id, int newBasicPrice) throws ItineraryException {
        Itinerary itinerary = readItinerary(id);
        if (itinerary == null)
            throw new ItineraryException("Itinerary doesn't exist!");
        itinerary.setBasicPrice(newBasicPrice);
        return true;
    }

    @Override
    public boolean deleteItinerary(int id) throws ItineraryException {
        Itinerary itinerary = readItinerary(id);
        if (itinerary == null)
            throw new ItineraryException("Itinerary doesn't exist!");
        itineraries.remove(itinerary);
        return false;
    }
}
