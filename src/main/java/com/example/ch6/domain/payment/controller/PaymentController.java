package com.example.ch6.domain.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ch6.domain.payment.model.request.PaymentRequest;
import com.example.ch6.domain.payment.model.response.PaymentResponse;
import com.example.ch6.domain.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(paymentService.createPayment(request));
	}
}
