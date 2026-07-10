package com.example.ch6.domain.cartitem.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddCartItemRequest(
	@NotNull(message = "상품 ID는 필수입니다.") Long menuId,
	@Min(value = 1, message = "수량은 1개 이상이어야 합니다.") Integer quantity
) {
}
