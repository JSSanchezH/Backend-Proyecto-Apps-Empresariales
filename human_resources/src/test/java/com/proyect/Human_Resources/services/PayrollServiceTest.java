package com.proyect.Human_Resources.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IPayrollRepository;
import com.proyect.Human_Resources.models.Payroll;

@ExtendWith(MockitoExtension.class)
public class PayrollServiceTest {

    @Mock
    private IPayrollRepository payrollRepository;

    @InjectMocks
    private PayrollService payrollService;

    private Payroll payroll;
    private ArrayList<Payroll> payrollList;

    @BeforeEach
    void setUp() {
        payroll = new Payroll();
        payroll.setId(1L);
        payroll.setBaseSalary(2000.0);
        payroll.setBonuses(500.0);
        payroll.setTotalPayment(2500.0);
        payroll.setPaymentDate(Date.valueOf("2025-05-26"));

        payrollList = new ArrayList<>();
        payrollList.add(payroll);
    }

    @Test
    void testGetPayrolls() {
        when(payrollRepository.findByEmployeeDepartmentHeadquarterCompanyNit(123456789L))
                .thenReturn(payrollList);

        ArrayList<Payroll> result = payrollService.getPayrolls(123456789L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(payrollRepository).findByEmployeeDepartmentHeadquarterCompanyNit(123456789L);
    }

    @Test
    void testSavePayroll() {
        when(payrollRepository.save(payroll)).thenReturn(payroll);

        Payroll saved = payrollService.savePayroll(payroll);

        assertNotNull(saved);
        assertEquals(2500.0, saved.getTotalPayment());
        verify(payrollRepository).save(payroll);
    }

    @Test
    void testSavePayrolls() {
        when(payrollRepository.saveAll(payrollList)).thenReturn(payrollList);

        ArrayList<Payroll> savedList = payrollService.savePayrolls(payrollList);

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(payrollRepository).saveAll(payrollList);
    }

    @Test
    void testGetPayrollById_Found() {
        when(payrollRepository.findById(1L)).thenReturn(Optional.of(payroll));

        Optional<Payroll> found = payrollService.getPayrollById(1L);

        assertTrue(found.isPresent());
        assertEquals(payroll, found.get());
        verify(payrollRepository).findById(1L);
    }

    @Test
    void testGetPayrollById_NotFound() {
        when(payrollRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Payroll> result = payrollService.getPayrollById(1L);

        assertFalse(result.isPresent());
        verify(payrollRepository).findById(1L);
    }

    @Test
    void testUpdatePayroll() {
        Payroll updatedData = new Payroll();
        updatedData.setBaseSalary(3000.0);
        updatedData.setBonuses(700.0);
        updatedData.setTotalPayment(3700.0);
        updatedData.setPaymentDate(Date.valueOf("2025-06-01"));

        when(payrollRepository.findById(1L)).thenReturn(Optional.of(payroll));
        when(payrollRepository.save(any(Payroll.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payroll updated = payrollService.updatePayroll(updatedData, 1L);

        assertEquals(3000.0, updated.getBaseSalary());
        assertEquals(700.0, updated.getBonuses());
        assertEquals(3700.0, updated.getTotalPayment());
        assertEquals(Date.valueOf("2025-06-01"), updated.getPaymentDate());

        verify(payrollRepository).findById(1L);
        verify(payrollRepository).save(any(Payroll.class));
    }

    @Test
    void testDeletePayroll_Success() {
        doNothing().when(payrollRepository).deleteById(1L);

        boolean result = payrollService.deletePayroll(1L);

        assertTrue(result);
        verify(payrollRepository).deleteById(1L);
    }

    @Test
    void testDeletePayroll_Failure() {
        doThrow(new RuntimeException("Delete failed")).when(payrollRepository).deleteById(1L);

        boolean result = payrollService.deletePayroll(1L);

        assertFalse(result);
        verify(payrollRepository).deleteById(1L);
    }
    
}
