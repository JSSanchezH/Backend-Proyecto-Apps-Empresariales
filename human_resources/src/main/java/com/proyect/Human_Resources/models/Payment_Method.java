package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "payment_methods") // Specifies the name of the table in the database that this entity maps to
// The table name is "payment_methods"
public class Payment_Method {

    @Id // Primary key attribute that is mapped to the id column of the payment_methods table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the payment method

    @Column(name = "name", nullable = false, length = 50) // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 50 characters
    private String name; // Name of the payment method

    // Getters and Setters for accessing and modifying the attributes of the Payment_Method class

    public Long getId() {
        return id; // Returns the unique identifier of the payment method
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the payment method
    }

    public String getName() {
        return name; // Returns the name of the payment method
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the payment method
    }
    
    
}
