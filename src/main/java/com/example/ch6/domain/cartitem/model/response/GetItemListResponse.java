package com.example.ch6.domain.cartitem.model.response;

import java.util.List;

public record GetItemListResponse(
	List<GetItemResponse> cartItems,
	int totalPrice
) {
}
