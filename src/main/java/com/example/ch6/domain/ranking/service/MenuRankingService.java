package com.example.ch6.domain.ranking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.example.ch6.common.entity.redis.Ranking;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuRankingService {

	private final StringRedisTemplate stringRedisTemplate;

	public static final String MENU_RANKING_KEY = "menu:ranking:";

	public void increaseMenuRanking(Map<String, Integer> orderMenus, LocalDate currentDate){
		String key = MENU_RANKING_KEY + currentDate.toString();

		for(Map.Entry<String ,Integer> entry : orderMenus.entrySet()){
			String menuName = entry.getKey();
			Integer quantity = entry.getValue();

			stringRedisTemplate.opsForZSet().incrementScore(key, menuName, quantity);
		}
	}

	public List<Ranking> findMenuRanking(){
		LocalDate currentDate = LocalDate.now();

		List<String> Keys = List.of(
			MENU_RANKING_KEY + currentDate.toString(),
			MENU_RANKING_KEY + currentDate.minusDays(1).toString(),
			MENU_RANKING_KEY + currentDate.minusDays(2).toString(),
			MENU_RANKING_KEY + currentDate.minusDays(3).toString(),
			MENU_RANKING_KEY + currentDate.minusDays(4).toString(),
			MENU_RANKING_KEY + currentDate.minusDays(5).toString(),
			MENU_RANKING_KEY + currentDate.minusDays(6).toString()
		);

		String dKey = "MenuRanking:7Days:";

		stringRedisTemplate.opsForZSet().unionAndStore(Keys.get(0),
			Keys.subList(1, Keys.size()),
			dKey);

		Set<ZSetOperations.TypedTuple<String>> result = stringRedisTemplate.opsForZSet().reverseRangeWithScores(dKey, 0, 2);

		if(result == null){
			return Collections.emptyList();
		}

		return result.stream()
			.map(tuple -> new Ranking(tuple.getValue(), tuple.getScore()))
			.toList();
	}
}
