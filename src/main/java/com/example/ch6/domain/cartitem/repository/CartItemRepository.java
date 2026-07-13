package com.example.ch6.domain.cartitem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.ch6.common.entity.CartItem;
import com.example.ch6.common.entity.Menu;
import com.example.ch6.domain.cartitem.model.response.GetItemResponse;

import jakarta.persistence.LockModeType;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	void deleteAllByUserId(Long userId);

	Optional<CartItem> findByUser_idAndMenu_id(Long userId, Long menuId);

	@Query("""
			SELECT ci
			FROM CartItem ci
			JOIN FETCH ci.menu
			WHERE ci.user.id = :userId			
			""")
	List<CartItem> findByUserId(Long userId);

}
