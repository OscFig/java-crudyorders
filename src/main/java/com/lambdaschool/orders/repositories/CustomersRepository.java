package com.lambdaschool.orders.repositories;

import com.lambdaschool.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

    //  List of customer whose name contains a given substring. Case insensitive search.
    //  The param (name) is the substring to search names for.
    //  Returns a list of customers whose name contains the given substring.

    List<Customer> findByCustnameContainingIgnoringCase(String name);


    //  Returns the first associated customer for the given agent.
    //  If no customers are associated with this agent, returns null.
    //  The param (agentid) is the agent you are seeking a customer for
    //  Returns the first customer record who is associated with this agent.
    //  if no customers are associated with this agent, returns null.

    Customer findFirstByAgent_Agentcode(long agentid);
}
