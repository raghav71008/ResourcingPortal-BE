package net.springboot.practice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "allocation")
public class Allocations {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)    
   @Column()
   private long allocationId;
   
   @Column
   private long employeeId;
   
   @Column(name="project_id")
   private String pro_Id;
   
   @Column(name="emp_Name")
   private String emp_Name;
   
   public String getEmp_Name() {
	return emp_Name;
}

public void setEmp_Name(String emp_Name) {
	this.emp_Name = emp_Name;
}
@Column(name="project_name")
   private String pro_Name;
   
   @Column(name="allocation_percentage")
   private int allocate_Percentage;
   
   @JsonFormat (pattern ="yyyy-MM-dd")
   @Column(name="allocation_start_date")
   private Date allocate_StartDate;
   
   @JsonFormat (pattern ="yyyy-MM-dd")
   @Column(name="allocation_end_date")
   private Date allocate_EndDate;
   
   @JsonFormat (pattern ="yyyy-MM-dd")
   @Column
   private Date created_Date;
   
   @JsonFormat (pattern ="yyyy-MM-dd")
   @Column
   private Date updated_Date;
   
   @Column(name="Finance_project_id" , columnDefinition = "integer default 0")
   private int fin_id;
   
	public int getFin_id() {
		return fin_id;
	}

	public void setFin_id(int fin_id) {
		this.fin_id = fin_id;
	}
	
	@javax.persistence.Transient
	private long empp_id;
	
	
	
	
	public long getEmpp_id() {
		return empp_id;
	}

	public void setEmpp_id(long empp_id) {
		this.empp_id = empp_id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empp_id",referencedColumnName = "employeeId", nullable  = true)
	@Fetch(FetchMode.JOIN)
	private Employee employee;
	
	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Column(columnDefinition = "varchar(10) default 'Active'")
    private String status = "Active";
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
public long getAllocationId() {
	return allocationId;
}

public void setAllocationId(long allocationId) {
	this.allocationId = allocationId;
}

public long getEmployeeId() {
	return employeeId;
}

public void setEmployeeId(long employeeId) {
	this.employeeId = employeeId;
}

public String getPro_Id() {
	return pro_Id;
}

public void setPro_Id(String string) {
	this.pro_Id = string;
}

public String getPro_Name() {
	return pro_Name;
}

public void setPro_Name(String pro_Name) {
	this.pro_Name = pro_Name;
}

public int getAllocate_Percentage() {
	return allocate_Percentage;
}

public void setAllocate_Percentage(int allocate_Percentage) {
	this.allocate_Percentage = allocate_Percentage;
}

public Date getAllocate_StartDate() {
	return allocate_StartDate;
}

public void setAllocate_StartDate(Date allocate_StartDate) {
	this.allocate_StartDate = allocate_StartDate;
}

public Date getAllocate_EndDate() {
	return allocate_EndDate;
}

public void setAllocate_EndDate(Date allocate_EndDate) {
	this.allocate_EndDate = allocate_EndDate;
}

public Date getCreated_Date() {
	return created_Date;
}

public void setCreated_Date(Date created_Date) {
	this.created_Date = created_Date;
}

public Date getUpdated_Date() {
	return updated_Date;
}

public void setUpdated_Date(Date updated_Date) {
	this.updated_Date = updated_Date;
}

@Override
public String toString() {
	return "Allocations [allocationId=" + allocationId + ", employeeId=" + employeeId + ", pro_Id=" + pro_Id
			+ ", emp_Name=" + emp_Name + ", pro_Name=" + pro_Name + ", allocate_Percentage=" + allocate_Percentage
			+ ", allocate_StartDate=" + allocate_StartDate + ", allocate_EndDate=" + allocate_EndDate
			+ ", created_Date=" + created_Date + ", updated_Date=" + updated_Date + ", fin_id=" + fin_id + ", empp_id="
			+ empp_id + ", employee=" + employee + ", status=" + status + "]";
}


   
}
