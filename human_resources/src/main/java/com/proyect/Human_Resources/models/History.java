package com.proyect.Human_Resources.models;

import java.sql.Date;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "history") // Specifies the name of the table in the database that this entity maps to
public class History {
    
    @Id // Primary key attribute that is mapped to the id column of the history table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the id will be generated automatically
    private long id; // Unique identifier for the history record

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false) // Specifies the foreign key column in the history table that references the employees table
    private Employee employee; // The employee associated with the history record

    @Column(name = "end_Date", nullable = false) // Column annotation to specify the mapping of the start_date attribute to the database
    private Date endDate; // Start date of the history record

    @Column(name = "reason", nullable = false) // Column annotation to specify the mapping of the start_date attribute to the database
    private String reason; // Reason for the history record

    // Getters and Setters for accessing and modifying the attributes of the History class

    public long getId() {
        return id; // Returns the unique identifier of the history record
    }

    public void setId(long id) {
        this.id = id; // Sets the unique identifier of the history record
    }

    public Employee getEmployee() {
        return employee; // Returns the employee associated with the history record
    }

    public void setEmployee(Employee employee) {
        this.employee = employee; // Sets the employee associated with the history record
    }

    public Date getEndDate() {
        return endDate; // Returns the end date of the history record
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate; // Sets the end date of the history record
    }

    public String getReason() {
        return reason; // Returns the reason for the history record
    }

    public void setReason(String reason) {
        this.reason = reason; // Sets the reason for the history record
    }

}
