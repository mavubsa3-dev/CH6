package com.example.ch6.domain.order.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
	@NotNull(message = "사용자 ID를 입력하세요") Long userId,
	@NotNull(message = "메뉴 ID를 입력하세요") Long menuId,
	@NotNull(message = "가격을 입력하세요")
	@Min(value = 0, message = "가격은 0원 이상이어야 합니다") Integer price
) {
}
