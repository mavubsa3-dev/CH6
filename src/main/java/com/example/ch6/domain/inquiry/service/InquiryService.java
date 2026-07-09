package com.example.ch6.domain.inquiry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ch6.common.entity.Menu;
import com.example.ch6.domain.inquiry.model.response.GetCoffeMenuResponse;
import com.example.ch6.domain.inquiry.repository.InquiryRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryService {

	private final InquiryRepository inquiryRepository;

	public List<GetCoffeMenuResponse> findCoffeeMenu(){
		List<Menu> list = inquiryRepository.findAll();
		if(list.isEmpty()){
			throw new IllegalArgumentException("등록된 상품이 없습니다.");
		}

		return list.stream()
			.map(result -> new GetCoffeMenuResponse(result.getId(), result.getName(), result.getPrice(),
				result.getDescription()))
			.toList();
	}

	public GetCoffeMenuResponse findOneCoffeeMenu(Long menuId){
		Menu menu = inquiryRepository.findById(menuId).orElseThrow(
			() -> new IllegalArgumentException("등록된 상품이 아닙니다.")
		);

		return GetCoffeMenuResponse.from(menu);
	}
}
