package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IUserCompanyRepository;
import com.proyect.Human_Resources.models.UserCompany;

@Service
public class UserCompanyService {

    private String generateApiKey() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 30);
    }

    @Autowired
    private IUserCompanyRepository userCompanyRepository; // Repository to handle database operations for UserCompany

    public ArrayList<UserCompany> getUserCompanies() {
        return (ArrayList<UserCompany>) userCompanyRepository.findAll(); // Returns a list of all UserCompany records
    }

    public UserCompany saveUserCompany(UserCompany userCompany) {
        userCompany.setApiKey(generateApiKey());
        return userCompanyRepository.save(userCompany); // Saves a new UserCompany record and returns it
    }

    public ArrayList<UserCompany> saveUserCompanies(ArrayList<UserCompany> userCompanies) {
        return (ArrayList<UserCompany>) userCompanyRepository.saveAll(userCompanies); // Saves a list of UserCompany
                                                                                      // records and returns it
    }

    public Optional<UserCompany> getUserCompanyById(long id) {
        return userCompanyRepository.findById(id); // Returns a UserCompany record by its ID
    }

    public UserCompany updateUserCompany(UserCompany userCompany, long id) {
        UserCompany userCompanyToUpdate = userCompanyRepository.findById(id).get(); // Retrieves the UserCompany record
                                                                                    // to update
        userCompanyToUpdate.setCompany(userCompany.getCompany());
        userCompanyToUpdate.setUserName(userCompany.getUserName()); // Updates the username
        userCompanyToUpdate.setPassword(userCompany.getPassword()); // Updates the password
        return userCompanyRepository.save(userCompanyToUpdate); // Saves the updated UserCompany record and returns it
    }

    public boolean deleteUserCompany(long id) {
        try {
            userCompanyRepository.deleteById(id); // Deletes the UserCompany record by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }
}
