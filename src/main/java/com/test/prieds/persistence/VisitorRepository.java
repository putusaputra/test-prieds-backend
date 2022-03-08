package com.test.prieds.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.prieds.model.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
	List<Visitor> findByNameContainingAndVisitDate(String name, LocalDate visitDate);
	Page<Visitor> findByNameContainingOrVisitDate(String name, LocalDate visitDate, Pageable pageable);
	Page<Visitor> findByNameContaining(String name, Pageable pageable);
}
