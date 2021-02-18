package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;

public interface AgentsService {

    //  Returns the Agent with the given primary key.
    Agent findAgentById(long id);

}
