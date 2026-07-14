package com.example.ch6.common.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.ch6.common.status.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_number", nullable = false, unique = true)
	private String orderNumber;


	@Column(name = "order_price", nullable = false)
	private Integer totalPrice;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();


	private Order(User user, String orderNumber, Integer totalPrice){
		this.user = user;
		this.orderNumber = orderNumber;
		this.totalPrice = totalPrice;
		this.status = OrderStatus.PENDING_PAYMENT;
	}

	public static Order From(User user, String orderNumber, Integer totalPrice){
		return new Order(user,  orderNumber, totalPrice);
	}

	public void cancelOrder(){
		if (this.status != OrderStatus.PENDING_PAYMENT) {
			throw new IllegalArgumentException("주문 상태가 올바르지 않습니다.");
		}

		for(OrderItem item : this.orderItems){
			item.getMenu().restoreStock(item.getQuantity());
		}
		this.status = OrderStatus.CANCELLED;
	}

	public void completed(){
		this.status = OrderStatus.COMPLETED;
	}

}
