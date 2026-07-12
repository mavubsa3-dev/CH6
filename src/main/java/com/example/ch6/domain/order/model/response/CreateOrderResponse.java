package com.example.ch6.domain.order.model.response;

import com.example.ch6.common.entity.Order;


public record CreateOrderResponse(
	Long orderId,
	String orderNumber,
	Integer totalAmount,
	Integer usePoint,
	Integer balance
) {
	public static CreateOrderResponse from(Order order, Integer usePoint, Integer currentBalance) {
		return new CreateOrderResponse(
			order.getId(),
			order.getOrderNumber(),
			order.getTotalPrice(),
			usePoint,
			currentBalance
		);
	}
}
