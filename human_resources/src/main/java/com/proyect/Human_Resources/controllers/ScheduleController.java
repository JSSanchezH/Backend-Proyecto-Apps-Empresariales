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

import com.proyect.Human_Resources.models.Schedule;
import com.proyect.Human_Resources.services.ScheduleService;

@RestController
@RequestMapping("/schedules") // Specifies the base URL for this controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService; // Injecting the ScheduleService dependency

    @GetMapping
    public ArrayList<Schedule> getSchedules() {
        return scheduleService.getSchedules(); // Retrieves all schedules from the service
    }

    @PostMapping
    public Schedule saveSchedule(@RequestBody Schedule schedule) {
        return scheduleService.saveSchedule(schedule); // Saves the schedule using the service and returns the saved schedule
    }

    @PostMapping("/batch")
    public ArrayList<Schedule> saveSchedules(@RequestBody ArrayList<Schedule> schedules) {
        return scheduleService.saveSchedules(schedules); // Saves a list of schedules using the service and returns the saved schedules
    }

    @GetMapping("/{id}")
    public Optional<Schedule> getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id); // Retrieves a schedule by its ID using the service
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@RequestBody Schedule schedule, @PathVariable Long id) {
        return scheduleService.updateSchedule(schedule, id); // Updates the schedule using the service and returns the updated schedule
    }

    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        if (scheduleService.deleteSchedule(id)) {
            return "Schedule deleted successfully"; // Returns a success message if the schedule was deleted
        } else {
            return "Error deleting schedule"; // Returns an error message if there was an issue during deletion
        }
    }    
}
