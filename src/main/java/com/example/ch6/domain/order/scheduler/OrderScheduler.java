package com.example.ch6.domain.order.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.Order;
import com.example.ch6.domain.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderScheduler {

	private final OrderRepository orderRepository;

	@Scheduled(cron = "0 0/2 * * * *")
	@Transactional
	public void findGhostOrder(){
		LocalDateTime time = LocalDateTime.now().minusMinutes(1);

		List<Order> ghostOrders = orderRepository.findGhostOrder(time);

		if(ghostOrders.isEmpty()){
			return;
		}

		log.info("[Scheduler] 30분 경과 {}개의 주문 취소", ghostOrders.size());

		for (Order order : ghostOrders) {
			order.cancelOrder();
			log.info("{}번 주문 취소 완료", order.getOrderNumber());
		}
	}
}
