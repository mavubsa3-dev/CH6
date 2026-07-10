package com.example.ch6.domain.pointhistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ch6.common.entity.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}
