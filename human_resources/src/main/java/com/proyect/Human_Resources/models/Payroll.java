package com.proyect.Human_Resources.models;

import java.sql.Date;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;


@Entity // Indicates that this class is an entity and is mapped to a database table
@Table(name = "payroll") // Specifies the name of the table in the database that this entity maps to
// The table name is "payroll"
public class Payroll {
    
    @Id // Primary key attribute that is mapped to the id column of the payroll table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the id will be generated automatically
    private Long id; // Unique identifier for the payroll record

    @OneToOne // One-to-one relationship with the Employee entity
    @JoinColumn(name = "employee_id", nullable = false) // Specifies the foreign key column in the payroll table that references the employees table
    private Employee employee; // The employee associated with the payroll record

    @Column(name = "payment_Date", nullable = false) // Column annotation to specify the mapping of the payment_Date attribute to the database
    private Date paymentDate; // Date of the payroll payment

    @Column(name = "base_Salary", nullable = false) // Column annotation to specify the mapping of the base_Salary attribute to the database
    private double baseSalary; // Base salary of the employee

    @Column(name = "bonuses", nullable = false) // Column annotation to specify the mapping of the bonuses attribute to the database
    private double bonuses; // Bonus amount for the employee

    @Column(name = "total_Payment", nullable = false) // Column annotation to specify the mapping of the total_Payment attribute to the database
    private double totalPayment; // Total payment amount for the employee, calculated as base salary + bonuses

    @OneToOne // One-to-one relationship with the Payment_Method entity
    @JoinColumn(name = "payment_method_id", nullable = false) // Specifies the foreign key column in the payroll table that references the payment_methods table
    private Payment_Method paymentMethod; // The payment method used for the payroll record

    // Getters and Setters for accessing and modifying the attributes of the Payroll class

    public Long getId() {
        return id; // Returns the unique identifier of the payroll record
    }

    public void setId(Long id) {
        this.id = id; // Sets the unique identifier of the payroll record
    }

    public Employee getEmployee() {
        return employee; // Returns the employee associated with the payroll record
    }

    public void setEmployee(Employee employee) {
        this.employee = employee; // Sets the employee associated with the payroll record
    }

    public Date getPaymentDate() {
        return paymentDate; // Returns the date of the payroll payment
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate; // Sets the date of the payroll payment
    }

    public double getBaseSalary() {
        return baseSalary; // Returns the base salary of the employee
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary; // Sets the base salary of the employee
    }

    public double getBonuses() {
        return bonuses; // Returns the bonus amount for the employee
    }


    public void setBonuses(double bonuses) {
        this.bonuses = bonuses; // Sets the bonus amount for the employee
    }

    public double getTotalPayment() {
        return totalPayment; // Returns the total payment amount for the employee
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment; // Sets the total payment amount for the employee
    }

    public Payment_Method getPaymentMethod() {
        return paymentMethod; // Returns the payment method used for the payroll record
    }

    public void setPaymentMethod(Payment_Method paymentMethod) {
        this.paymentMethod = paymentMethod; // Sets the payment method used for the payroll record
    }

}
