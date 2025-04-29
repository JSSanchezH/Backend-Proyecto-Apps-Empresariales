package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IEvaluationRepository;
import com.proyect.Human_Resources.models.Evaluation;

@Service // Indicates that this class is a service component in the Spring framework
public class EvaluationService {

    @Autowired
    private IEvaluationRepository evaluationRepository; // Injecting the IEvaluationRepository dependency

    public ArrayList<Evaluation> getEvaluations() {
        return (ArrayList<Evaluation>) evaluationRepository.findAll(); // Retrieves all evaluations records from the database
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation); // Saves the evaluation record to the database and returns the saved record
    }

    public ArrayList<Evaluation> saveEvaluations(ArrayList<Evaluation> evaluations) {
        return (ArrayList<Evaluation>) evaluationRepository.saveAll(evaluations); // Saves a list of evaluation records to the database and returns the saved records
    }

    public Optional<Evaluation> getEvaluationById(long id) {
        return evaluationRepository.findById(id); // Retrieves the evaluation record by its ID
    }

    public Evaluation updateEvaluation(Evaluation evaluation, long id) {
        Evaluation evaluationToUpdate = evaluationRepository.findById(id).get(); // Retrieves the evaluation record to update
        evaluationToUpdate.setEmployee(evaluation.getEmployee()); // Updates the employee associated with the evaluation
        evaluationToUpdate.setEvaluationDate(evaluation.getEvaluationDate()); // Updates the date of the evaluation
        evaluationToUpdate.setPunctuality(evaluation.getPunctuality()); // Updates the punctuality score of the employee in the evaluation
        evaluationToUpdate.setPerformance(evaluation.getPerformance()); // Updates the performance score of the employee in the evaluation
        evaluationToUpdate.setCourtesy(evaluation.getCourtesy()); // Updates the courtesy score of the employee in the evaluation
        evaluationToUpdate.setPrecision(evaluation.getPrecision()); // Updates the precision score of the employee in the evaluation
        evaluationToUpdate.setColaboration(evaluation.getColaboration()); // Updates the collaboration score of the employee in the evaluation
        evaluationToUpdate.setProactivity(evaluation.getProactivity()); // Updates the proactivity score of the employee in the evaluation
        return evaluationRepository.save(evaluationToUpdate); // Saves the updated evaluation record and returns it
    }

    public boolean deleteEvaluation(long id) {
        try {
            evaluationRepository.deleteById(id); // Deletes the evaluation record by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }
    
}
