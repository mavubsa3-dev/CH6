package com.example.ch6.domain.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ch6.common.entity.Menu;

public interface InquiryRepository extends JpaRepository<Menu, Long> {
}
