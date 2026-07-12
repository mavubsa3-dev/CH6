package com.example.ch6.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ch6.common.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
