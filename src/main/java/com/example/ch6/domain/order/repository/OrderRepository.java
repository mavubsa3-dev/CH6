package com.example.ch6.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ch6.common.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
