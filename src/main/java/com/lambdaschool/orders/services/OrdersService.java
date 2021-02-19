package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrdersService {

    //  A list of all orders with advanceamounts greater than 0.0;
    //  Returns a list of all orders with advanceamounts greater than 0.0. or an empty list if not found.
    List<Order> findOrderWithAdvanceAmount();

    //  Returns the order with the given primary key.
    //  The param (long id) is used to find the order you need.
    //  Returns the given order or throws an exception if not found.
    Order findOrdersById(long id);

    //  Given a complete order object,save the order in the database.
    //  If a primary key is provided, the record is completely replaced
    //  If no primary key is provided, one is automatically generated and the record is added to the database
    //  The param (order)  is the order object to ve saved
    //  Returns the save order object including any automatically generated fields
    Order save(Order order);

    //  Deletes the order record from the database based off of the provided primary key
    //  The param (id) is the primary key of the order to delete
    void delete(long id);
}
