package net.springboot.practice.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import net.springboot.practice.model.Project;

public interface ProjectRepository extends JpaRepository<Project , Long>{
	List<Project> findAll(Specification<Project> specification);
}
