package net.springboot.practice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Project;

public class ProjectFilter {

	
	public static Specification<Project> getSpec(String proj_name,String proj_Id,String billable,Integer fin_Id,String status){
		return ((root,query,criteriaBuilder)->{
		List<Predicate> predicate = new ArrayList<>();
		System.out.println("project_name "+proj_name);
		System.out.println("project_id "+proj_Id);
		System.out.println("billable "+ billable);
		System.out.println("Finance_Id "+fin_Id);
		System.out.println("status "+ status);
		System.out.println();
		
		
		if(proj_name!=null && !(proj_name.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("proj_name"),proj_name));
		}
		if(proj_Id!=null && !(proj_Id.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("proj_Id"),proj_Id));
		}
		if(billable!=null && !(billable.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("billable"),billable));
		}
		
		if(!(fin_Id == null)){
			predicate.add(criteriaBuilder.equal(root.get("fin_Id"),fin_Id));
		}
		
		if(status!= null && !(status.isEmpty())) {
			predicate.add(criteriaBuilder.equal(root.get("status"),status));
		}
		return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
		});
	
}
}