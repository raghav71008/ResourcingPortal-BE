package net.springboot.practice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.model.Allocations;


public interface AllocationService {

Allocations saveAllocations(Allocations allocation);
	
	List<Allocations> getAllAllocations();
	
	Allocations getAllocationById(long id);
	
	Allocations updateAllocations(Allocations allocation , long id);
	
	void deleteAllocations(long id);
	
	List<Allocations>  searchByProj(String id);
	
	List<Allocations>  searchByEmp(long empId);
	
	List<Allocations> searchByDateFrame(long empId,String id);
	List<Allocations> searchByDateFrame(long empId);

	void save(MultipartFile file);

	List<Allocations> searchByCurrentFrame();
	void allocDelete(long allocId);
	
	
	
}
