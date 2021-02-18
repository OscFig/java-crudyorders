package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    //  Returns a single order based off of an ordernum
    //  Example: <a href="http://localhost:2019/orders/order/50">http://localhost:2019/orders/order/50</a>
    //  The param (ordernum) is for the order you want.
    //  This returns a JSON of the order with a Status of OK.
    @GetMapping(value = "/order/{ordernum}", produces = {"application/json"})
    public ResponseEntity<?> getOrderByNumber(@PathVariable long ordernum) {

        Order o = ordersService.findOrdersById(ordernum);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
