package com.proyect.Human_Resources.models;

import java.sql.Date;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "work_absences") // Specifies the name of the table in the database that this entity maps to
// The table name is "work_absences"
public class WorkAbsences {

    @Id // Primary key attribute that is mapped to the id column of the work_absences table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the id will be generated automatically
    private Long id; // Unique identifier for the work absence

    @ManyToOne // Many-to-one relationship with the Employee entity
    @JoinColumn(name = "employee_id", nullable = false) // Specifies the foreign key column in the work_absences table that references the employees table
    private Employee employee; // The employee associated with the work absence

    @OneToOne // One-to-one relationship with the AbsenceType entity
    @JoinColumn(name = "absence_type_id", nullable = false) // Specifies the foreign key column in the work_absences table that references the absence_types table
    private Absence_Type absenceType; // The type of absence associated with the work absence

    @Column(name = "start_date", nullable = false) // Column annotation to specify the mapping of the start_date attribute to the database
    private Date startDate; // Start date of the work absence

    @Column(name = "end_date", nullable = false) // Column annotation to specify the mapping of the end_date attribute to the database
    private Date endDate; // End date of the work absence

    @Column(name = "description", nullable = false, length = 100) // Column annotation to specify the mapping of the description attribute to the database
    private String description; // Reason for the work absence

    // Getters and Setters for accessing and modifying the attributes of the WorkAbsences class

    public Long getId() {
        return id; // Returns the unique identifier of the work absence
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the work absence
    }

    public Employee getEmployee() {
        return employee; // Returns the employee associated with the work absence
    }

    public void setEmployee(Employee employee) {
        this.employee = employee; // Sets the employee associated with the work absence
    }

    public Absence_Type getAbsenceType() {
        return absenceType; // Returns the type of absence associated with the work absence
    }

    public void setAbsenceType(Absence_Type absenceType) {
        this.absenceType = absenceType; // Sets the type of absence associated with the work absence
    }

    public Date getStartDate() {
        return startDate; // Returns the start date of the work absence
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate; // Sets the start date of the work absence
    }

    public Date getEndDate() {
        return endDate; // Returns the end date of the work absence
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate; // Sets the end date of the work absence
    }

    public String getDescription() {
        return description; // Returns the reason for the work absence
    }

    public void setDescription(String description) {
        this.description = description; // Sets the reason for the work absence
    }
}
