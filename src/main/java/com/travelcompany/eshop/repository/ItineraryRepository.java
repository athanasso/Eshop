package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.exception.ItineraryException;
import com.travelcompany.eshop.model.Itinerary;
import java.util.List;

public interface ItineraryRepository {
    //CRUD

    boolean createItinerary(Itinerary itinerary) throws ItineraryException;

    Itinerary readItinerary(int id) throws ItineraryException;

    List<Itinerary> readItineraries();

    boolean updateItinerary(int id, int newBasicPrice) throws ItineraryException;

    boolean deleteItinerary(int id) throws ItineraryException;
}
