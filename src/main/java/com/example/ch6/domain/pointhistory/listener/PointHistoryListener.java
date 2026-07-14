package com.example.ch6.domain.pointhistory.listener;

import static com.example.ch6.common.entity.kafka.topic.KakfaTopic.*;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.ch6.common.entity.kafka.event.PaymentCompletedEvent;
import com.example.ch6.domain.pointhistory.service.PointHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PointHistoryListener {

	private final PointHistoryService pointHistoryService;

	@KafkaListener(
		topics = TOPIC_PAYMENT_COMPLETED,
		groupId = "payment-history-group",
		containerFactory = "paymentHistoryKafkaListenerContainerFactory"
	)
	public void consume(PaymentCompletedEvent event){

		log.info("[Consumer-Histroy] 결제 완료 이벤트 수신 paymentId : {}, orderNumber : {} ", event.getPaymentId(), event.getOrderNumber());

		pointHistoryService.savePaymentHistory(event);
	}
}
