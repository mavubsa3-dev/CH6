package com.example.ch6.domain.ranking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ch6.common.entity.redis.Ranking;
import com.example.ch6.domain.ranking.service.MenuRankingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking/menu")
public class MenuRankingController {

	private final MenuRankingService menuRankingService;

	@GetMapping
	public ResponseEntity<List<Ranking>> getMenuRanking(){
		return ResponseEntity.ok(menuRankingService.findMenuRanking());
	}
}
