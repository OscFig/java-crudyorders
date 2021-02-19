package com.lambdaschool.orders.repositories;

import com.lambdaschool.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Long> {
    //  The CRUD Repository connecting Order to rest of the application.
    List<Order> findAllByAdvanceamountGreaterThan(Double amount);
}
