package com.example.ch6.domain.cartitem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.CartItem;
import com.example.ch6.common.entity.Menu;
import com.example.ch6.common.entity.User;
import com.example.ch6.domain.cartitem.model.request.AddCartItemRequest;
import com.example.ch6.domain.cartitem.model.request.UpdateQuantityRequest;
import com.example.ch6.domain.cartitem.model.response.AddCartItemResponse;
import com.example.ch6.domain.cartitem.model.response.GetItemListResponse;
import com.example.ch6.domain.cartitem.model.response.GetItemResponse;
import com.example.ch6.domain.cartitem.repository.CartItemRepository;
import com.example.ch6.domain.repository.MenuRepository;
import com.example.ch6.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final MenuRepository menuRepository;


	@Transactional
	public AddCartItemResponse addItem(Long userId, AddCartItemRequest request){

		Optional<CartItem> item = cartItemRepository.findByUser_idAndMenu_id(userId, request.menuId());

		if(item.isPresent()){
			CartItem addItem = item.get();
			addItem.addQuantity(request.quantity());
			return AddCartItemResponse.from(addItem);
		}else{
			User user = userRepository.findById(userId).orElseThrow(
				() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
			);

			Menu menu = menuRepository.findById(request.menuId()).orElseThrow(
				() -> new IllegalArgumentException("존재하지 않는 메뉴입니다.")
			);

			CartItem addItem = CartItem.from(user, menu, request.quantity());
			CartItem savedItem = cartItemRepository.save(addItem);

			return AddCartItemResponse.from(savedItem);
		}
	}

	@Transactional(readOnly = true)
	public GetItemListResponse getItem(Long userId){
		List<GetItemResponse> items = cartItemRepository.findByUserId(userId).stream()
			.map(GetItemResponse::from)
			.toList();

		int totalPrice = items.stream().mapToInt(item -> item.price() * item.quantity()).sum();

		return new GetItemListResponse(items, totalPrice);
	}

	@Transactional
	public void updateQuantity(Long userId, UpdateQuantityRequest request){
		CartItem item = cartItemRepository.findByUser_idAndMenu_id(userId, request.menuId()).orElseThrow(
			() -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
		);

		item.changeQuantity(request.quantity());
	}
}
