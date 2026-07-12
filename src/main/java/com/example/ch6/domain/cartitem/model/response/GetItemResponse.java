package com.example.ch6.domain.cartitem.model.response;

import com.example.ch6.common.entity.CartItem;


public record GetItemResponse(
	Long id,
	Long menuId,
	String menuname,
	int price,
	int quantity,
	int stock
) {
	public static GetItemResponse from(CartItem item){
		return new GetItemResponse(
			item.getId(),
			item.getMenu().getId(),
			item.getMenu().getName(),
			item.getMenu().getPrice(),
			item.getQuantity(),
			item.getMenu().getStock()
		);
	}
}

