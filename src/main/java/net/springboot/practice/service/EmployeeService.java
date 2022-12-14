package net.springboot.practice.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id);
	
	Employee updateEmployee(Employee employee , long id);
	
	void deleteEmployee(long id);

	List<Employee> searchByEmpNo(long empid);

	List<Employee> getAllEmployeesAlloc(String alloc);

	List<Employee> getAllocationByDate(String from);

	String addSearchFilter(long id);

	void save(MultipartFile file);
	void empDelete(long empId);



	//List<Employee> findAll(Specification<Employee> specification);
	
//	List<Employee> employeeFilter(Employee employee);
}
