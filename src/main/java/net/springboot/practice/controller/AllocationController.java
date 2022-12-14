package net.springboot.practice.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

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

import net.springboot.practice.helper.AllocationsHelper;
import net.springboot.practice.helper.LoginHelper;
import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;
import net.springboot.practice.repository.AllocationRepository;
import net.springboot.practice.repository.EmployeeRepository;
import net.springboot.practice.service.AllocationService;
import net.springboot.practice.service.EmployeeService;
import net.springboot.practice.service.impl.AllocationFilter;
import net.springboot.practice.service.impl.AllocationServiceImpl;
import net.springboot.practice.service.impl.EmployeeServiceImpl;
import net.springboot.practice.service.impl.filters;
import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;
import net.springboot.practice.service.AllocationService;
import net.springboot.practice.service.EmployeeService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/allocations")
public class AllocationController {
	private AllocationService allocationService;
	@Autowired
	private AllocationRepository allocrepo;
	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	////
	//build create employee Rest API
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	public ResponseEntity<Allocations> saveAllocation(@RequestBody Allocations allocations){
		
		return new ResponseEntity<Allocations>(allocationService.saveAllocations(allocations), HttpStatus.CREATED);
	}
	////////////  /////////////// /////////////// ///////////////
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	public List<Allocations> getAllAllocations(){
		return allocationService.getAllAllocations();
	}
	
	@CrossOrigin("http://localhost:3000")
    @GetMapping("/deleteAllocation/{allocId}")
    public void updateStatus(@PathVariable("allocId") long allocId) {
        System.out.println(allocId);
        allocationService.allocDelete(allocId);
    }
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	///////////////
//	@CrossOrigin("http://localhost:3000*")
//	@GetMapping("/projId/{id}")
//	public ResponseEntity<Allocations> getAllocationsByProjId(@PathVariable("id") long projId){
//		return new ResponseEntity<Allocations>(allocationService.getAllocationById(projId), HttpStatus.OK);
//		
//	}
//	@GetMapping("/dateFilter/{id}")
//	public ResponseEntity<Allocations> dateFilter(@PathVariable int id){
//		try {
//			Allocations allocation = allocationService.searchByDateFrame(id);
//			return new ResponseEntity<Allocations>(allocation,HttpStatus.OK);}
//		catch(NoSuchElementException e) {
//			return new ResponseEntity<Allocations>(HttpStatus.NOT_FOUND);	
//		}
//	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//ALLOCATION FILTERS
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/dateFilter/{empid}/{id}")
	public List<Allocations> dateFilter(@PathVariable long empid,@PathVariable String id){
		try {
			return allocationService.searchByDateFrame(empid,id);}
		catch(NoSuchElementException e) {
			return (List<Allocations>) new ResponseEntity<Allocations>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/currentDateFilter")
	public List<Allocations> currentDateFilter(){
		try {
			return allocationService.searchByCurrentFrame();}
		catch(NoSuchElementException e) {
			return (List<Allocations>) new ResponseEntity<Allocations>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/dateFilter/{empid}")
	public List<Allocations> dateFilter(@PathVariable long empid){
		try {
			return allocationService.searchByDateFrame(empid);}
		catch(NoSuchElementException e) {
			return (List<Allocations>) new ResponseEntity<Allocations>(HttpStatus.NOT_FOUND);	
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	@RequestMapping("/projId/{id}")
	public List<Allocations> getProjId(@PathVariable String id) {
		try {
		
		return allocationService.searchByProj(id);
		} catch(NoSuchElementException e) {
			return (List<Allocations>) new ResponseEntity<Allocations>(HttpStatus.NOT_FOUND);
		}
	}
	////////////// ///////////////////// //////////////////////  //////////////////////////////
	
	@RequestMapping("/empId/{id}")
	public List<Allocations> getEmpId(@PathVariable int id) {
		try {
		
		return allocationService.searchByEmp(id);
		} catch(NoSuchElementException e) {
			return (List<Allocations>) new ResponseEntity<Allocations>(HttpStatus.NOT_FOUND);
		}
	}
	/////////////// /////////////////// ////////////////////// /////////////////////////////////
	
	@CrossOrigin("http://localhost:3000*")
	@GetMapping("/allocationId/{id}")
	public ResponseEntity<Allocations> getAllocationsByAllocationId(@PathVariable("id") long allId){
		return new ResponseEntity<Allocations>(allocationService.getAllocationById(allId), HttpStatus.OK);
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("{id}")
	public ResponseEntity<Allocations> updateAllocations(@PathVariable("id") long id , @RequestBody Allocations allocations){
		
		return new ResponseEntity<Allocations>(allocationService.updateAllocations(allocations , id), HttpStatus.OK); 
		
	}
	
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAllocation(@PathVariable("id") long id){
		
		allocationService.deleteAllocations(id);
		
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
	
///////////////////************************/////////////////////////////////////////////

@CrossOrigin("http://localhost:3000")
@RequestMapping("/searchalloc")
public List<Allocations> list(
@RequestParam(value="emp_Id",required=false) Long emp_id,
@RequestParam(value ="emp_Name", required=false) String emp_Name, 
@RequestParam(value="pro_Name",required=false) String pro_Name,
@RequestParam(value="pro_Id",required=false) String pro_Id,
@RequestParam(value="allocate_StartDate",required=false)@DateTimeFormat(iso=ISO.DATE_TIME, pattern="yyyy-MM-dd") Date allocate_StartDate,
@RequestParam(value="allocate_EndDate",required=false)@DateTimeFormat(iso=ISO.DATE_TIME, pattern="yyyy-MM-dd") Date allocate_EndDate,
@RequestParam(value="allocation_percentage",required=false) Integer allocation_percentage,
@RequestParam(value="Finance_ProjectId",required=false) Integer Finance_ProjectId
){

Specification<Allocations> specification =  AllocationFilter.getSpec(emp_id,pro_Name,pro_Id,emp_Name,allocate_StartDate,allocate_EndDate,
allocation_percentage,Finance_ProjectId);  //employeeId);
return allocrepo.findAll(specification);
}

////////////////////////////////////////////////////////////////////////////////////

@CrossOrigin("http://login:3000")
@PostMapping("/bulkuploadAllocation")
public ResponseEntity<?> uploadAllocationData(@RequestParam("file") MultipartFile file ){
    if (AllocationsHelper.checkExcelFormat(file)) {
        //true

    	try {
            this.allocationService.save(file);

            return ResponseEntity.ok(Map.of("message", "Allocations File is Uploaded and Data is saved to db!!"));}
//    	 return ResponseEntity.ok(Map.of("message",res ));
//    	 }
//    	
             catch(Exception e) {
        	   return ResponseEntity.ok(Map.of("message","Some Employee given in the Uploaded File doesn't exist in the Employee Table!!"));
           }



    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file!!");
}
	
}
