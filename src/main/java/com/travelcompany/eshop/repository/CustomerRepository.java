package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.Customer;

import java.util.List;

public interface CustomerRepository {
    //CRUD

    boolean createCustomer(Customer customer);

    Customer readCustomer(int id);

    List<Customer> readCustomers();

    boolean updateCustomer(int id, String newAddress);

    boolean deleteCustomer(int id);
}