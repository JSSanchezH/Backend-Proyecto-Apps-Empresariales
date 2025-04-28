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

import com.proyect.Human_Resources.models.City;
import com.proyect.Human_Resources.services.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {
    
    @Autowired
    private CityService cityService; // Service to handle business logic for cities

    // Define endpoints for CRUD operations on cities here

    @GetMapping
    public ArrayList<City> getAllCities() {
        return cityService.getCities(); // Returns a list of all cities
    }

    @PostMapping
    public City saveCity(@RequestBody City city) {
        return cityService.saveCity(city); // Saves a new city and returns it
    }

    @PostMapping("/batch")
    public ArrayList<City> saveCities(@RequestBody ArrayList<City> cities) {
        return cityService.saveCities(cities); // Saves a list of cities and returns it
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable("id") long id) {
        return cityService.getCityById(id); // Returns a city by its ID
    }

    @PutMapping("/{id}")
    public City updateCity(@RequestBody City city, @PathVariable("id") long id) {
        return cityService.updateCity(city, id); // Updates a city by its ID and returns the updated city
    }

    @DeleteMapping("/{id}")
    public String deleteCity(@PathVariable("id") long id) {
        boolean ok = cityService.deleteCity(id); // Deletes a city by its ID
        if (ok) {
            return "City deleted successfully"; // Returns success message
        } else {
            return "Error deleting city"; // Returns error message
        }
    }
}
