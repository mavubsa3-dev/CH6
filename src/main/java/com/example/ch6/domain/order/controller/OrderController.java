package com.example.ch6.domain.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ch6.domain.order.model.request.CreateOrderRequest;
import com.example.ch6.domain.order.model.response.CreateOrderResponse;
import com.example.ch6.domain.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<CreateOrderResponse> crateOrder(@Valid @RequestBody CreateOrderRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(request));
	}
}
