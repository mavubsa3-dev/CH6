package com.example.ch6.domain.cartitem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ch6.domain.cartitem.model.request.AddCartItemRequest;
import com.example.ch6.domain.cartitem.model.request.UpdateQuantityRequest;
import com.example.ch6.domain.cartitem.model.response.AddCartItemResponse;
import com.example.ch6.domain.cartitem.model.response.GetItemListResponse;
import com.example.ch6.domain.cartitem.model.response.GetItemResponse;
import com.example.ch6.domain.cartitem.service.CartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartItem")
public class CartItemController {

	private final CartItemService cartItemService;

	@PostMapping("/{userId}")
	public ResponseEntity<AddCartItemResponse> addItem(@PathVariable Long userId, @RequestBody AddCartItemRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(cartItemService.addItem(userId, request));
	}

	@GetMapping("/list/{userId}")
	public ResponseEntity<GetItemListResponse> getItem(@PathVariable Long userId){

		return ResponseEntity.status(HttpStatus.OK).body(cartItemService.getItem(userId));
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<String> updateQuantity(@PathVariable Long userId, @RequestBody UpdateQuantityRequest request){

		cartItemService.updateQuantity(userId, request);
		return ResponseEntity.status(HttpStatus.OK).body("수량이 성공적으로 변경되었습니다.");
	}
}
