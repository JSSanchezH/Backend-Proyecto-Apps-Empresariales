package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;


@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "user_company") // Specifies the name of the table in the database that this entity maps to
// The table name is "user_company"
public class UserCompany {

    @Id // Primary key attribute that is mapped to the id column of the user_company table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the user_company record

    @OneToOne // One-to-one relationship with the Company entity
    @JoinColumn(name = "company_id", nullable = false) // Specifies the foreign key column in the user_company table that references the companies table
    private Company company; // The company associated with the user_company record
    // The company_id column in the user_company table references the id column in the companies table

    @Column(name = "username", nullable = false, length = 50) // Column annotation to specify the mapping of the username attribute to the database
    private String userName; // Username of the user associated with the company

    @Column(name = "password", nullable = false, length = 50) // Column annotation to specify the mapping of the password attribute to the database
    private String password; // Password of the user associated with the company

    // Getters and Setters for accessing and modifying the attributes of the UserCompany class

    public Long getId() {
        return id; // Returns the unique identifier of the user_company record
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the user_company record
    }

    public Company getCompany() {
        return company; // Returns the company associated with the user_company record
    }

    public void setCompany(Company company) {
        this.company = company; // Sets the company associated with the user_company record
    }

    public String getUserName() {
        return userName; // Returns the username of the user associated with the company
    }

    public void setUserName(String userName) {
        this.userName = userName; // Sets the username of the user associated with the company
    }

    public String getPassword() {
        return password; // Returns the password of the user associated with the company
    }

    public void setPassword(String password) {
        this.password = password; // Sets the password of the user associated with the company
    }
}
