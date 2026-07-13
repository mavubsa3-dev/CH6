package com.example.ch6.domain.payment.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
	@NotNull(message = "사용자 ID를 입력하세요") Long userId,
	@NotNull(message = "메뉴 ID를 입력하세요") Long orderId
) {
}
