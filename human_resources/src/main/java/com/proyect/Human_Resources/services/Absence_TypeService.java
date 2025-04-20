package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IAbsenceTypeRepository;
import com.proyect.Human_Resources.models.Absence_Type;

@Service
public class Absence_TypeService {

    @Autowired
    private IAbsenceTypeRepository absenceTypeRepository;

    public ArrayList <Absence_Type> getAbsenceTypes() {
        return (ArrayList<Absence_Type>) absenceTypeRepository.findAll();
    }

    public Absence_Type saveAbsenceType(Absence_Type absenceType) {
        return absenceTypeRepository.save(absenceType);
    }

    public Optional<Absence_Type> getAbsenceTypeById(long id) {
        return absenceTypeRepository.findById(id);
    }

    public Absence_Type updateAbsenceType(Absence_Type absenceType, long id) {
        Absence_Type absenceTypeToUpdate = absenceTypeRepository.findById(id).get();
        absenceTypeToUpdate.setName(absenceType.getName());
        return absenceTypeRepository.save(absenceTypeToUpdate);
    }

    public boolean deleteAbsenceType(long id) {
        try {
            absenceTypeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }    

        
}
