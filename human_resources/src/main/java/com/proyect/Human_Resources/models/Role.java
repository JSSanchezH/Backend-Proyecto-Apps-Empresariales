package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
// Defining the Role entity and mapping it to the "roles" table in the database
public class Role {
    
    // Constructor for the Role class
    // This constructor is used by JPA to create instances of the Role class
    public Role() {
    }

    // Primary key attribute that is mapped to the id column of the clients table
    @Id
    private Long id; // Unique identifier for the role

    // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 50 characters
    @Column(name = "name", nullable = false, length = 50) 
    private String name; // Name of the role

    // Getters and Setters for accessing and modifying the attributes of the Role class

    public Long getId() {
        return id; // Returns the unique identifier of the role
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the role
    }

    public String getName() {
        return name; // Returns the name of the role
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the role
    }
}
