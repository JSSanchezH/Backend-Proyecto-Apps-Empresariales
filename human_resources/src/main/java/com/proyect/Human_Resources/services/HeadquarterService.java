package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IHeadquarterRepository;
import com.proyect.Human_Resources.models.Headquarter;

@Service
public class HeadquarterService {
 
    @Autowired
    private IHeadquarterRepository headquarterRepository; // Repository to handle database operations for headquarters

    public ArrayList<Headquarter> getHeadquarters() {
        return (ArrayList<Headquarter>) headquarterRepository.findAll(); // Returns a list of all headquarters
    }

    public Headquarter saveHeadquarter(Headquarter headquarter) {
        return headquarterRepository.save(headquarter); // Saves a new headquarter and returns it
    }

    public ArrayList<Headquarter> saveHeadquarters(ArrayList<Headquarter> headquarters) {
        return (ArrayList<Headquarter>) headquarterRepository.saveAll(headquarters); // Saves a list of headquarters and returns it
    }

    public Optional<Headquarter> getHeadquarterById(long id) {
        return headquarterRepository.findById(id); // Returns a headquarter by its ID
    }

    public Headquarter updateHeadquarter(Headquarter headquarter, long id) {
        Headquarter headquarterToUpdate = headquarterRepository.findById(id).get(); // Retrieves the headquarter to update
        headquarterToUpdate.setName(headquarter.getName()); // Updates the name of the headquarter
        headquarterToUpdate.setPhone(headquarter.getPhone()); // Updates the phone number of the headquarter
        headquarterToUpdate.setCity(headquarter.getCity()); // Updates the city of the headquarter
        return headquarterRepository.save(headquarterToUpdate); // Saves the updated headquarter and returns it
    }

    public boolean deleteHeadquarter(long id) {
        try {
            headquarterRepository.deleteById(id); // Deletes the headquarter by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }
}
