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
    
}
