package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    //  Returns all orders with their customers that have an advanceamount greater than 0.
    //  Example: http://localhost:2019/orders/advanceamount
    //  Returns JSON List of all orders with their customers that have an advanceamount greater than 0.
    @GetMapping(value = "/advanceamount")
    public ResponseEntity<?> getOrdersWithAdvanceAmount(){
        List<Order> myOrderList = ordersService.findOrderWithAdvanceAmount();
        return new ResponseEntity<>(myOrderList, HttpStatus.OK);
    }

    //  Returns a single order based off of an ordernum
    //  Example: <a href="http://localhost:2019/orders/order/50">http://localhost:2019/orders/order/50</a>
    //  The param (ordernum) is for the order you want.
    //  This returns a JSON of the order with a Status of OK.
    @GetMapping(value = "/order/{ordernum}", produces = {"application/json"})
    public ResponseEntity<?> getOrderByNumber(@PathVariable long ordernum) {

        Order o = ordersService.findOrdersById(ordernum);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    //  After generating a new primary key (long), saves a complete order record to the database.
    //  Example: POST http://localhost:2019/orders/order
    //  The param (newOrder) makes a complete new order record to be added to the database.
    //  Customer must already exist.
    //  Returns no body, a Status of CREATED, and in the location header, a link to the newly created order record.
    //  including the new order record's primary key.
    //  This calls the Method OrdersService.save(Order)
    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewOrder(
            @Valid
            @RequestBody
                Order newOrder)
    {
        newOrder.setOrdnum(0);
        newOrder = ordersService.save(newOrder);

        //  set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordernum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //  Given a complete Order Object and...
    //  Given the order primary key is in the order table,
    //  replace the order record referenced by this order id
    //  Example: PUT http://localhost:2019/orders/order/19
    //  The param (updateOrder) is a complete order record to replace the targeted one.
    //  The param (id) is the primary key (long) of the Order to be replaced
    //  Returns a Status code of OK.
    //  This calls the Method OrdersService.save(Order)
    @PutMapping(value = "/order/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateOrder(
            @RequestBody
                Order updateOrder,
            @PathVariable
                long id)
    {
        updateOrder.setOrdnum(id);
        ordersService.save(updateOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //  Delete the given order record.
    //  Example: DELETE http://localhost:2019/orders/order/19
    //  The param (id) is the primary key of the order to be deleted.
    //  Returns no body and a Status of OK if the deletion was successful.
    //  This calls the Method OrdersService.delete(long)
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteCustomerById(
            @PathVariable
                long id)
    {
        ordersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
