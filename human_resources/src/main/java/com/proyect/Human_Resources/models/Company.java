package com.proyect.Human_Resources.models;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;


@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "companies") // Specifies the name of the table in the database that this entity maps to the companies table
public class Company {

    @Id // Primary key attribute that is mapped to the id column of the companies table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the ID value
    private Long id; // Unique identifier for the company
    
    @Column(name = "name", nullable = false, length = 50) // Column annotation to specify the mapping of the name attribute to the database
    // The name column is not nullable and has a maximum length of 50 characters
    private String name; // Name of the company

    @Column(name = "nit", nullable = false) // Column annotation to specify the mapping of the nit attribute to the database
    private long nit; // NIT (Tax Identification Number) of the company

    @Column(name = "address", nullable = false, length = 100) // Column annotation to specify the mapping of the address attribute to the database
    // The address column is not nullable and has a maximum length of 100 characters
    private String address; // Address of the company

    @Column(name = "email", nullable = false, length = 50) // Column annotation to specify the mapping of the email attribute to the database
    // The email column is not nullable and has a maximum length of 50 characters
    private String email; // Email of the company

    @Column(name = "type_Industry", nullable = false, length = 30) // Column annotation to specify the mapping of the type_Industry attribute to the database
    // The type_Industry column is not nullable and has a maximum length of 30 characters
    private String typeIndustry; // Type of industry of the company
    
    @Column(name = "url_Logo", nullable = false, length = 100) // Column annotation to specify the mapping of the url_Logo attribute to the database
    // The url_Logo column is not nullable and has a maximum length of 100 characters
    private String urlLogo; // URL of the company's logo

    // Default constructor for JPA
    public Company() {
        // No-argument constructor required by JPA
    }

    // Parameterized constructor to initialize the Company object with specific values
    public Company(Long id,String name, long nit, String address, String email, String typeIndustry, String urlLogo) {
        this.name = name; // Sets the name of the company
        this.nit = nit; // Sets the NIT of the company
        this.address = address; // Sets the address of the company
        this.email = email; // Sets the email of the company
        this.typeIndustry = typeIndustry; // Sets the type of industry of the company
        this.urlLogo = urlLogo; // Sets the URL of the company's logo
    }

    // Getters and Setters for accessing and modifying the attributes of the Company class

    public Long getId() {
        return id; // Returns the unique identifier of the company
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the company
    }

    public String getName() {
        return name; // Returns the name of the company
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the company
    }

    public long getNit() {
        return nit; // Returns the NIT of the company
    }

    public void setNit(long nit) {
        this.nit = nit; // Sets the NIT of the company
    }

    public String getAddress() {
        return address; // Returns the address of the company
    }

    public void setAddress(String address) {
        this.address = address; // Sets the address of the company
    }

    public String getEmail() {
        return email; // Returns the email of the company
    }

    public void setEmail(String email) {
        this.email = email; // Sets the email of the company
    }

    public String getTypeIndustry() {
        return typeIndustry; // Returns the type of industry of the company
    }

    public void setTypeIndustry(String typeIndustry) {
        this.typeIndustry = typeIndustry; // Sets the type of industry of the company
    }

    public String getUrlLogo() {
        return urlLogo; // Returns the URL of the company's logo
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo; // Sets the URL of the company's logo
    }

    
}
