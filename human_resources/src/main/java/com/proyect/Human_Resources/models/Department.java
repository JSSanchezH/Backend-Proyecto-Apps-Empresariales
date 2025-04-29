package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "departments") // Specifies the name of the table in the database that this entity maps to
// The table name is "departments"
public class Department {

    @Id // Primary key attribute that is mapped to the id column of the departments table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the department

    @Column(name = "name", nullable = false, length = 100) // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 100 characters
    private String name; // Name of the department

    @ManyToOne // Specifies a many-to-one relationship with the headquarter entity
    @JoinColumn(name = "headquarter_id", nullable = false) // Specifies the foreign key column in the departments table that references the headquarters table
    private Headquarter headquarter; // The headquarter associated with the department

    // Getters and Setters for accessing and modifying the attributes of the Department class

    public Long getId() {
        return id; // Returns the unique identifier of the department
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the department
    }

    public String getName() {
        return name; // Returns the name of the department
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the department
    }

    public Headquarter getHeadquarter() {
        return headquarter; // Returns the headquarter associated with the department
    }

    public void setHeadquarter(Headquarter headquarter) {
        this.headquarter = headquarter; // Sets the headquarter associated with the department
    }
    
}
