package com.example.ch6.common.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();


	private Order(User user, List<OrderItem> orderItems,  String orderNumber, Integer totalPrice){
		this.user = user;
		this.orderItems = orderItems;
		this.orderNumber = orderNumber;
		this.totalPrice = totalPrice;
	}

	public static Order From(User user, List<OrderItem> orderItems, String orderNumber, Integer totalPrice){
		return new Order(user, orderItems, orderNumber, totalPrice);
	}


}
