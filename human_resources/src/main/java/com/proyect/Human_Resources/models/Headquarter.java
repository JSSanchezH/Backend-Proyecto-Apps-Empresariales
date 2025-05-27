package com.proyect.Human_Resources.models;

import jakarta.persistence.*;

@Entity
@Table(name = "headquarters") // Specifies the name of the table in the database that this entity maps to
public class Headquarter {
    
    @Id
    private long id; // Unique identifier for the headquarter

    @Column(name = "name", nullable = false) // Column annotation to specify the mapping of the name attribute to the database
    private String name; // Name of the headquarter

    @Column(name = "address", nullable = false) // Column annotation to specify the mapping of the address attribute to the database
    private String address; // Address of the headquarter

    @Column(name = "phone", nullable = false) // Column annotation to specify the mapping of the phone attribute to the database
    private long phone; // Phone number of the headquarter

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false) // Specifies the foreign key column in the headquarters table that references the cities table
    private City city; // The city associated with the headquarter

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false) // Specifies the foreign key column in the headquarters table that references the companies table
    private Company company; // The company associated with the headquarter

    // Default constructor for JPA
    public Headquarter() {
        // No-argument constructor required by JPA
    }

    // Parameterized constructor to initialize the Headquarter object with specific values
    public Headquarter(long id, String name, String address, long phone, City city, Company company) {
        this.id = id; // Sets the unique identifier of the headquarter
        this.name = name; // Sets the name of the headquarter
        this.address = address; // Sets the address of the headquarter
        this.phone = phone; // Sets the phone number of the headquarter
        this.city = city; // Sets the city associated with the headquarter
        this.company = company; // Sets the company associated with the headquarter
    }

    // Getters and Setters for accessing and modifying the attributes of the Headquarter class

    public long getId() {
        return id; // Returns the unique identifier of the headquarter
    }

    public void setId(long id) {
        this.id = id; // Sets the unique identifier of the headquarter
    }

    public String getName() {
        return name; // Returns the name of the headquarter
    }

    public void setName(String name) {
        this.name = name; // Sets the name of the headquarter
    }

    public String getAddress() {
        return address; // Returns the address of the headquarter
    }

    public void setAddress(String address) {
        this.address = address; // Sets the address of the headquarter
    }

    public long getPhone() {
        return phone; // Returns the phone number of the headquarter
    }

    public void setPhone(long phone) {
        this.phone = phone; // Sets the phone number of the headquarter
    }

    public City getCity() {
        return city; // Returns the city associated with the headquarter
    }

    public void setCity(City city) {
        this.city = city; // Sets the city associated with the headquarter
    }

    public Company getCompany() {
        return company; // Returns the company associated with the headquarter
    }

    public void setCompany(Company company) {
        this.company = company; // Sets the company associated with the headquarter
    }
}
