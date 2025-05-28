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

import com.proyect.Human_Resources.models.Country;
import com.proyect.Human_Resources.services.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ArrayList<Country> getCountries() {
        return countryService.getCountries();
    }

    @PostMapping
    public Country saveCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @PostMapping("/batch")
    public ArrayList<Country> saveCountries(@RequestBody ArrayList<Country> countries) {
        return countryService.saveCountries(countries);
    }

    @GetMapping("/{id}")
    public Optional<Country> getCountryById(@PathVariable("id") long id) {
        return countryService.getCountryById(id);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@RequestBody Country country, @PathVariable("id") long id) {
        return countryService.updateCountry(country, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCountry(@PathVariable("id") long id) {
        boolean ok = countryService.deleteCountry(id);
        if (ok) {
            return "Country deleted successfully";
        } else {
            return "Error deleting country";
        }
    }

    @GetMapping("/continent/{id}")
    public ArrayList<Country> getCountriesByContinentId(@PathVariable("id") Long id) {
        return countryService.getCountriesByContinentId(id);
    }
}
