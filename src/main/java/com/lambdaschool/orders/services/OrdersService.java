package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;

import java.util.List;

public interface OrdersService {

    //  A list of all orders with advanceamounts greater than 0.0;
    //  Returns a list of all orders with advanceamounts greater than 0.0. or an empty list if not found.
    List<Order> findOrderWithAdvanceAmount();

    //  Returns the order with the given primary key.
    //  The param (long id) is used to find the order you need.
    //  Returns the given order or throws an exception if not found.
    Order findOrdersById(long id);
}
