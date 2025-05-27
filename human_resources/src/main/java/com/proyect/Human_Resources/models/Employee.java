package com.proyect.Human_Resources.models;

import java.sql.Date;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "employees") // Specifies the name of the table in the database that this entity maps to
// The table name is "employees"
public class Employee {

    @Id // Primary key attribute that is mapped to the id column of the employees table
    private Long id; // Unique identifier for the employee;

    @Column(name = "first_Name", nullable = false, length = 50) // Column annotation to specify the mapping of the lastname attribute to the database
    private String firstname; // Name of the employee

    @Column(name = "last_Name", nullable = false, length = 50) // Column annotation to specify the mapping of the lastname attribute to the database
    private String lastname; // Last name of the employee

    @Column(name = "email", nullable = false, length = 50) // Column annotation to specify the mapping of the email attribute to the database
    private String email; // Email of the employee

    @Column(name = "phone_number", nullable = false, length = 50) // Column annotation to specify the mapping of the phone attribute to the database
    private String phoneNumber; // Phone number of the employee
    
    @Column(name = "hire_Date", nullable = false) // Column annotation to specify the mapping of the Hire_Date attribute to the database
    private Date hireDate; // Date of hire of the employee

    @ManyToOne // One-to-one relationship with the Role entity
    @JoinColumn(name = "role_id", nullable = false) // Specifies the foreign key column in the employees table that references the roles table
    private Role role; // The role associated with the employee

    @ManyToOne // Many-to-one relationship with the Department entity
    @JoinColumn(name = "department_id", nullable = false) // Specifies the foreign key column in the employees table that references the departments table
    private Department department; // The department associated with the employee

    @Column(name = "url_Foto", nullable = false, length = 100) // Column annotation to specify the mapping of the url_Foto attribute to the database
    private String urlFoto;

    @Column(name = "status", nullable = false) // Column annotation to specify the mapping of the status attribute to the database
    private boolean status; // Status of the employee (active/inactive)

    // Default constructor for JPA
    public Employee() {
        // No-argument constructor required by JPA
    }

    // Parameterized constructor to initialize the Employee object with specific values
    public Employee(Long id, String firstname, String lastname, String email, String phoneNumber, Date hireDate, Role role, Department department, String urlFoto, boolean status) {
        this.id = id; // Sets the unique identifier of the employee
        this.firstname = firstname; // Sets the first name of the employee
        this.lastname = lastname; // Sets the last name of the employee
        this.email = email; // Sets the email of the employee
        this.phoneNumber = phoneNumber; // Sets the phone number of the employee
        this.hireDate = hireDate; // Sets the hire date of the employee
        this.role = role; // Sets the role associated with the employee
        this.department = department; // Sets the department associated with the employee
        this.urlFoto = urlFoto; // Sets the URL of the employee's photo
        this.status = status; // Sets the status of the employee (active/inactive)
    }

    // Getters and Setters for accessing and modifying the attributes of the Employee class

    public Long getId() {
        return id; // Returns the unique identifier of the employee
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the employee
    }

    public String getFirstname() {
        return firstname; // Returns the first name of the employee
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname; // Sets the first name of the employee
    }

    public String getLastname() {
        return lastname; // Returns the last name of the employee
    }

    public void setLastname(String lastname) {
        this.lastname = lastname; // Sets the last name of the employee
    }

    public String getEmail() {
        return email; // Returns the email of the employee
    }

    public void setEmail(String email) {
        this.email = email; // Sets the email of the employee
    }

    public String getPhoneNumber() {
        return phoneNumber; // Returns the phone number of the employee
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber; // Sets the phone number of the employee
    }

    public Date getHireDate() {
        return hireDate; // Returns the hire date of the employee
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate; // Sets the hire date of the employee
    }

    public Role getRole() {
        return role; // Returns the role associated with the employee
    }

    public void setRole(Role role) {
        this.role = role; // Sets the role associated with the employee
    }

    public Department getDepartment() {
        return department; // Returns the department associated with the employee
    }

    public void setDepartment(Department department) {
        this.department = department; // Sets the department associated with the employee
    }

    public String getUrlFoto() {
        return urlFoto; // Returns the URL of the employee's photo
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto; // Sets the URL of the employee's photo
    }

    public boolean isStatus() {
        return status; // Returns the status of the employee (active/inactive)
    }

    public void setStatus(boolean status) {
        this.status = status; // Sets the status of the employee (active/inactive)
    }
}
