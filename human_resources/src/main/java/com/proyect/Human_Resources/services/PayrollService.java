package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IPayrollRepository;
import com.proyect.Human_Resources.models.Payroll;

@Service
public class PayrollService {

    @Autowired
    private IPayrollRepository payrollRepository; // Injecting the ISalaryRepository dependency

    public ArrayList<Payroll> getPayrolls(long nit) {
        return (ArrayList<Payroll>) payrollRepository.findByEmployeeDepartmentHeadquarterCompanyNit(nit); // Retrieves all payroll records from the database
    }

    public Payroll savePayroll(Payroll payroll) {
        return payrollRepository.save(payroll); // Saves the payroll record to the database and returns the saved record
    }

    public ArrayList<Payroll> savePayrolls(ArrayList<Payroll> payrolls) {
        return (ArrayList<Payroll>) payrollRepository.saveAll(payrolls); // Saves a list of payroll records to the database and returns the saved records
    }

    public Optional<Payroll> getPayrollById(long id) {
        return payrollRepository.findById(id); // Retrieves the payroll record by its ID
    }

    public Payroll updatePayroll(Payroll payroll, long id) {
        Payroll payrollToUpdate = payrollRepository.findById(id).get(); // Retrieves the payroll record to update
        payrollToUpdate.setEmployee(payroll.getEmployee()); // Updates the employee associated with the payroll record
        payrollToUpdate.setPaymentDate(payroll.getPaymentDate()); // Updates the payment date of the payroll record
        payrollToUpdate.setBaseSalary(payroll.getBaseSalary()); // Updates the base salary of the payroll record
        payrollToUpdate.setBonuses(payroll.getBonuses()); // Updates the bonuses of the payroll record
        payrollToUpdate.setPaymentMethod(payroll.getPaymentMethod()); // Updates the payment method of the payroll record
        payrollToUpdate.setTotalPayment(payroll.getTotalPayment()); // Updates the total payment of the payroll record
        return payrollRepository.save(payrollToUpdate); // Saves the updated payroll record and returns it
    }

    public boolean deletePayroll(long id) {
        try {
            payrollRepository.deleteById(id); // Deletes the payroll record by its ID
            return true; // Returns true if deletion was successful
        } catch (Exception e) {
            return false; // Returns false if there was an error during deletion
        }
    }
    
}

