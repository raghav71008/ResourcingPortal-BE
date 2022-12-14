package net.springboot.practice.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import net.springboot.practice.model.Employee;

public class filters {
	public static Specification<Employee> getSpec(String pro_Assign,String grade,String deputation,String buss_Unit,String skill_Set,String emp_name,
			String location,String designation,Date dateOfJoiningStart,Date dateOfJoiningEnd){
		return ((root,query,criteriaBuilder)->{
		List<Predicate> predicate = new ArrayList<>();
		
		System.out.println("Dep "+deputation);
		System.out.println("PA "+pro_Assign);
		System.out.println("Grade "+grade);
		System.out.println("Buss "+buss_Unit);
		System.out.println("Skill "+skill_Set);
		System.out.println("empname "+emp_name);
		System.out.println("location "+location);
		System.out.println("designation "+designation);
		System.out.println("dateOfJoiningStart "+dateOfJoiningStart);
		System.out.println("dateOfJoiningEnd "+dateOfJoiningEnd);
		
		//System.out.println("employeeId "+employeeId);
		System.out.println();
//		if(employeeId!=0 && !(employeeId==null)) {
//			predicate.add(criteriaBuilder.equal(root.get("employeeId"),employeeId));
//		}
		if(pro_Assign!=null && !(pro_Assign.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("pro_Assign"),pro_Assign));
		}
		
		if(grade!=null && !(grade.isEmpty())) {
				predicate.add(criteriaBuilder.equal(root.get("grade"),grade));
			}
		if(buss_Unit!=null && !(buss_Unit.isEmpty())) {
			predicate.add(criteriaBuilder.equal(root.get("buss_Unit"),buss_Unit));
		}
		if(deputation!=null && !(deputation.isEmpty())) {
			predicate.add(criteriaBuilder.equal(root.get("deputation"),deputation));
		}
		if(skill_Set!=null && !(skill_Set.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("skill_Set"),skill_Set));
		}
		if(emp_name!=null && !(emp_name.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("emp_name"),emp_name));
		}
		if(location!=null && !(location.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("location"),location));
		}
		if(designation!=null && !(designation.isEmpty())) {
			predicate.add(criteriaBuilder.like(root.get("designation"),designation));
		}
		if(dateOfJoiningStart!=null || dateOfJoiningEnd!=null) {
            if(dateOfJoiningStart == null)
            predicate.add(criteriaBuilder.lessThanOrEqualTo((root.get("dateOfJoining")),dateOfJoiningEnd));
            else if (dateOfJoiningEnd == null)
            predicate.add(criteriaBuilder.greaterThanOrEqualTo((root.get("dateOfJoining")),dateOfJoiningStart));
            else
            predicate.add(criteriaBuilder.between((root.get("dateOfJoining")),dateOfJoiningStart,dateOfJoiningEnd));
            
            //System.out.print("sda"+root.get("dateOfJoining"));
        }
		//predicates.add(cb.like(cb.lower(root.get("startDate")), "%" + params.getStartDate() + "%"));
//		if(employeeId!=0 && !(employeeId == null)) {
//			predicate.add(criteriaBuilder.equal(root.get("employeeId"),employeeId));
//		}
			return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
		});
		
	}
}
