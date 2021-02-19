package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;

public interface AgentsService {

    //  Returns the Agent with the given primary key.
    Agent findAgentById(long id);

    //  Deletes an agent if the given agent has no customers.
    //  Throws exception if the agent primary key is not found or the agent has customers.
    void deleteUnassigned(long agentid);

}
