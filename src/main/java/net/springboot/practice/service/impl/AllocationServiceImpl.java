package net.springboot.practice.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.allocationDAO.allocationDAO;
import net.springboot.practice.exception.ResourceNotFoundException;
import net.springboot.practice.helper.AllocationsHelper;
import net.springboot.practice.helper.EmployeeHelper;
import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;
import net.springboot.practice.repository.AllocationRepository;
import net.springboot.practice.service.AllocationService;
//import net.springboot.practice.service.allocationsService;

@Service
public class AllocationServiceImpl implements AllocationService{
private AllocationRepository allocationRepository;

@Autowired
private allocationDAO dao;
	
	public AllocationServiceImpl(AllocationRepository allocationRepository) {
		super();
		this.allocationRepository = allocationRepository;
	}


	@Override
	public Allocations saveAllocations(Allocations allocations) {
		return allocationRepository.save(allocations);
	}


	@Override
	public List<Allocations> getAllAllocations() {
		return allocationRepository.findAll();
	}


	@Override
	public Allocations getAllocationById(long id) {
		Optional<Allocations> allocations = allocationRepository.findById(id);
		if(allocations.isPresent()) {
			return allocations.get();
		}else {
			throw new ResourceNotFoundException("allocations", "id", id);
		}
	}


	@Override
	public Allocations updateAllocations(Allocations allocation, long id) {
		Allocations existingAllocations = allocationRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("allocations","Id",id) );
		
//		existingAllocations.setAllocationId(allocation.getAllocationId());
		existingAllocations.setEmployeeId(allocation.getEmployeeId());
		existingAllocations.setPro_Id(allocation.getPro_Id());
		existingAllocations.setPro_Name(allocation.getPro_Name());
		existingAllocations.setAllocate_StartDate(allocation.getAllocate_StartDate());
		existingAllocations.setAllocate_EndDate(allocation.getAllocate_EndDate());
		existingAllocations.setAllocate_Percentage(allocation.getAllocate_Percentage());
		existingAllocations.setFin_id(allocation.getFin_id());
		existingAllocations.setCreated_Date(allocation.getCreated_Date());
		existingAllocations.setUpdated_Date(allocation.getUpdated_Date());
		existingAllocations.setEmp_Name(allocation.getEmp_Name());
		// save existing allocations
		
		allocationRepository.save(existingAllocations);
		return existingAllocations;
	}


	@Override
	public void deleteAllocations(long id) {
		// TODO Auto-generated method stub
		allocationRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Allocations","Id",id) );
		
		allocationRepository.deleteById(id);
	}


	@Override
	public List<Allocations> searchByProj(String projId) {
		// TODO Auto-generated method stub
		return dao.searchByProj(projId);
	}


	@Override
	public List<Allocations> searchByEmp(long empId) {
		// TODO Auto-generated method stub
		return dao.searchByEmp(empId);
	}

    @Override
	public List<Allocations> searchByDateFrame(long empId,String dateId) {
		// TODO Auto-generated method stub
		return dao.searchByDateFrame(empId,dateId);
	}
    
    @Override
	public List<Allocations> searchByCurrentFrame() {
		// TODO Auto-generated method stub
		return dao.searchByCurrentFrame();
	}
    
    @Override
 	public List<Allocations> searchByDateFrame(long empId) {
 		// TODO Auto-generated method stub
 		return dao.searchByEmp(empId);
 	}


	@Override
	public void save(MultipartFile file) 
//			throws java.lang.IllegalStateException
	{
		// TODO Auto-generated method stub
		
			 try {
				List<Allocations> allocation = AllocationsHelper.convertExcelToListOfAllocations(file.getInputStream());
				this.allocationRepository.saveAll(allocation);
//				return "abc";
			} 
//			 catch(java.lang.IllegalStateException ex) {
//				 return "pqtr";
//			 }
			 catch (IOException e) {
				// TODO Auto-generated catch block
//				return "xyz";
				e.printStackTrace();
			}
//			return null;
			 
		
	}
	
	@Override
    public void allocDelete(long allocId) {
        dao.allocDelete(allocId);
    }
}
