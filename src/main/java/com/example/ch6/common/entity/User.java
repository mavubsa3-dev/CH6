package com.example.ch6.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_email", nullable = false)
	private String email;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_name", nullable = false)
	private String name;

	@Column(name = "point_balance", nullable = false)
	private Integer balance;

	public User(String email, String password, String name, Integer balance){
		this.email = email;
		this.password = password;
		this.name = name;
		this.balance = balance;
	}

}
