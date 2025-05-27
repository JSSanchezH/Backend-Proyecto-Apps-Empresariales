package com.proyect.Human_Resources.models;

import java.sql.Date;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "evaluations") // Specifies the name of the table in the database that this entity maps to
// The table name is "evaluations"
public class Evaluation {

    @Id // Primary key attribute that is mapped to the id column of the evaluations table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the id will be generated automatically
    private Long id; // Unique identifier for the evaluation

    @ManyToOne // Many-to-one relationship with the Employee entity
    @JoinColumn(name = "employee_id", nullable = false) // Specifies the foreign key column in the evaluations table that references the employees table
    private Employee employee; // The employee associated with the evaluation

    @Column(name = "evaluation_date", nullable = false) // Column annotation to specify the mapping of the evaluation_date attribute to the database
    private Date evaluationDate; // Date of the evaluation

    @Column(name = "punctuality", nullable = false) // Column annotation to specify the mapping of the punctuality attribute to the database
    private long punctuality; // Punctuality score of the employee in the evaluation

    @Column(name = "performance", nullable = false) // Column annotation to specify the mapping of the performance attribute to the database
    private long performance; // Performance score of the employee in the evaluation

    @Column(name = "courtesy", nullable = false) // Column annotation to specify the mapping of the courtesy attribute to the database
    private long courtesy; // Courtesy score of the employee in the evaluation

    @Column(name = "precision", nullable = false) // Column annotation to specify the mapping of the precision attribute to the database
    private long precision; // Precision score of the employee in the evaluation
    
    @Column(name = "collaboration", nullable = false) // Column annotation to specify the mapping of the collaboration attribute to the database
    private long collaboration; // Collaboration score of the employee in the evaluation

    @Column(name = "proactivity", nullable = false) // Column annotation to specify the mapping of the proactivity attribute to the database
    private long proactivity; // Proactivity score of the employee in the evaluation

    // Default constructor for JPA
    public Evaluation() {
        // No-argument constructor required by JPA
    }

    // Parameterized constructor to initialize the Evaluation object with specific values
    public Evaluation(Long id, Employee employee, Date evaluationDate, long punctuality, long performance, long courtesy, long precision, long collaboration, long proactivity) {
        this.id = id; // Sets the unique identifier of the evaluation
        this.employee = employee; // Sets the employee associated with the evaluation
        this.evaluationDate = evaluationDate; // Sets the date of the evaluation
        this.punctuality = punctuality; // Sets the punctuality score of the employee in the evaluation
        this.performance = performance; // Sets the performance score of the employee in the evaluation
        this.courtesy = courtesy; // Sets the courtesy score of the employee in the evaluation
        this.precision = precision; // Sets the precision score of the employee in the evaluation
        this.collaboration = collaboration; // Sets the collaboration score of the employee in the evaluation
        this.proactivity = proactivity; // Sets the proactivity score of the employee in the evaluation
    }

    // GFetters and Setters for accessing and modifying the attributes of the Evaluation class

    public Long getId() {
        return id; // Returns the unique identifier of the evaluation
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the evaluation
    }

    public Employee getEmployee() {
        return employee; // Returns the employee associated with the evaluation
    }

    public void setEmployee(Employee employee) {
        this.employee = employee; // Sets the employee associated with the evaluation
    }

    public Date getEvaluationDate() {
        return evaluationDate; // Returns the date of the evaluation
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate; // Sets the date of the evaluation
    }

    public long getPunctuality() {
        return punctuality; // Returns the punctuality score of the employee in the evaluation
    }

    public void setPunctuality(long punctuality) {
        this.punctuality = punctuality; // Sets the punctuality score of the employee in the evaluation
    }

    public long getPerformance() {
        return performance; // Returns the performance score of the employee in the evaluation
    }

    public void setPerformance(long performance) {
        this.performance = performance; // Sets the performance score of the employee in the evaluation
    }

    public long getCourtesy() {
        return courtesy; // Returns the courtesy score of the employee in the evaluation
    }

    public void setCourtesy(long courtesy) {
        this.courtesy = courtesy; // Sets the courtesy score of the employee in the evaluation
    }

    public long getPrecision() {
        return precision; // Returns the precision score of the employee in the evaluation
    }

    public void setPrecision(long precision) {
        this.precision = precision; // Sets the precision score of the employee in the evaluation
    }

   public long getCollaboration() {
        return collaboration; // Returns the collaboration score of the employee in the evaluation
    }

    public void setCollaboration(long collaboration) {
        this.collaboration = collaboration; // Sets the collaboration score of the employee in the evaluation
    }

    public long getProactivity() {
        return proactivity; // Returns the proactivity score of the employee in the evaluation
    }

    public void setProactivity(long proactivity) {
        this.proactivity = proactivity; // Sets the proactivity score of the employee in the evaluation
    }

}

