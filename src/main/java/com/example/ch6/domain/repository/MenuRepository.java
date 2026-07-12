package com.example.ch6.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ch6.common.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
