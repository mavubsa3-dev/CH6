package com.example.ch6.common.entity;

import com.example.ch6.common.status.PointType;

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
@Table(name = "point_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", nullable = false)
	private Payment payment;

	@Enumerated(EnumType.STRING)
	private PointType pointType;

	@Column(name = "points", nullable = false)
	private Integer amount;

	private PointHistory(Payment payment, PointType pointType, Integer amount){
		this.payment = payment;
		this.pointType = pointType;
		this.amount = amount;
	}

	public static PointHistory from(Payment payment, PointType pointType, Integer amount){
		return new PointHistory(payment, pointType, amount);
	}
}
