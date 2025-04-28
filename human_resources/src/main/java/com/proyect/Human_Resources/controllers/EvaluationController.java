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

import com.proyect.Human_Resources.models.Evaluation;
import com.proyect.Human_Resources.services.EvaluationService;

@RestController
@RequestMapping("/evaluations") // Base URL for the EvaluationController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService; // Injecting the EvaluationService dependency

    @GetMapping
    public ArrayList<Evaluation> getEvaluations() {
        return evaluationService.getEvaluations(); // Retrieves all evaluations records from the database
    }

    @PostMapping
    public Evaluation saveEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.saveEvaluation(evaluation); // Saves the evaluation record to the database and returns the saved record
    }

    @PostMapping("/batch")
    public ArrayList<Evaluation> saveEvaluations(@RequestBody ArrayList<Evaluation> evaluations) {
        return evaluationService.saveEvaluations(evaluations); // Saves a list of evaluation records to the database and returns the saved records
    }

    @GetMapping("/{id}")
    public Optional<Evaluation> getEvaluationById(@PathVariable long id) {
        return evaluationService.getEvaluationById(id); // Retrieves the evaluation record by its ID
    }

    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@RequestBody Evaluation evaluation, @PathVariable long id) {
        return evaluationService.updateEvaluation(evaluation, id); // Updates the evaluation record and returns the updated record
    }

    @DeleteMapping("/{id}")
    public String deleteEvaluation(@PathVariable long id) {
        boolean isDeleted = evaluationService.deleteEvaluation(id); // Deletes the evaluation record by its ID
        if (isDeleted) {
            return "Evaluation deleted successfully"; // Returns success message if deletion was successful
        } else {
            return "Error deleting evaluation"; // Returns error message if there was an error during deletion
        }
    }
    
    
}
