package com.example.ch6.domain.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ch6.common.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("""
		select o
		from Order o
		where o.status = 'PENDING_PAYMENT' AND o.createdAt <= :limitTime
		""")
	List<Order> findGhostOrder(LocalDateTime limitTime);
}
