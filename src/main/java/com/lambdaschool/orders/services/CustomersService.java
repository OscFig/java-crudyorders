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

    //  Given a complete customer object, save the customer in the database.
    //  If a primary key is provided, the record is completely replaced.
    //  If no primary key is provided, one is automatically generated and the record is added to the database.
    //  The param (customer) is the customer object to be saved
    //  Returns the save customer object including any automatically generated fields
    Customer save(Customer customer);

    //  Updates the provided fields in the customer record referenced by the primary key.
    //  Regarding orders, this process only allows adding order. Deleting and editing those lists...
    //  is done through separate endpoints.
    //  The param (customer) is just the customer fields to be updated.
    //  The param (id) is the primary key (long) of the customer to update
    //  Returns the complete customer object that got updated
    Customer update(Customer customer, long id);

    //  Deletes the customer record and their orders from the database based off of the provided primary key
    //  The param (id) is the primary key (long) of the customer to delete
    void delete(long id);
}
