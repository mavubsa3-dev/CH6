package com.example.ch6.domain.pointhistory.service;

import org.springframework.stereotype.Service;

import com.example.ch6.common.entity.User;
import com.example.ch6.domain.pointhistory.model.request.ChargePointRequest;
import com.example.ch6.domain.pointhistory.repository.PointHistoryRepository;
import com.example.ch6.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

	private final PointHistoryRepository pointHistoryRepository;
	private final UserRepository userRepository;

	public String chargePoint(ChargePointRequest request){
		User user = userRepository.findById(request.userId()).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
		);

		user.chargeBalance(request.point());
		userRepository.save(user);

		return user.getId() + "에 " + request.point() + " 포인트를 충전했습니다. 잔액 : " + user.getBalance();
	}
}
