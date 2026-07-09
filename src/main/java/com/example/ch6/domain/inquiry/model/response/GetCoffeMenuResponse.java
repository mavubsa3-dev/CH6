package com.example.ch6.domain.inquiry.model.response;

import com.example.ch6.common.entity.Menu;

public record GetCoffeMenuResponse(
	Long id,
	String name,
	Integer price,
	String description
) {
	public static GetCoffeMenuResponse from(Menu menu){
		return new GetCoffeMenuResponse(
			menu.getId(),
			menu.getName(),
			menu.getPrice(),
			menu.getDescription()
		);
	}
}
