package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.ICityRepository;
import com.proyect.Human_Resources.models.City;

@Service
public class CityService {

    @Autowired
    private ICityRepository cityRepository; // Repository to handle database operations for cities

    public ArrayList<City> getCities() {
        return (ArrayList<City>) cityRepository.findAll(); // Returns a list of all cities
    }

    public City saveCity(City city) {
        return cityRepository.save(city); // Saves a new city and returns it
    }

    public Optional<City> getCityById(long id) {
        return cityRepository.findById(id); // Returns a city by its ID
    }

    public City updateCity(City city, long id) {
        City cityToUpdate = cityRepository.findById(id).get(); // Retrieves the city to update
        cityToUpdate.setName(city.getName()); // Updates the name of the city
        cityToUpdate.setState(city.getState()); // Updates the state of the city
        return cityRepository.save(cityToUpdate); // Saves the updated city and returns it
    }

    public boolean deleteCity(long id) {
        try {
            cityRepository.deleteById(id); // Deletes the city by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }
    
}
