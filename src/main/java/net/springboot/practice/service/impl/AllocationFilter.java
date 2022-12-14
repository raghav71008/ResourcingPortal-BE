package net.springboot.practice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import net.springboot.practice.model.Allocations;


public class AllocationFilter {
  
	public static Specification<Allocations> getSpec(Long emp_id,String pro_Name,String pro_Id,String emp_Name,Date allocate_StartDate,Date allocate_EndDate,
			Integer allocation_percentage,Integer Finance_ProjectId){
		return ((root,query,criteriaBuilder)->{
		List<Predicate> predicate = new ArrayList<>();
		//System.out.println("sdasdasd");
		System.out.println("emp_id "+emp_id);
		System.out.println("project_name "+pro_Name);
		System.out.println("project_id "+pro_Id);
		System.out.println("emp_name "+emp_Name);
		System.out.println("alloc_StartDate "+allocate_StartDate);
		System.out.println("alloc_EndDate "+allocate_EndDate);
		System.out.println("allocation_percentage "+allocation_percentage);
		System.out.println("Finance_ProjectId "+Finance_ProjectId);
//		System.out.println("employeeId "+employeeId);
		System.out.println();
		
		
		
//		if(!(pro_Name!=null)) {
//			predicate.add(criteriaBuilder.like(root.get("pro_Name"),pro_Name));
//		}
		
		
		if(pro_Name!=null && !(pro_Name.isEmpty())) {
			predicate.add(criteriaBuilder.equal(root.get("pro_Name"),pro_Name));
		}
	
		if(emp_Name!=null && !(emp_Name.isEmpty())) {
			predicate.add(criteriaBuilder.equal(root.get("emp_Name"),emp_Name));
		}
		
		if(pro_Id!=null && !(pro_Id.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("pro_Id"),pro_Id));
		}
		
		if(allocate_StartDate!=null ) {
			predicate.add(criteriaBuilder.equal(root.get("allocate_StartDate"),allocate_StartDate));
		}
		
		if(allocate_EndDate!=null) {
			predicate.add(criteriaBuilder.equal(root.get("allocate_EndDate"),allocate_EndDate));
		}
		if(!(allocation_percentage == null)) {
			predicate.add(criteriaBuilder.equal(root.get("allocate_Percentage"),allocation_percentage));
		}
		
		if( !(Finance_ProjectId == null)){
			predicate.add(criteriaBuilder.equal(root.get("fin_id"),Finance_ProjectId));
		}
		
		if(!(emp_id == null)) {
			predicate.add(criteriaBuilder.equal(root.get("employeeId"),emp_id));
		}
		return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
		});
      }
 }