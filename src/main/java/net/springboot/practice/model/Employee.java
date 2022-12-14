package net.springboot.practice.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;



@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	@Id
	@Column
	private long employeeId;
	
	@Column
    private String emp_name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;
    
    @Column
    private String skill_Set;
    
    @Column(name="business_unit")
    private String buss_Unit;
    
    @Column(name="ageing")
    private int age;
    
    @Column
    private String supervisor_Name;
    
    @Column
    private String designation;
    
    @Column
    private String grade;
    
    @Column(name="deputation")
    private String deputation;
    
    @Column
    private String TSR;
    
    @Column 
    private String location;
    
    @Column
    private String AR;
    
    @Column(columnDefinition = "varchar(10) default 'Active'")
    private String status="Active";
    
    
    

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeputation() {
		return deputation;
	}

	public void setDeputation(String deputation) {
		this.deputation = deputation;
	}

	public String getTSR() {
		return TSR;
	}

	public void setTSR(String tSR) {
		TSR = tSR;
	}

	public String getAR() {
		return AR;
	}

	public void setAR(String aR) {
		AR = aR;
	}

	public Set<Allocations> getAllocation() {
		return allocation;
	}

	public void setAllocation(Set<Allocations> allocation) {
		this.allocation = allocation;
	}

	@Column(name="project_assign")
    private String pro_Assign;
//    
//    @Column
//    private String pro_Allocated;
//    
//    @Column
//    private double pro_Id;
//    
    @Column(name="project_name")
    private String pro_Name;
    
//    @JsonFormat (pattern ="yyyy-MM-dd")
//    @Column
//    private Date allocate_StartDate;
//    
//    @JsonFormat (pattern ="yyyy-MM-dd")
//    @Column
//    private Date allocate_EndDate;
//    
//    @Column
//    private int allocate_Percentage;
//    
//    @Column
//    private String report_To;
//    
    @Column
    private String comments;
    
    @JsonFormat (pattern ="yyyy-MM-dd")
    @Column
    private Date created_Date;
    
    @JsonFormat (pattern ="yyyy-MM-dd")
    @Column
    private Date updated_Date;
    ///////////////////////////////////////////////////
    @OneToMany(targetEntity = Allocations.class, mappedBy = "employeeId", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<Allocations> allocation;
/////////////////////////////////////////////////////////////
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getSkill_Set() {
		return skill_Set;
	}

	public void setSkill_Set(String skill_Set) {
		this.skill_Set = skill_Set;
	}

	public String getBuss_Unit() {
		return buss_Unit;
	}

	public void setBuss_Unit(String buss_Unit) {
		this.buss_Unit = buss_Unit;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSupervisor_Name() {
		return supervisor_Name;
	}

	public void setSupervisor_Name(String supervisor_Name) {
		this.supervisor_Name = supervisor_Name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPro_Assign() {
		return pro_Assign;
	}

	public void setPro_Assign(String pro_Assign) {
		this.pro_Assign = pro_Assign;
	}

	

	public String getPro_Name() {
		return pro_Name;
	}

	public void setPro_Name(String pro_Name) {
		this.pro_Name = pro_Name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date date) {
		this.created_Date = date;
	}

	public Date getUpdated_Date() {
		return updated_Date;
	}

	public void setUpdated_Date(Date date) {
		this.updated_Date = date;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", emp_name=" + emp_name + ", dateOfJoining=" + dateOfJoining
				+ ", skill_Set=" + skill_Set + ", buss_Unit=" + buss_Unit + ", age=" + age + ", supervisor_Name="
				+ supervisor_Name + ", designation=" + designation + ", grade=" + grade + ", deputation=" + deputation
				+ ", TSR=" + TSR + ", location=" + location + ", AR=" + AR + ", status=" + status + ", pro_Assign="
				+ pro_Assign + ", pro_Name=" + pro_Name + ", comments=" + comments + ", created_Date=" + created_Date
				+ ", updated_Date=" + updated_Date + ", allocation=" + allocation + "]";
	}



	
     

	

    

	

	
    
	
    
    
    
}
