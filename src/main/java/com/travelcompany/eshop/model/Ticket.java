package com.travelcompany.eshop.model;

public class Ticket {
    private int id;
    private int passengerId;
    private int itineraryId;
    private String paymentMethod;
    private int paymentAmount;

    public Ticket(int id, int passengerId, int itineraryId, String paymentMethod, int paymentAmount) {
        this.id = id;
        this.passengerId = passengerId;
        this.itineraryId = itineraryId;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    
    
}
