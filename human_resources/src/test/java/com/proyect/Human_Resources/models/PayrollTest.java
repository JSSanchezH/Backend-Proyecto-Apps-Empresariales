package com.proyect.Human_Resources.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class PayrollTest {
    
    @Test
    public void testSettersAndGetters() {
        Payroll payroll = new Payroll();

        Long id = 1L;
        Employee employee = new Employee();
        Date paymentDate = Date.valueOf("2024-05-25");
        double baseSalary = 1500.0;
        double bonuses = 300.0;
        double totalPayment = 1800.0;
        Payment_Method paymentMethod = new Payment_Method();

        payroll.setId(id);
        payroll.setEmployee(employee);
        payroll.setPaymentDate(paymentDate);
        payroll.setBaseSalary(baseSalary);
        payroll.setBonuses(bonuses);
        payroll.setTotalPayment(totalPayment);
        payroll.setPaymentMethod(paymentMethod);

        assertEquals(id, payroll.getId());
        assertEquals(employee, payroll.getEmployee());
        assertEquals(paymentDate, payroll.getPaymentDate());
        assertEquals(baseSalary, payroll.getBaseSalary());
        assertEquals(bonuses, payroll.getBonuses());
        assertEquals(totalPayment, payroll.getTotalPayment());
        assertEquals(paymentMethod, payroll.getPaymentMethod());
    }
}
