package com.example.ch6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ch6Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch6Application.class, args);
	}

}
