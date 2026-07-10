package com.example.ch6.domain.pointhistory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ch6.domain.pointhistory.model.request.ChargePointRequest;
import com.example.ch6.domain.pointhistory.service.PointHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/point")
public class PointHistroyController {

	private final PointHistoryService pointHistoryService;

	@PostMapping
	public ResponseEntity<String> chargePoint(@RequestBody ChargePointRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(pointHistoryService.chargePoint(request));
	}
}
