package com.example.ch6.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.ch6.common.entity.Menu;

import jakarta.persistence.LockModeType;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("""
		select m
		from Menu m
		where m.id IN :menuIds
		order by m.id asc
	""")
	List<Menu> findAllByIdWithLock(List<Long> menuIds);

}
