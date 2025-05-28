package com.proyect.Human_Resources.models;

import java.sql.Time;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "schedules") // Specifies the name of the table in the database that this entity maps to
// The table name is "schedules"
public class Schedule {

    @Id // Primary key attribute that is mapped to the id column of the schedules table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the id will be generated automatically
    private Long id; // Unique identifier for the schedule

    @Column(name = "start_Time", nullable = false) // Column annotation to specify the mapping of the Start-Time attribute to the database
    private Time startTime; // Start time of the schedule

    @Column(name = "end_Time", nullable = false) // Column annotation to specify the mapping of the End-Time attribute to the database
    private Time endTime; // End time of the schedule

    @Column(name = "break_Start", nullable = false) // Column annotation to specify the mapping of the break_Start attribute to the database
    private Time breakStart; // Start time of the break

    @Column(name = "break_End", nullable = false) // Column annotation to specify the mapping of the break_End attribute to the database
    private Time breakEnd; // End time of the break

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false) // Specifies the foreign key column in the schedules table that references the employees table
    private Employee employee; // The employee associated with the schedule

    // Default constructor for JPA

    public Schedule() {
        // No-argument constructor required by JPA
    }

    // Parameterized constructor to initialize the Schedule object with specific values

    public Schedule(Long id, Time startTime, Time endTime, Time breakStart, Time breakEnd, Employee employee) {
        this.id = id; // Sets the unique identifier of the schedule
        this.startTime = startTime; // Sets the start time of the schedule
        this.endTime = endTime; // Sets the end time of the schedule
        this.breakStart = breakStart; // Sets the start time of the break
        this.breakEnd = breakEnd; // Sets the end time of the break
        this.employee = employee; // Sets the employee associated with the schedule
    }
    

    // Getters and Setters for accessing and modifying the attributes of the Schedule class
    
    public Long getId() {
        return id; // Returns the unique identifier of the schedule
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the schedule
    }

    public Time getStartTime() {
        return startTime; // Returns the start time of the schedule
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime; // Sets the start time of the schedule
    }

    public Time getEndTime() {
        return endTime; // Returns the end time of the schedule
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime; // Sets the end time of the schedule
    }

    public Time getBreakStart() {
        return breakStart; // Returns the start time of the break
    }

    public void setBreakStart(Time breakStart) {
        this.breakStart = breakStart; // Sets the start time of the break
    }

    public Time getBreakEnd() {
        return breakEnd; // Returns the end time of the break
    }

    public void setBreakEnd(Time breakEnd) {
        this.breakEnd = breakEnd; // Sets the end time of the break
    }

    public Employee getEmployee() {
        return employee; // Returns the employee associated with the schedule
    }

    public void setEmployee(Employee employee) {
        this.employee = employee; // Sets the employee associated with the schedule
    }
    
}
