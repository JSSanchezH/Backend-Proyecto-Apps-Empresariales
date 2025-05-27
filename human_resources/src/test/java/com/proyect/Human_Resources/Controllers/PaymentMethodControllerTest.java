package com.proyect.Human_Resources.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.Human_Resources.Security.ApiKeyAuthFilter;
import com.proyect.Human_Resources.controllers.PaymentMethodController;
import com.proyect.Human_Resources.models.*;
import com.proyect.Human_Resources.services.Payment_MethodService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = PaymentMethodController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ApiKeyAuthFilter.class)
    }
)
public class PaymentMethodControllerTest {

     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Payment_MethodService paymentMethodService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllPaymentMethods() throws Exception {
        ArrayList<Payment_Method> paymentMethods = new ArrayList<>();
        paymentMethods.add(new Payment_Method(1L, "Credit Card"));
        paymentMethods.add(new Payment_Method(2L, "Bank Transfer"));

        Mockito.when(paymentMethodService.getPayment_Methods()).thenReturn(paymentMethods);

        mockMvc.perform(get("/payment-methods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Credit Card")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Bank Transfer")));
    }

    @Test
    void testGetPaymentMethodById() throws Exception {
        Payment_Method paymentMethod = new Payment_Method(1L, "Credit Card");

        Mockito.when(paymentMethodService.getPayment_MethodById(1L)).thenReturn(Optional.of(paymentMethod));

        mockMvc.perform(get("/payment-methods/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Credit Card")));
    }

    @Test
    void testGetPaymentMethodByIdNotFound() throws Exception {
        Mockito.when(paymentMethodService.getPayment_MethodById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/payment-methods/999"))
                .andExpect(status().isOk());
    }

    @Test
    void testSavePaymentMethod() throws Exception {
        Payment_Method input = new Payment_Method(null, "Cash");
        Payment_Method saved = new Payment_Method(3L, "Cash");

        Mockito.when(paymentMethodService.savePayment_Method(Mockito.any(Payment_Method.class))).thenReturn(saved);

        mockMvc.perform(post("/payment-methods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Cash")));
    }

    @Test
    void testUpdatePaymentMethod() throws Exception {
        Payment_Method updated = new Payment_Method(1L, "Updated Payment Method");

        Mockito.when(paymentMethodService.updatePayment_Method(Mockito.any(Payment_Method.class), Mockito.eq(1L))).thenReturn(updated);

        mockMvc.perform(put("/payment-methods/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Payment Method")));
    }

    @Test
    void testDeletePaymentMethodSuccess() throws Exception {
        Mockito.when(paymentMethodService.deletePayment_Method(1L)).thenReturn(true);

        mockMvc.perform(delete("/payment-methods/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment method deleted successfully"));
    }

    @Test
    void testDeletePaymentMethodFail() throws Exception {
        Mockito.when(paymentMethodService.deletePayment_Method(1L)).thenReturn(false);

        mockMvc.perform(delete("/payment-methods/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error deleting payment method"));
    }

    @Test
    void testSavePaymentMethodsBatch() throws Exception {
        ArrayList<Payment_Method> inputPaymentMethods = new ArrayList<>();
        inputPaymentMethods.add(new Payment_Method(null, "Debit Card"));
        inputPaymentMethods.add(new Payment_Method(null, "PayPal"));

        ArrayList<Payment_Method> savedPaymentMethods = new ArrayList<>();
        savedPaymentMethods.add(new Payment_Method(4L, "Debit Card"));
        savedPaymentMethods.add(new Payment_Method(5L, "PayPal"));

        Mockito.when(paymentMethodService.savePayment_Methods(Mockito.any(ArrayList.class))).thenReturn(savedPaymentMethods);

        mockMvc.perform(post("/payment-methods/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputPaymentMethods)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].id", is(4)))
                .andExpect(jsonPath("$[0].name", is("Debit Card")))
                .andExpect(jsonPath("$[1].id", is(5)))
                .andExpect(jsonPath("$[1].name", is("PayPal")));
    }
    
}
