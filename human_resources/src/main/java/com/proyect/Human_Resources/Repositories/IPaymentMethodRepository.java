package com.proyect.Human_Resources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.Human_Resources.models.Payment_Method;

public interface IPaymentMethodRepository extends JpaRepository<Payment_Method, Long> {
    // Custom query methods can be defined here if needed    
}