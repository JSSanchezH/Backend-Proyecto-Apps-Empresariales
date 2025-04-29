package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IStateRepository;
import com.proyect.Human_Resources.models.State;

@Service
public class StateService {

    @Autowired
    private IStateRepository stateRepository;

    public ArrayList<State> getStates() {
        return (ArrayList<State>) stateRepository.findAll();
    }

    public State saveState(State state) {
        return stateRepository.save(state);
    }

    public ArrayList<State> saveStates(ArrayList<State> states) {
        return (ArrayList<State>) stateRepository.saveAll(states);
    }

    public Optional<State> getStateById(long id) {
        return stateRepository.findById(id);
    }

    public State updateState(State state, long id) {
        State stateToUpdate = stateRepository.findById(id).get();
        stateToUpdate.setName(state.getName());
        stateToUpdate.setCountry(state.getCountry());
        return stateRepository.save(stateToUpdate);
    }

    public boolean deleteState(long id) {
        try {
            stateRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
