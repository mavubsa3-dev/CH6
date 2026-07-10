package com.example.ch6.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ch6.common.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
