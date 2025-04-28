package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IScheduleReposiory;
import com.proyect.Human_Resources.models.Schedule;

@Service // Indicates that this class is a service component in the Spring framework
public class ScheduleService {

    @Autowired 
    private IScheduleReposiory scheduleRepository; // Injecting the IScheduleRepository dependency

    public ArrayList<Schedule> getSchedules() {
        return (ArrayList<Schedule>) scheduleRepository.findAll(); // Retrieves all schedule records from the database
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule); // Saves the schedule record to the database and returns the saved record
    }

    public ArrayList<Schedule> saveSchedules(ArrayList<Schedule> schedules) {
        return (ArrayList<Schedule>) scheduleRepository.saveAll(schedules); // Saves a list of schedule records to the database and returns the saved records
    }

    public Optional<Schedule> getScheduleById(long id) {
        return scheduleRepository.findById(id); // Retrieves the schedule record by its ID
    }

    public Schedule updateSchedule(Schedule schedule, long id) {
        Schedule scheduleToUpdate = scheduleRepository.findById(id).get(); // Retrieves the schedule record to update
        scheduleToUpdate.setStartTime(schedule.getStartTime()); // Updates the start time of the schedule record
        scheduleToUpdate.setEndTime(schedule.getEndTime()); // Updates the end time of the schedule record
        scheduleToUpdate.setBreakStart(schedule.getBreakStart()); // Updates the break start time of the schedule record
        scheduleToUpdate.setBreakEnd(schedule.getBreakEnd()); // Updates the break end time of the schedule record
        scheduleToUpdate.setEmployee(schedule.getEmployee()); // Updates the employee associated with the schedule record
        return scheduleRepository.save(scheduleToUpdate); // Saves the updated schedule record and returns it
    }

    public boolean deleteSchedule(long id) {
        try {
            scheduleRepository.deleteById(id); // Deletes the schedule record by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }

}
