package com.example.ch6.domain.payment.model.response;

import java.util.List;

import com.example.ch6.common.entity.Payment;

public record PaymentResponse(
	Long paymentId,
	String orderNumber,
	List<String> orderedMenus,
	Integer totalAmount,
	Integer balance
) {

	public static PaymentResponse from(Payment payment, List<String> orderedMenus, Integer balance){
		return new PaymentResponse(
			payment.getId(),
			payment.getOrder().getOrderNumber(),
			orderedMenus,
			payment.getTotalPrice(),
			balance
		);
	}
}
