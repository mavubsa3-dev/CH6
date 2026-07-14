package com.example.ch6.domain.payment.producer;

import static com.example.ch6.common.entity.kafka.topic.KakfaTopic.*;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ch6.common.entity.kafka.event.PaymentCompletedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

	private final KafkaTemplate<String, PaymentCompletedEvent> paymentCompletedEventKafkaTemplate;

	public void send(PaymentCompletedEvent event){

		paymentCompletedEventKafkaTemplate.send(TOPIC_PAYMENT_COMPLETED, event);
	}
}
