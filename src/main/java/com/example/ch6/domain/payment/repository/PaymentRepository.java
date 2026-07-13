package com.example.ch6.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ch6.common.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	@Query("""
			select p
			from Payment p
			join fetch p.order o
			where o.id = :orderId
					and o.user.id = :userId
		""")
	Payment findByOrderIdAndUserId(Long orderId, Long userId);

	boolean existsByOrderId(Long orderId);
}
