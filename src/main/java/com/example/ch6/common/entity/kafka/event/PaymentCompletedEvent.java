package com.example.ch6.common.entity.kafka.event;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompletedEvent {

	private Long paymentId;
	private Long userId;
	private String orderNumber;
	private Map<String, Integer> orderMenus;
	private Integer totalAmount;
	private Integer balance;
	private String paidAt;
}
