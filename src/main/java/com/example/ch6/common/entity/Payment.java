package com.example.ch6.common.entity;

import java.time.LocalDateTime;
import com.example.ch6.common.status.PaymentStatus;
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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@Column(name = "total_amount", nullable = false)
	private Integer totalPrice;

	@Column(name = "user_balance", nullable = false)
	private Integer balance;

	@Column(name = "paid_time", nullable = false)
	private LocalDateTime paidAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", nullable = false)
	private PaymentStatus paymentStatus;


	private Payment(Order order, Integer totalPrice, Integer balance, PaymentStatus status, LocalDateTime paidAt){
		this.order = order;
		this.totalPrice = totalPrice;
		this.paymentStatus = status;
		this.balance = balance;
		this.paidAt = paidAt;
	}

	public static Payment From(Order order, Integer totalPrice, Integer usePoint, PaymentStatus status, LocalDateTime paidAt){
		return new Payment(order, totalPrice, usePoint, status, paidAt);
	}



}
