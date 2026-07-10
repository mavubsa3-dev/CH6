package com.example.ch6.common.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cartItems")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CartItem extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false)
	private Menu menu;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	private CartItem(User user, Menu menu, Integer quantity){
		this.user = user;
		this.menu = menu;
		this.quantity = quantity;
	}

	public static CartItem from(User user, Menu menu, Integer quantity){
		return new CartItem(user, menu, quantity);
	}

	public void addQuantity(int quantity){
		if(quantity < 1){
			throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
		}

		if(this.quantity + quantity > this.menu.getStock()){
			throw new IllegalArgumentException("수량이 부족합니다.");
		}

		this.quantity += quantity;
	}

	public void changeQuntity(int quantity){
		if(quantity < 1){
			throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
		}

		if(this.quantity + quantity > this.menu.getStock()){
			throw new IllegalArgumentException("수량이 부족합니다.");
		}

		this.quantity = quantity;
	}
}
