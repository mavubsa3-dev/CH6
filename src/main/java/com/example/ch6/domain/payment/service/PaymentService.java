package com.example.ch6.domain.payment.service;

import org.springframework.stereotype.Service;

import com.example.ch6.domain.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
}
