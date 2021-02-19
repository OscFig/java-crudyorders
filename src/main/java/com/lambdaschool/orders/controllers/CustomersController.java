package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomersService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


//  The entry point for clients to access Customer data
@RestController
@RequestMapping("/customers")// This makes it so you dont have to type "/customers" for your endpoints.
public class CustomersController {

    //  Using the customers service to process customer data.
    @Autowired
    private CustomersService customersService;


    //  -----GET Controllers-----

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
    //  The param: (custid) is the primary key of the customer you are looking for.
    //  returns JSON of the customer you looked for with a OK status.
    //  Calls the Method in services/CustomersService (findCustomerById(long))
    @GetMapping(value = "/customer/{custid}", produces = "application/json")
    public ResponseEntity<?> getCustomerById(@PathVariable long custid) {
        Customer c = customersService.findCustomersById(custid);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //  Returns a list of customers whose name contains the given substring.
    //  Example: http://localhost:2019/customers/namelike/sun
    //  The param: (custname) is the substring in the customers' names that you are looking for.
    //  returns a JSON list of the customers found with the substring({custname}) and an OK Status.
    //  Calls the Method findByCustomerName(String) in services/Customers
    @GetMapping(value = "/namelike/{custname}", produces = "application/json")
    public ResponseEntity<?> findCustomerByName(@PathVariable String custname) {
        List<Customer> myCustomerList = customersService.findByCustomerName(custname);
        return new ResponseEntity<>(myCustomerList, HttpStatus.OK);
    }

    // -----POST Controller-----

    //  After generating anew primary key (long), saves a complete customer record including orders to the database.
    //  Example: POST  http://localhost:2019/customers/customer
    //  The param: (newCustomer) is a complete customer record including orders. The assigned agent must already exist.
    //  returns No Body, at status of CREATED, and in the location  header a link to the newly created customer record
    //  including the new customer record's primary key.
    //  Calls the Method CustomersService.save(Customer)
    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewCustomer(
            @Valid
            @RequestBody
                Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customersService.save(newCustomer);

        //  set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{custid}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(newCustomer,responseHeaders, HttpStatus.CREATED);
    }

    //  -----PUT Controller-----

    //  Given a complete Customer Object
    //  Given the customer primary key is in the customer table,
    //  replace the Customer record and orders referenced by this customer.
    //  Example: PUT http://localhost:2019/customers/customer/5 (or any {custid})
    //  The param: (updateCustomer) is a complete customer including all orders to be used to replace the customer.
    //  The param: (custid) is the primary key of the customer you  wish to replace the customer.
    //  Calls the Method CustomersService.save(Customer)
    @PutMapping(value = "/customer/{custid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> replaceCustomer(
            @Valid
            @RequestBody
                Customer updateCustomer,
            @PathVariable
                long custid)
    {
        updateCustomer.setCustcode(custid);
        updateCustomer = customersService.save(updateCustomer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    //  -----PATCH Controller-----

    //  Updates the customer record associated with the given id with the provided data.
    //  Only the provided fields are changed.
    //  Orders are added to the customer record. Deleting, modifying orders are different endpoints.
    //  Example: PATCH http://localhost:2019/customers/customer/5 (or any {custid})
    //  The param: (updateCustomer) is an object containing values for just the fields that are being updated.
    //  All other fields are left NULL
    //  The param: (custid) is the primary key of the customer you want to update.
    //  Returns a Status of OK.
    //  Calls the Method CustomersService.update(Customer, long)
    @PatchMapping(value = "/customer/{custid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateCustomer(
            @RequestBody
                Customer updateCustomer,
            @PathVariable
                long custid)
    {
        // This calls "update" in CustomersService
        customersService.update(updateCustomer, custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //  -----DELETE Controller-----

    //  Delete the given customer record with its associated orders
    //  Example: DELETE http://localhost:2019/customers/customer/5
    //  The param (custid) is the primary key (long) of the customer to delete
    //  Returns an OK Status if deletion is successful, otherwise a body in not returned
    //  Calls the Method CustomersService.delete(long)
    @DeleteMapping(value = "customer/{custid}")
    public ResponseEntity<?> deleteCustomerById(
            @PathVariable
                long custid)
    {
        customersService.delete(custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
