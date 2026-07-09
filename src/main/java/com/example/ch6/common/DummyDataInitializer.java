package com.example.ch6.common;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ch6.common.entity.Menu;
import com.example.ch6.domain.inquiry.repository.InquiryRepository;

import lombok.RequiredArgsConstructor;

@Component // 스프링 빈으로 등록되어 서버 실행 시 자동 실행됨
@RequiredArgsConstructor
public class DummyDataInitializer implements CommandLineRunner {

	private final InquiryRepository inquiryRepository;

	@Override
	public void run(String... args) throws Exception {
		// 이미 데이터가 있다면 중복 생성하지 않도록 방어 로직 추가
		if (inquiryRepository.count() == 0) {
			List<Menu> dummyMenus = List.of(
				Menu.from("아이스 아메리카노", 2000, "시원하고 깔끔한 맛의 아메리카노"),
				Menu.from("카페 라떼", 3000, "고소한 우유가 듬뿍 들어간 라떼"),
				Menu.from("바닐라 라떼", 3500, "달콤한 바닐라 시럽이 추가된 라떼"),
				Menu.from("콜드브루", 3500, "12시간 동안 차갑게 우려낸 깔끔한 커피"),
				Menu.from("딸기 스무디", 4000, "신선한 딸기를 갈아 만든 상큼한 스무디")
			);

			inquiryRepository.saveAll(dummyMenus);
			System.out.println("✅ 커피 메뉴 더미 데이터 5건이 성공적으로 생성되었습니다!");
		}
	}
}
