package com.proyect.Human_Resources.controllers;

import java.util.ArrayList;
import java.util.List;
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

import com.proyect.Human_Resources.models.Continent;
import com.proyect.Human_Resources.services.ContinentService;

@RestController
@RequestMapping("/continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping
    public ArrayList<Continent> getContinents() {
        return continentService.getContinents();
    }

    @PostMapping
    public Continent saveContinent(@RequestBody Continent continent) {
        return continentService.saveContinent(continent);
    }

    @PostMapping("/batch")
    public List<Continent> saveContinents(@RequestBody List<Continent> continents) {
        return continentService.saveContinents(continents);
    }

    @GetMapping(path = "/{id}")
    public Optional<Continent> getContinentById(@PathVariable("id") long id) {
        return continentService.getContinentById(id);
    }

    @PutMapping(path = "/{id}")
    public Continent updateContinent(@RequestBody Continent continent, @PathVariable("id") long id) {
        return continentService.updateContinent(continent, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteContinent(@PathVariable("id") long id) {
        boolean ok = continentService.deleteContinent(id);
        if (ok) {
            return "Continent deleted successfully";
        } else {
            return "Error deleting continent";
        }
    }
}
