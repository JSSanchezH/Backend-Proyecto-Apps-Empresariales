package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.ICountryRepository;
import com.proyect.Human_Resources.models.Country;

@Service
public class CountryService {

    @Autowired
    private ICountryRepository countryRepository;

    public ArrayList<Country> getCountries() {
        return (ArrayList<Country>) countryRepository.findAll();
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public Optional<Country> getCountryById(long id) {
        return countryRepository.findById(id);
    }


    public Country updateCountry(Country country, long id) {
        Country countryToUpdate = countryRepository.findById(id).get();
        countryToUpdate.setName(country.getName());
        countryToUpdate.setContinent(country.getContinent());
        return countryRepository.save(countryToUpdate);
    }

    public boolean deleteCountry(long id) {
        try {
            countryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
}
