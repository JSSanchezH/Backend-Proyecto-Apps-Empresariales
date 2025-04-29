package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "states") // Specifies the name of the table in the database that this entity maps to
// The table name is "states"
public class State {
    
    @Id // Primary key attribute that is mapped to the id column of the states table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the state

    @Column(name = "name", nullable = false, length = 100) // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 100 characters
    private String name; // Name of the state

    @ManyToOne // Many-to-one relationship with the Country entity
    @JoinColumn(name = "country_id", nullable = false) // Specifies the foreign key column in the states table that references the countries table
    private Country country; // The country associated with the state
    // The country_id column in the states table references the id column in the countries table

    // Getters and Setters for accessing and modifying the attributes of the State class

    public Long getId() {
        return id; // Returns the unique identifier of the state
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the state
    }

    public String getName() {
        return name; // Returns the name of the state
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the state
    }

    public Country getCountry() {
        return country; // Returns the country associated with the state
    }

    public void setCountry(Country country) {
        this.country = country; // Sets the country associated with the state
    }

}
