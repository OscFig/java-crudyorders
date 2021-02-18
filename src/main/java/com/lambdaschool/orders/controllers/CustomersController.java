package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//  The entry point for clients to access Customer data
@RestController
@RequestMapping("/customers")// This makes it so you dont have to type "/customers" for your endpoints.
public class CustomersController {

    //  Using the customers service to process customer data.
    @Autowired
    private CustomersService customersService;


    //  Returns a list of all Customers with their orders.
    //  Example: http://localhost:2019/customers/orders
    //  returns JSON list of all Customers with their orders with a status of Ok.
    //  Calls the Method in services/CustomersService (findAllCustomers())
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomers() {
        List<Customer> myCustomers = customersService.findAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    //  Returns a single customer based off a customer id number.
    //  Example: http://localhost:2019/customers/customer/5
    //  The param (custid) is the primary key of the customer you are looking for.
    //  returns JSON of the customer you looked for with a OK status.
    //  Calls the Method in services/CustomersService (findCustomerById(long))
    @GetMapping(value = "/customer/{custid}", produces = "application/json")
    public ResponseEntity<?> getCustomerById(@PathVariable long custid){
        Customer c = customersService.findCustomersById(custid);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //  Returns a list of customers whose name contains the given substring.
    //  Example: http://localhost:2019/customers/namelike/sun
    //  The param (custname) is the substring in the customers' names that you are looking for.
    //  returns a JSON list of the customers found with the substring({custname}) and an OK Status.
    //  Calls the Method findByCustomerName(String) in services/Customers
    @GetMapping(value = "/namelike/{custname}", produces = "application/json")
    public ResponseEntity<?> findCustomerByName(@PathVariable String custname) {
        List<Customer> myCustomerList = customersService.findByCustomerName(custname);
        return new ResponseEntity<>(myCustomerList, HttpStatus.OK);
    }

}
