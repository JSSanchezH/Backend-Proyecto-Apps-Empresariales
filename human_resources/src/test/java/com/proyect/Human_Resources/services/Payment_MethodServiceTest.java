package com.proyect.Human_Resources.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyect.Human_Resources.Repositories.IPaymentMethodRepository;
import com.proyect.Human_Resources.models.Payment_Method;

@ExtendWith(MockitoExtension.class)
public class Payment_MethodServiceTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @InjectMocks
    private Payment_MethodService paymentMethodService;

    private Payment_Method paymentMethod;
    private ArrayList<Payment_Method> paymentMethodList;

    @BeforeEach
    void setUp() {
        paymentMethod = new Payment_Method();
        paymentMethod.setId(1L);
        paymentMethod.setName("Credit Card");

        paymentMethodList = new ArrayList<>();
        paymentMethodList.add(paymentMethod);
    }

    @Test
    void testGetPayment_Methods() {
        when(paymentMethodRepository.findAll()).thenReturn(paymentMethodList);

        ArrayList<Payment_Method> result = paymentMethodService.getPayment_Methods();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(paymentMethodRepository).findAll();
    }

    @Test
    void testSavePayment_Method() {
        when(paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);

        Payment_Method saved = paymentMethodService.savePayment_Method(paymentMethod);

        assertNotNull(saved);
        verify(paymentMethodRepository).save(paymentMethod);
    }

    @Test
    void testSavePayment_Methods() {
        when(paymentMethodRepository.saveAll(paymentMethodList)).thenReturn(paymentMethodList);

        ArrayList<Payment_Method> savedList = paymentMethodService.savePayment_Methods(paymentMethodList);

        assertNotNull(savedList);
        assertEquals(1, savedList.size());
        verify(paymentMethodRepository).saveAll(paymentMethodList);
    }

    @Test
    void testGetPayment_MethodById_Found() {
        when(paymentMethodRepository.findById(1L)).thenReturn(Optional.of(paymentMethod));

        Optional<Payment_Method> found = paymentMethodService.getPayment_MethodById(1L);

        assertTrue(found.isPresent());
        assertEquals(paymentMethod, found.get());
        verify(paymentMethodRepository).findById(1L);
    }

    @Test
    void testGetPayment_MethodById_NotFound() {
        when(paymentMethodRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Payment_Method> found = paymentMethodService.getPayment_MethodById(1L);

        assertFalse(found.isPresent());
        verify(paymentMethodRepository).findById(1L);
    }

    @Test
    void testUpdatePayment_Method() {
        Payment_Method updatedData = new Payment_Method();
        updatedData.setName("Debit Card");

        when(paymentMethodRepository.findById(1L)).thenReturn(Optional.of(paymentMethod));
        when(paymentMethodRepository.save(any(Payment_Method.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment_Method updated = paymentMethodService.updatePayment_Method(updatedData, 1L);

        assertEquals("Debit Card", updated.getName());
        verify(paymentMethodRepository).findById(1L);
        verify(paymentMethodRepository).save(any(Payment_Method.class));
    }

    @Test
    void testDeletePayment_Method_Success() {
        doNothing().when(paymentMethodRepository).deleteById(1L);

        boolean result = paymentMethodService.deletePayment_Method(1L);

        assertTrue(result);
        verify(paymentMethodRepository).deleteById(1L);
    }

    @Test
    void testDeletePayment_Method_Failure() {
        doThrow(new RuntimeException("Error deleting")).when(paymentMethodRepository).deleteById(1L);

        boolean result = paymentMethodService.deletePayment_Method(1L);

        assertFalse(result);
        verify(paymentMethodRepository).deleteById(1L);
    }
    
}
