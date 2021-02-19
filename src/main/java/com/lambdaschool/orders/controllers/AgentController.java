package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.services.AgentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents") // Routes will start with "/agents"
public class AgentController {

    //  Using the agent service to process agent data.
    @Autowired
    private AgentsService agentsService;

    //  Returns a single agent based off a restaurant id number.
    //  Example: <a href="http://localhost:2019/agent/12">http://localhost:2019/agent/12</a>
    //  The (agentid) Param is the primary key number of the agent we are looking for.
    //  Returns a JSON of the agent with an Ok Status.
    //  The Method (findAgentById(long agentid) can be found in AgentsService
    @GetMapping(value = "/agent/{agentid}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long agentid) {

        Agent a = agentsService.findAgentById(agentid);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    //  Delete the given agent if the agent is not assigned to a customer.
    //  Example: DELETE http://localhost:2019/agents/agent/12
    //  The param (agentid) is the primary key of the agent you want to delete
    //  Returns no Body and a Status of OK when the deletion is successful.
    //  This calls the AgentsService.deleteUnassigned(long)
    @DeleteMapping(value = "/unassigned/{agentid}")
    public ResponseEntity<?> deleteAgentById(
            @PathVariable
                long agentid)
    {
        agentsService.deleteUnassigned(agentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
