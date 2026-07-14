package com.example.ch6.domain.ranking.listener;

import static com.example.ch6.common.entity.kafka.topic.KakfaTopic.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.ch6.common.entity.kafka.event.PaymentCompletedEvent;
import com.example.ch6.domain.ranking.service.MenuRankingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class MenuRankingListener {

	private final MenuRankingService service;

	@KafkaListener(
		topics = TOPIC_PAYMENT_COMPLETED,
		groupId = "menu-ranking-group",
		containerFactory = "menuRankingKafkaListenerContainerFactory"
	)
	public void consumer(PaymentCompletedEvent event){

		log.info("[상품 랭킹 조회] : 상품들 값 가져옴");

		LocalDate currentDate = LocalDate.parse(event.getPaidAt());

		service.increaseMenuRanking(event.getOrderMenus(), currentDate);
	}
}
