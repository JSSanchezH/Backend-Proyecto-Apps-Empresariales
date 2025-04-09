package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "countries") // Specifies the name of the table in the database that this entity maps to
// The table name is "countries"
public class Country {
    
    @Id // Primary key attribute that is mapped to the id column of the countries table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the country

    @Column(name = "name", nullable = false, length = 100) // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 100 characters
    private String name; // Name of the country

    @ManyToOne // Many-to-one relationship with the Continent entity
    @JoinColumn(name = "continent_id", nullable = false) // Specifies the foreign key column in the countries table that references the continents table
    private Continent continent; // The continent associated with the country
    // The continent_id column in the countries table references the id column in the continents table

    // Getters and Setters for accessing and modifying the attributes of the Country class

    public Long getId() {
        return id; // Returns the unique identifier of the country
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the country
    }

    public String getName() {
        return name; // Returns the name of the country
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the country
    }

    public Continent getContinent() {
        return continent; // Returns the continent associated with the country
    }

    public void setContinent(Continent continent) {
        this.continent = continent; // Sets the continent associated with the country
    }

}
