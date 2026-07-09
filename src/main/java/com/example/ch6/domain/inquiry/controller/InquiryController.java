package com.example.ch6.domain.inquiry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ch6.domain.inquiry.model.response.GetCoffeMenuResponse;
import com.example.ch6.domain.inquiry.service.InquiryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InquiryController {

	private final InquiryService inquiryService;

	@GetMapping("/api/menus")
	public ResponseEntity<List<GetCoffeMenuResponse>> findCoffeeMenu(){
		return ResponseEntity.status(HttpStatus.OK).body(inquiryService.findCoffeeMenu());
	}

	@GetMapping("/api/menus/{menuId}")
	public ResponseEntity<GetCoffeMenuResponse> findOneCoffeeMenu(@PathVariable Long menuId){
		return ResponseEntity.status(HttpStatus.OK).body(inquiryService.findOneCoffeeMenu(menuId));
	}
}
