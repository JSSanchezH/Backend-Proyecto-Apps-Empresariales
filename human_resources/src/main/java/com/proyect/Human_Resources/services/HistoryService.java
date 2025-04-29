package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IHistoryRepository;
import com.proyect.Human_Resources.models.History;

@Service // Indicates that this class is a service component in the Spring framework
public class HistoryService {

    @Autowired 
    private IHistoryRepository historyRepository; // Injecting the IHistoryRepository dependency

    // Method to save a history record

    public ArrayList<History> getHistories() {
        return (ArrayList<History>) historyRepository.findAll(); // Saves the history record to the database and returns the saved record
    }

    public History saveHistory(History history) {
        return historyRepository.save(history); // Saves the history record to the database and returns the saved record
    }

    public ArrayList<History> saveHistories(ArrayList<History> histories) {
        return (ArrayList<History>) historyRepository.saveAll(histories); // Saves a list of history records to the database and returns the saved records
    }
    
    // Method to retrieve a history record by its ID
    public Optional<History> getHistoryById(long id) {
        return historyRepository.findById(id); // Retrieves the history record by its ID
    }

    // Method to update a history record

    public History updateHistory(History history, long id) {
        History historyToUpdate = historyRepository.findById(id).get(); // Retrieves the history record to update
        historyToUpdate.setEndDate(history.getEndDate()); // Updates the end date of the history record
        historyToUpdate.setReason(history.getReason()); // Updates the reason of the history record
        return historyRepository.save(historyToUpdate); // Saves the updated history record and returns it
    }

    public boolean deleteHistory(long id) {
        try {
            historyRepository.deleteById(id); // Deletes the history record by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }

}
