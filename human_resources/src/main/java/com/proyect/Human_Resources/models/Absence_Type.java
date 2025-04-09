package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;


@Entity
@Table(name = "absence_types")
// Defining the Absence_Type entity and mapping it to the "absence_types" table in the database
public class Absence_Type {
    
    @Id // Primary key attribute that is mapped to the id column of the absence_types table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the absence type

    @Column(name = "name", nullable = false, length = 50) // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 50 characters
    private String name; // Name of the absence type

    // Getters and Setters for accessing and modifying the attributes of the Absence_Type class

    public Long getId() {
        return id; // Returns the unique identifier of the absence type
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the absence type
    }

    public String getName() {
        return name; // Returns the name of the absence type
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the absence type
    }
    
}
