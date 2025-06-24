package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
