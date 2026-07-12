package com.example.ch6.domain.order.model.request;

import java.util.List;

import com.example.ch6.common.entity.Order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
	@NotNull(message = "사용자 ID를 입력하세요") Long userId,
	@NotEmpty(message = "장바구니 ID를 입력하세요") List<Long> cartItemId,
	@NotNull(message = "사용할 포인트를 입력해주세요.")
	@Min(value = 0, message = "포인트는 0 이상이어야 합니다.") Integer usePoint

) {
}
