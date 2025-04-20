package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IContinentRepository;
import com.proyect.Human_Resources.models.Continent;

@Service
public class ContinentService {

    @Autowired
    private IContinentRepository continentRepository;

    public ArrayList<Continent> getContinents() {
        return (ArrayList<Continent>) continentRepository.findAll();
    }

    public Continent saveContinent(Continent continent) {
        return continentRepository.save(continent);
    }

    public Optional<Continent> getContinentById(long id) {
        return continentRepository.findById(id);
    }

    public Continent updateContinent(Continent continent, long id){
        Continent continentToUpdate = continentRepository.findById(id).get();
        continentToUpdate.setName(continent.getName());
        return continentRepository.save(continentToUpdate);
    }

    public boolean deleteContinent(long id) {
        try {
            continentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
