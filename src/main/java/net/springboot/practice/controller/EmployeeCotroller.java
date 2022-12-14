package net.springboot.practice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.helper.EmployeeHelper;
import net.springboot.practice.helper.LoginHelper;
import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;
import net.springboot.practice.repository.EmployeeRepository;
import net.springboot.practice.service.EmployeeService;
import net.springboot.practice.service.impl.EmployeeServiceImpl;
import net.springboot.practice.service.impl.filters;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/employees/*")
public class EmployeeCotroller {
	
	private EmployeeService employeeService;
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	private EmployeeRepository employeerepo;

	public EmployeeCotroller(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//build create employee Rest API
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		System.out.println(employee);
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	@CrossOrigin("http://localhost:3000*")
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeid){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeid), HttpStatus.OK);	
	}
	@CrossOrigin("http://localhost:3000")
	@PutMapping("{id}")
	public ResponseEntity<Employee>updateEmploye(@PathVariable("id") long id , @RequestBody Employee employee){
		
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee , id), HttpStatus.OK); 
		
	}
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////******************************************///////////////////////////
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/genrepo/{empid}")
	public ResponseEntity<Employee> getEmployeeByIdGenRepo(@PathVariable("empid") long employeeid){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeid), HttpStatus.OK);
		
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/genrepo/allocatedornot/{alloc}")
	public List<Employee> getAllocatedOrNot(@PathVariable("alloc") String alloc){
		return employeeService.getAllEmployeesAlloc(alloc);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/genrepo/datefilter/{from}")
	public List<Employee> getAllocationByDate(@PathVariable String from){
		System.out.println(from);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFrom = null;
//        try {
//			// dateFrom = sdf.parse(from);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        System.out.println(dateFrom);
        
		return employeeService.getAllocationByDate(from);
        
		
	}
///////////////////***********************//////////////////////////////////////////
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/addsearchfilter/{id}")
	public String addSearchFilter(@PathVariable long id) {
		return employeeService.addSearchFilter(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/searchbyempno/{id}")
	public List<Employee> searchbyempno(@PathVariable long id) {
		return employeeService.searchByEmpNo(id);
	}
	
//	@CrossOrigin("http://localhost:3000")
//	@GetMapping("/searchbyempno/{id}")
//	public List<Employee> searchempname()
	
///////////////////************************/////////////////////////////////////////////
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/searchemp")
	public List<Employee> list(
			@RequestParam(value="employeeId",required=false) Long employeeId,
			@RequestParam(value="pro_Assign",required=false) String proAssign,
			@RequestParam(value="grade",required=false) String grade,
			@RequestParam(value="deputation",required=false) String deputation,
			@RequestParam(value="buss_Unit",required=false) String buss_Unit,
			@RequestParam(value="skill_Set",required=false) String skill_Set,
			@RequestParam(value="emp_name",required=false) String emp_name,
			@RequestParam(value="location",required=false) String location,
			@RequestParam(value="designation",required=false) String designation,
			@RequestParam(value="dateOfJoiningStart",required=false)  @DateTimeFormat(iso=ISO.DATE_TIME, pattern="yyyy-MM-dd")Date dateOfJoiningStart,
            @RequestParam(value="dateOfJoiningEnd",required=false)  @DateTimeFormat(iso=ISO.DATE_TIME, pattern="yyyy-MM-dd")Date dateOfJoiningEnd
			){
		//@RequestParam(value="dateOfJoining",required=false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfJoining
	
       Specification<Employee> specification = 	filters.getSpec(proAssign,grade,deputation,buss_Unit,skill_Set,emp_name,location,designation,dateOfJoiningStart,dateOfJoiningEnd);
       return employeerepo.findAll(specification);
	}
	
	
	
//	@CrossOrigin("http://localhost:3000")
//	@RequestMapping("/searchempTest/{employee}")
//	public List<Employee> listt(@RequestBody Employee employee)
//	{
//		System.out.print(employee);
//		//@RequestParam(value="dateOfJoining",required=false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfJoining
//		//Long employeeId = employee.getEmployeeId();
//		String proAssign = employee.getPro_Assign();
//		String grade = employee.getGrade();
//		String deputation= employee.getDeputation();
//		String buss_Unit = employee.getBuss_Unit();
//		String skill_Set = employee.getSkill_Set();
//		String emp_name = employee.getEmp_name();
//		String location = employee.getLocation();
//		String designation = employee.getDesignation();
//		//@DateTimeFormat(iso=ISO.DATE_TIME, pattern="yyyy-MM-dd")
//		Date dateOfJoining = employee.getDateOfJoining();
//       Specification<Employee> specification = 	filters.getSpec(proAssign,grade,deputation,buss_Unit,skill_Set,emp_name,location,designation,dateOfJoining);
//       return employeerepo.findAll(specification);
//	}
	
	
	@CrossOrigin("http://login:3000")
	@PostMapping("/bulkuploadEmployee")
	public ResponseEntity<?> uploadEmployeeData(@RequestParam("file") MultipartFile file) {
        if (EmployeeHelper.checkExcelFormat(file)) {
            //true
        	try {
        		this.employeeService.save(file);

        		return ResponseEntity.ok(Map.of("message", "Employees File is Uploaded and Data is Saved to DB!!"));}
        	
               catch(Exception e) {
            	   return ResponseEntity.ok(Map.of("message","SQL Error, please recheck the Uploaded file!!"));
               }
            

           


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file!!");
    }
	
	@CrossOrigin("http://localhost:3000")
    @GetMapping("/deleteEmployee/{empId}")
    public void updateStatus(@PathVariable("empId") long empId) {
        System.out.println(empId);
        employeeService.empDelete(empId);
    }
	
	
	
	
	
}
