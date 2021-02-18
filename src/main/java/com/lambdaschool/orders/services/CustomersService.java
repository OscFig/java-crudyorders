package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;

import java.util.List;

public interface CustomersService {

    // Returns a list of all Customers with their orders and agents.
    List<Customer> findAllCustomers();

    //  Returns a list of all Customers whose name contains the given substring.
    List<Customer> findByCustomerName(String custname);

    //  Returns the customer with the given primary key.
    Customer findCustomersById(long id);
}
