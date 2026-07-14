package com.example.ch6.domain.pointhistory.service;

import java.awt.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.PointHistory;
import com.example.ch6.common.entity.User;
import com.example.ch6.common.entity.kafka.event.PaymentCompletedEvent;
import com.example.ch6.common.status.PointType;
import com.example.ch6.domain.pointhistory.model.request.ChargePointRequest;
import com.example.ch6.domain.pointhistory.repository.PointHistoryRepository;
import com.example.ch6.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointHistoryService {

	private final PointHistoryRepository pointHistoryRepository;
	private final UserRepository userRepository;

	@Transactional
	public String chargePoint(ChargePointRequest request){
		User user = userRepository.findById(request.userId()).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
		);

		user.chargeBalance(request.point());

		PointHistory pointHistory = PointHistory.from(user, PointType.CHARGE, request.point());
		pointHistoryRepository.save(pointHistory);

		return user.getId() + "에 " + request.point() + " 포인트를 충전했습니다. 잔액 : " + user.getBalance();
	}

	@Transactional
	public void savePaymentHistory(PaymentCompletedEvent event){
		User user = userRepository.findById(event.getUserId()).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
		);

		PointHistory pointHistory = PointHistory.from(user, PointType.USE, event.getTotalAmount());
		pointHistoryRepository.save(pointHistory);

		log.info("[DB] : 결제 기록 저장 완료 paymentId : {}, userId : {} ", event.getPaymentId(), user.getId());
	}
}
