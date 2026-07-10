package com.example.ch6.common;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ch6.common.entity.Menu;
import com.example.ch6.common.entity.User;
import com.example.ch6.domain.inquiry.repository.InquiryRepository;
import com.example.ch6.domain.repository.MenuRepository;
import com.example.ch6.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component // 스프링 빈으로 등록되어 서버 실행 시 자동 실행됨
@RequiredArgsConstructor
public class DummyDataInitializer implements CommandLineRunner {

	private final MenuRepository menuRepository;
	private final UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// 이미 데이터가 있다면 중복 생성하지 않도록 방어 로직 추가
		if (menuRepository.count() == 0) {
			List<Menu> dummyMenus = List.of(
				Menu.from("아이스 아메리카노", 2000, "시원하고 깔끔한 맛의 아메리카노", 10),
				Menu.from("카페 라떼", 3000, "고소한 우유가 듬뿍 들어간 라떼", 10),
				Menu.from("바닐라 라떼", 3500, "달콤한 바닐라 시럽이 추가된 라떼", 10),
				Menu.from("콜드브루", 3500, "12시간 동안 차갑게 우려낸 깔끔한 커피", 10),
				Menu.from("딸기 스무디", 4000, "신선한 딸기를 갈아 만든 상큼한 스무디", 10)
			);

			menuRepository.saveAll(dummyMenus);
			System.out.println("✅ 커피 메뉴 더미 데이터 5건이 성공적으로 생성되었습니다!");
		}

		if (userRepository.count() == 0) {
			List<User> dummyMenus = List.of(
				User.From("email@email.com", "1234", "김", 0),
				User.From("emai2@email.com", "1234", "나", 2000),
				User.From("emai3@email.com", "1234", "박", 4000),
				User.From("emai4@email.com", "1234", "이", 8000),
				User.From("emai5@email.com", "1234", "최", 10000)
			);

			userRepository.saveAll(dummyMenus);
			System.out.println("✅ 커피 메뉴 더미 데이터 5건이 성공적으로 생성되었습니다!");
		}
	}
}
