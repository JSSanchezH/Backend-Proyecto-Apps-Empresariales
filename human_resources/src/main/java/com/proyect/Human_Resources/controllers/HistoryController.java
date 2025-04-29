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

import com.proyect.Human_Resources.models.History;
import com.proyect.Human_Resources.services.HistoryService;

@RestController
@RequestMapping("/history")
// This annotation indicates that this class is a REST controller and will handle HTTP requests
public class HistoryController {

    @Autowired
    private HistoryService historyService; // Injecting the HistoryService dependency

    @GetMapping
    public ArrayList<History> getHistories() {
        return historyService.getHistories(); // Retrieves a list of all history records
    }

    @PostMapping
    public History saveHistory(@RequestBody History history) {
        return historyService.saveHistory(history); // Saves a new history record and returns it
    }

    @PostMapping("/batch")
    public ArrayList<History> saveHistories(@RequestBody ArrayList<History> histories) {
        return historyService.saveHistories(histories); // Saves a list of history records and returns them
    }

    @GetMapping("/{id}")
    public Optional<History> getHistoryById(@PathVariable("id")long id) {
        return historyService.getHistoryById(id); // Retrieves a history record by its ID
    }

    @PutMapping("/{id}")
    public History updateHistory(@RequestBody History history,@PathVariable("id") long id) {
        return historyService.updateHistory(history, id); // Updates an existing history record and returns it
    }

    @DeleteMapping("/{id}")
    public String deleteHistory(@PathVariable("id") long id) {
        boolean ok = historyService.deleteHistory(id); // Deletes a history record by its ID
        if (ok) {
            return "History deleted successfully"; // Returns success message
        } else {
            return "Error deleting history"; // Returns false if there was an error during deletion
        }
    }
    
}
