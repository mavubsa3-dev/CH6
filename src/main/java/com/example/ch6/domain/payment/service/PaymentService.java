package com.example.ch6.domain.payment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.Order;
import com.example.ch6.common.entity.Payment;
import com.example.ch6.common.entity.PointHistory;
import com.example.ch6.common.entity.User;
import com.example.ch6.common.status.PaymentStatus;
import com.example.ch6.common.status.PointType;
import com.example.ch6.domain.cartitem.repository.CartItemRepository;
import com.example.ch6.domain.order.repository.OrderRepository;
import com.example.ch6.domain.payment.model.request.PaymentRequest;
import com.example.ch6.domain.payment.model.response.PaymentResponse;
import com.example.ch6.domain.payment.repository.PaymentRepository;
import com.example.ch6.domain.pointhistory.repository.PointHistoryRepository;
import com.example.ch6.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	private final PointHistoryRepository pointHistoryRepository;
	private final UserRepository userRepository;
	private final CartItemRepository cartItemRepository;

	@Transactional
	public PaymentResponse createPayment(PaymentRequest request){

		User user = userRepository.findById(request.userId()).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
		);

		Order order = orderRepository.findById(request.orderId()).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 주문입니다.")
		);

		if(paymentRepository.existsByOrderId(order.getId())){
			throw new IllegalArgumentException("이미 결제가 완료된 주문입니다.");
		}

		validPayment(order);

		Payment payment = Payment.From(order, order.getTotalPrice(), order.getUser().getBalance(), PaymentStatus.SUCCESS, LocalDateTime.now());
		paymentRepository.save(payment);

		PointHistory pointHistory = PointHistory.from(user, PointType.USE, order.getTotalPrice());
		pointHistoryRepository.save(pointHistory);

		cartItemRepository.deleteAllByUserId(request.userId());

		List<String> orderedMenusName = order.getOrderItems().stream()
			.map(orderItem -> orderItem.getMenu().getName() + " " + orderItem.getQuantity() + "잔")
			.toList();

		return PaymentResponse.from(payment, orderedMenusName, user.getBalance());

	}

	private void validPayment(Order order){
		User user = order.getUser();
		user.deductBalance(order.getTotalPrice());
	}
}
