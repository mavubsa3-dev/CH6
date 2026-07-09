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
@Table(name = "menus")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "menu_name", nullable = false)
	private String name;

	@Column(name = "menu_price", nullable = false)
	private Integer price;

	@Column(name = "menu_description", nullable = false)
	private String description;

	private Menu(String name, Integer price, String description){
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public static Menu from(String name, Integer price, String description){
		return new Menu(name, price, description);
	}
}
