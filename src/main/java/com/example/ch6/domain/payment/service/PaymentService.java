package com.example.ch6.domain.payment.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.Order;
import com.example.ch6.common.entity.OrderItem;
import com.example.ch6.common.entity.Payment;
import com.example.ch6.common.entity.PointHistory;
import com.example.ch6.common.entity.User;
import com.example.ch6.common.entity.kafka.event.PaymentCompletedEvent;
import com.example.ch6.common.status.OrderStatus;
import com.example.ch6.common.status.PaymentStatus;
import com.example.ch6.common.status.PointType;
import com.example.ch6.domain.cartitem.repository.CartItemRepository;
import com.example.ch6.domain.order.repository.OrderRepository;
import com.example.ch6.domain.payment.model.request.PaymentRequest;
import com.example.ch6.domain.payment.model.response.PaymentResponse;
import com.example.ch6.domain.payment.producer.PaymentProducer;
import com.example.ch6.domain.payment.repository.PaymentRepository;
import com.example.ch6.domain.pointhistory.repository.PointHistoryRepository;
import com.example.ch6.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final CartItemRepository cartItemRepository;
	private final PaymentProducer producer;

	@Transactional
	public PaymentResponse createPayment(PaymentRequest request){

		User user = userRepository.findById(request.userId()).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
		);

		Order order = orderRepository.findById(request.orderId()).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 주문입니다.")
		);

		if(order.getStatus() != OrderStatus.PENDING_PAYMENT){
			throw new IllegalArgumentException("결제 가능한 주문이 없습니다.");
		}

		if(paymentRepository.existsByOrderId(order.getId())){
			throw new IllegalArgumentException("이미 결제가 완료된 주문입니다.");
		}

		validPayment(order);

		Payment payment = Payment.From(order, order.getTotalPrice(), order.getUser().getBalance(), PaymentStatus.SUCCESS, LocalDateTime.now());
		paymentRepository.save(payment);
		order.completed();

		List<String> orderedMenusName = order.getOrderItems().stream()
			.map(orderItem -> orderItem.getMenu().getName() + " " + orderItem.getQuantity() + "잔")
			.toList();

		Map<String, Integer> orderMenus = order.getOrderItems().stream()
				.collect(Collectors.toMap(
					orderItem -> orderItem.getMenu().getName(),
					OrderItem::getQuantity
				));

		sendHistory(payment, orderMenus, user.getBalance());

		cartItemRepository.deleteAllByUserId(request.userId());

		return PaymentResponse.from(payment, orderedMenusName, user.getBalance());

	}

	private void validPayment(Order order){
		User user = order.getUser();
		user.deductBalance(order.getTotalPrice());
	}

	private void sendHistory(Payment payment, Map<String, Integer> orderMenus, Integer balance){
		String paidAt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

		Order order = payment.getOrder();

		PaymentCompletedEvent event = PaymentCompletedEvent.builder()
			.paymentId(payment.getId())
			.userId(order.getUser().getId())
			.orderNumber(payment.getOrder().getOrderNumber())
			.orderMenus(orderMenus)
			.totalAmount(payment.getTotalPrice())
			.balance(balance)
			.paidAt(paidAt)
			.build();

		producer.send(event);
	}
}
