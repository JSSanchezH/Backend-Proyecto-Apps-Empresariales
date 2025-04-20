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

import com.proyect.Human_Resources.models.Absence_Type;
import com.proyect.Human_Resources.services.Absence_TypeService;

@RestController
@RequestMapping("/absence_types")
public class Absence_TypeController {

    @Autowired
    private Absence_TypeService absenceTypeService;

    @GetMapping
    public ArrayList<Absence_Type> getAbsenceTypes() {
        return absenceTypeService.getAbsenceTypes();
    }

    @PostMapping
    public Absence_Type saveAbsenceType(@RequestBody Absence_Type absenceType) {
        return absenceTypeService.saveAbsenceType(absenceType);
    }

    @GetMapping("/{id}")
    public Optional<Absence_Type> getAbsenceTypeById(@PathVariable("id") long id) {
        return absenceTypeService.getAbsenceTypeById(id);
    }

    @PutMapping("/{id}")
    public Absence_Type updateAbsenceType(@RequestBody Absence_Type absenceType, @PathVariable("id") long id) {
        return absenceTypeService.updateAbsenceType(absenceType, id);
    }

    @DeleteMapping("/{id}")
    public String deleteAbsenceType(@PathVariable("id") long id) {
        boolean ok = absenceTypeService.deleteAbsenceType(id);
        if (ok) {
            return "Absence type deleted successfully";
        } else {
            return "Error deleting absence type";
        }
    }

    
    
}
