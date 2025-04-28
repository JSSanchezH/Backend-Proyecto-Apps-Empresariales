package com.proyect.Human_Resources.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.Human_Resources.models.Payroll;
import com.proyect.Human_Resources.services.PayrollService;

@RestController
@RequestMapping("/payrolls") // Specifies the base URL for this controller
public class PayrollControler {

    @Autowired
    private PayrollService payrollService; // Injecting the PayrollService dependency

    // Add methods to handle HTTP requests for payrolls here 

    @GetMapping
    public ArrayList<Payroll> getPayrolls() {
        return payrollService.getPayrolls(); // Retrieves all payrolls from the service
    }

    @PostMapping
    public Payroll savePayroll(@RequestBody Payroll payroll) {
        return payrollService.savePayroll(payroll); // Saves the payroll using the service and returns the saved payroll
    }

    @PostMapping("/batch")
    public ArrayList<Payroll> savePayrolls(@RequestBody ArrayList<Payroll> payrolls) {
        return payrollService.savePayrolls(payrolls); // Saves a list of payrolls using the service and returns the saved payrolls
    }

    @GetMapping("/{id}")
    public Optional<Payroll> getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id); // Retrieves a payroll by its ID using the service
    }

    @PutMapping("/{id}")
    public Payroll updatePayroll(@RequestBody Payroll payroll, @PathVariable Long id) {
        return payrollService.updatePayroll(payroll, id); // Updates the payroll using the service and returns the updated payroll
    }

    @DeleteMapping("/{id}")
    public String deletePayroll(@PathVariable Long id) {
        if (payrollService.deletePayroll(id)) {
            return "Payroll deleted successfully"; // Returns a success message if the payroll was deleted
        } else {
            return "Error deleting payroll"; // Returns an error message if there was an issue during deletion
        }
    }    
}
