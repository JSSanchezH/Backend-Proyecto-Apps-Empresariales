package com.proyect.Human_Resources.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.Repositories.IPaymentMethodRepository;
import com.proyect.Human_Resources.models.Payment_Method;

@Service
public class Payment_MethodService {

    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    public ArrayList<Payment_Method> getPayment_Methods() {
        return (ArrayList<Payment_Method>) paymentMethodRepository.findAll();
    }

    public Payment_Method savePayment_Method(Payment_Method paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public ArrayList<Payment_Method> savePayment_Methods(ArrayList<Payment_Method> paymentMethods) {
        return (ArrayList<Payment_Method>) paymentMethodRepository.saveAll(paymentMethods);
    }

    public Optional<Payment_Method> getPayment_MethodById(long id) {
        return paymentMethodRepository.findById(id);
    }

    public Payment_Method updatePayment_Method(Payment_Method paymentMethod, long id) {
        Payment_Method paymentMethodToUpdate = paymentMethodRepository.findById(id).get();
        paymentMethodToUpdate.setName(paymentMethod.getName());
        return paymentMethodRepository.save(paymentMethodToUpdate);
    }

    public boolean deletePayment_Method(long id) {
        try {
            paymentMethodRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
