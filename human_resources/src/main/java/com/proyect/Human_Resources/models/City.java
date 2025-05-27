package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;


@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "cities") // Specifies the name of the table in the database that this entity maps to
// The table name is "cities"
public class City {

    @Id // Primary key attribute that is mapped to the id column of the cities table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the city

    @Column(name = "name", nullable = false, length = 100) // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 50 characters
    private String name; // Name of the city
    
    @ManyToOne // Many-to-one relationship with the State entity
    @JoinColumn(name = "state_id", nullable = false) // Specifies the foreign key column in the cities table that references the states table
    private State state; // The state associated with the city
    // The state_id column in the cities table references the id column in the states table

    // Default constructor
    public City() {
        // No-argument constructor for JPA
    }

    // Parameterized constructor to initialize the City object with name and state
    public City(Long id,String name, State state) {
        this.id = id; // Sets the unique identifier of the city
        this.name = name; // Sets the name of the city
        this.state = state; // Sets the state associated with the city
    }

    // Getters and Setters for accessing and modifying the attributes of the City class

    public Long getId() {
        return id; // Returns the unique identifier of the city
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the city
    }

    public String getName() {
        return name; // Returns the name of the city
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the city
    }
    
    public State getState() {
        return state; // Returns the state associated with the city
    }

    public void setState(State state) {
        this.state = state; // Sets the state associated with the city
    }

}
