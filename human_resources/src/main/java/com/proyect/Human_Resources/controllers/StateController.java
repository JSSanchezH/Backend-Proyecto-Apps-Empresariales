package com.proyect.Human_Resources.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.Human_Resources.models.State;
import com.proyect.Human_Resources.services.StateService;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService; // Service to handle business logic for states

    // Define endpoints for CRUD operations on states here

    @GetMapping
    public ArrayList<State> getAllStates() {
        return stateService.getStates(); // Returns a list of all states
    }

    @PostMapping
    public State createState(@RequestBody State state) {
        return stateService.saveState(state); // Saves a new state and returns it
    }

    @GetMapping("/{id}")
    public Optional<State> getStateById(@PathVariable("id") long id) {
        return stateService.getStateById(id); // Returns a state by its ID
    }

    @PutMapping("/{id}")
    public State updateState(@RequestBody State state, @PathVariable("id") long id) {
        return stateService.updateState(state, id); // Updates a state by its ID and returns the updated state
    }

    @DeleteMapping("/{id}")
    public String deleteState(@PathVariable("id") long id) {
        boolean ok = stateService.deleteState(id); // Deletes a state by its ID
        if (ok) {
            return "State deleted successfully"; // Returns success message
        } else {
            return "Error deleting state"; // Returns error message
        }
    }
}
