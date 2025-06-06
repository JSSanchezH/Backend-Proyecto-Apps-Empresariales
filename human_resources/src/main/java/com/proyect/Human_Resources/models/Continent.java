package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity
@Table(name = "continents")
// Defining the Continent entity and mapping it to the "continents" table in the
// database
public class Continent {

    @Id // Primary key attribute that is mapped to the id column of the continents table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the continent

    @Column(name = "name", nullable = false, length = 100) // Column annotation to specify the mapping of the name
                                                          // attribute to the database
    // The name column is not nullable and has a maximum length of 100 characters
    private String name; // Name of the continent

    public Continent() {
        // Default constructor for JPA
    }

    public Continent(Long id, String name) {
        this.id = id; // Initializing the unique identifier of the continent
        this.name = name; // Initializing the name of the continent
    }

    // Getters and Setters for accessing and modifying the attributes of the
    // Continent class

    public Long getId() {
        return id; // Returns the unique identifier of the continent
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the continent
    }

    public String getName() {
        return name; // Returns the name of the continent
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the continent
    }
}
