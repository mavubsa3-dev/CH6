package com.example.ch6.domain.inquiry.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.Menu;
import com.example.ch6.domain.inquiry.model.response.GetCoffeMenuResponse;
import com.example.ch6.domain.inquiry.repository.InquiryRepository;
import com.example.ch6.domain.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryService {

	private final MenuRepository menuRepository;

	public List<GetCoffeMenuResponse> findCoffeeMenu(){
		return menuRepository.findAll().stream()
			.map(GetCoffeMenuResponse::from)
			.toList();
	}

	public GetCoffeMenuResponse findOneCoffeeMenu(Long menuId){
		Menu menu = menuRepository.findById(menuId).orElseThrow(
			() -> new IllegalArgumentException("등록된 상품이 아닙니다.")
		);

		return GetCoffeMenuResponse.from(menu);
	}
}
