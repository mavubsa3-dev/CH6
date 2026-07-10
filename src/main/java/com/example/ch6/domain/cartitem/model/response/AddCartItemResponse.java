package com.example.ch6.domain.cartitem.model.response;

import java.time.LocalDateTime;

import com.example.ch6.common.entity.CartItem;
import com.example.ch6.domain.cartitem.model.request.AddCartItemRequest;

public record AddCartItemResponse(
	Long cartItemId,
	Long menuId,
	Integer quantity,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {

	public static AddCartItemResponse from(CartItem item){
		return new AddCartItemResponse(
			item.getId(),
			item.getMenu().getId(),
			item.getQuantity(),
			item.getCreatedAt(),
			item.getUpdatedAt()
		);
	}
}
