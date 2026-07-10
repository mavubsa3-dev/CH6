package com.example.ch6.domain.order.service;

import org.springframework.stereotype.Service;

import com.example.ch6.domain.order.model.request.CreateOrderRequest;
import com.example.ch6.domain.order.model.response.CreateOrderResponse;
import com.example.ch6.domain.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	public CreateOrderResponse crateOrder(Long memberId, CreateOrderRequest request){

	}
}
