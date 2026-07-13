package com.example.ch6.domain.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.CartItem;
import com.example.ch6.common.entity.Menu;
import com.example.ch6.common.entity.Order;
import com.example.ch6.common.entity.OrderItem;
import com.example.ch6.common.entity.Payment;
import com.example.ch6.common.entity.PointHistory;
import com.example.ch6.common.entity.User;
import com.example.ch6.common.status.PaymentStatus;
import com.example.ch6.common.status.PointType;
import com.example.ch6.domain.cartitem.repository.CartItemRepository;
import com.example.ch6.domain.order.model.request.CreateOrderRequest;
import com.example.ch6.domain.order.model.response.CreateOrderResponse;
import com.example.ch6.domain.order.repository.OrderRepository;
import com.example.ch6.domain.payment.repository.PaymentRepository;
import com.example.ch6.domain.pointhistory.repository.PointHistoryRepository;
import com.example.ch6.domain.repository.MenuRepository;
import com.example.ch6.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final CartItemRepository cartItemRepository;
	private final MenuRepository menuRepository;

	@Transactional
	public CreateOrderResponse createOrder(CreateOrderRequest request){
		User user = userRepository.findById(request.userId()).orElseThrow(
			() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
		);

		List<CartItem> cartItems = cartItemRepository.findAllById(request.cartItemId());
		if(cartItems.isEmpty() || cartItems == null){
			throw new IllegalArgumentException("장바구니를 찾을 수 없습니다.");
		}

		List<Long> menuIds = cartItems.stream()
			.map(cartItem -> cartItem.getMenu().getId())
			.toList();

		menuRepository.findAllByIdWithLock(menuIds);

		for (CartItem cartItem : cartItems) {
			cartItem.getMenu().decreaseStock(cartItem.getQuantity());
		}

		Integer totalAmount = cartItems.stream()
			.mapToInt(cartItem -> cartItem.getQuantity() * cartItem.getMenu().getPrice()).sum();

		String orderNumber = "OrderNuber - " + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		List<OrderItem> orderItems = new ArrayList<>();
		Order order = Order.From(user, orderItems,orderNumber, totalAmount);

		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = OrderItem.from(cartItem.getMenu(), cartItem.getMenu().getPrice(), cartItem.getQuantity());

			orderItem.assingOrder(order);
			order.getOrderItems().add(orderItem);
		}
		orderRepository.save(order);


		return new CreateOrderResponse(order.getId(), orderNumber, totalAmount, request.usePoint(), user.getBalance());

	}
}
