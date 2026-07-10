package com.example.ch6.domain.cartitem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ch6.common.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findByUser_idAndMenu_id(Long userId, Long menuId);
}
