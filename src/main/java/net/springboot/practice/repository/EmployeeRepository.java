package net.springboot.practice.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springboot.practice.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
  List<Employee> findAll(Specification<Employee> specification);
}
