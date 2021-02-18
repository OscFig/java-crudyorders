package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "agentsService")
public class AgentsServiceImpl implements AgentsService {

    @Autowired
    private AgentsRepository agentrepos;

    @Override
    public Agent findAgentById(long id) throws EntityNotFoundException {
        return agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent Id " + id + " Not Found"));
    }

}
