package net.springboot.practice.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import net.springboot.practice.model.Allocations;

public interface AllocationRepository extends JpaRepository<Allocations, Long> {
	List<Allocations> findAll(Specification<Allocations> specification);
}
