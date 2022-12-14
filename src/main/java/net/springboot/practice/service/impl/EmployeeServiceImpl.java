package net.springboot.practice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//import java.util.function.Predicate;

import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import net.springboot.practice.allocationDAO.employeeDAO;
import net.springboot.practice.exception.ResourceNotFoundException;
import net.springboot.practice.helper.EmployeeHelper;
import net.springboot.practice.helper.LoginHelper;
import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;
import net.springboot.practice.model.Login;
import net.springboot.practice.repository.EmployeeRepository;
import net.springboot.practice.service.EmployeeService;
import org.springframework.data.jpa.domain.Specification;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
	private EmployeeRepository employeeRepository;
	@Autowired
	private employeeDAO dao = new employeeDAO();
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Employee", "id", id);
		}
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check id exists
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee","Id",id) );
		
		existingEmployee.setEmployeeId(employee.getEmployeeId());
		existingEmployee.setEmp_name(employee.getEmp_name());
		existingEmployee.setDateOfJoining(employee.getDateOfJoining());
		existingEmployee.setSkill_Set(employee.getSkill_Set());
		existingEmployee.setBuss_Unit(employee.getBuss_Unit());
		existingEmployee.setAge(employee.getAge());
		existingEmployee.setSupervisor_Name(employee.getSupervisor_Name());
		existingEmployee.setDesignation(employee.getDesignation());
		existingEmployee.setGrade(employee.getGrade());
		existingEmployee.setLocation(employee.getLocation());
		existingEmployee.setPro_Assign(employee.getPro_Assign());
		existingEmployee.setPro_Name(employee.getPro_Name());
		existingEmployee.setComments(employee.getComments());
		existingEmployee.setCreated_Date(employee.getCreated_Date());
		existingEmployee.setUpdated_Date(employee.getUpdated_Date());
		existingEmployee.setDeputation(employee.getDeputation());
		existingEmployee.setTSR(employee.getTSR());
		existingEmployee.setAR(employee.getAR());
		
		// save existing employee
		
		employeeRepository.save(existingEmployee);
		return existingEmployee;
				
		
	}


	@Override
	public void deleteEmployee(long id) {
		
		//check existing 
		employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee","Id",id) );
		
		employeeRepository.deleteById(id);
		
	}


	@Override
	public List<Employee> searchByEmpNo(long empid) {
		return dao.searchByEmp(empid);
	}


	@Override
	public List<Employee> getAllEmployeesAlloc(String alloc) {
		return dao.getAllEmployeesAlloc(alloc);
	}


	@Override
	public List<Employee> getAllocationByDate(String dateFrom) {
		// TODO Auto-generated method stub
		return dao.getAllocationByDate(dateFrom);
	}


	@Override
	public String addSearchFilter(long id) {
		// TODO Auto-generated method stub
		return dao.addSearchFilter(id);	
	}


	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		
			 try {
				List<Employee> employee = EmployeeHelper.convertExcelToListOfEmployee(file.getInputStream());
				this.employeeRepository.saveAll(employee);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
		
	}
	
	@Override
    public void empDelete(long empId) {
        dao.empDelete(empId);
    }


	
	
		
	
	}



