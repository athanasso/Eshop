package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.Itinerary;
import java.util.List;

public interface ItineraryRepository {
      //CRUD

    boolean createItinerary(Itinerary itinerary);

    Itinerary readItinerary(int id);

    List<Itinerary> readItineraries();

    boolean updateItinerary(int id, int newBasicPrice);

    boolean deleteItinerary(int id);
}
