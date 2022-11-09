package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.Category;
import com.travelcompany.eshop.exception.CustomerEmailException;

public class Customer {

    private int id;
    private String name;
    private String email;
    private String address;
    private String nationality;
    private Category category;

    public Customer(int id, String name, String email, String address, String nationality, Category category) throws CustomerEmailException {
        this.id = id;
        this.name = name;
        setEmail(email);
        this.address = address;
        this.nationality = nationality;
        this.category = category;
    }

    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws CustomerEmailException {
        if (email.contains("travelcompany.com")) {
            throw new CustomerEmailException("Employees can't be customers!");
        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
