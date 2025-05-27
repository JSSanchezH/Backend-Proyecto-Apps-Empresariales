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

import com.proyect.Human_Resources.models.Payment_Method;
import com.proyect.Human_Resources.services.Payment_MethodService;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @Autowired
    private Payment_MethodService paymentMethodService;

    @GetMapping
    public ArrayList<Payment_Method> getPayment_Methods() {
        return paymentMethodService.getPayment_Methods();
    }

    @PostMapping
    public Payment_Method savePayment_Method(@RequestBody Payment_Method paymentMethod) {
        return paymentMethodService.savePayment_Method(paymentMethod);
    }

    @PostMapping("/batch")
    public ArrayList<Payment_Method> savePayment_Methods(@RequestBody ArrayList<Payment_Method> paymentMethods) {
        return paymentMethodService.savePayment_Methods(paymentMethods);
    }

    @GetMapping(path = "/{id}")
    public Optional<Payment_Method> getPayment_MethodById(@PathVariable("id") long id) {
        return paymentMethodService.getPayment_MethodById(id);
    }

    @PutMapping(path = "/{id}")
    public Payment_Method updatePayment_Method(@RequestBody Payment_Method paymentMethod, @PathVariable("id") long id) {
        return paymentMethodService.updatePayment_Method(paymentMethod, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deletePayment_Method(@PathVariable("id") long id) {
        boolean ok = paymentMethodService.deletePayment_Method(id);
        if (ok) {
            return "Payment method deleted successfully";
        } else {
            return "Error deleting payment method";
        }
    }
}
