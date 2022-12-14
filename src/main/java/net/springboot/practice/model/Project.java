package net.springboot.practice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Project")
public class Project {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column
	private long Proj_Pk;
	
	@Column(name="business_unit")
	private String buss_unit;
	
	@Column(name="Vertical_Horizontal")
	private String vertical;
	
	@Column(name="Department")
	private String department;
	
	@Column(name="Project_Id")
	private String proj_Id;
	
	@Column(name="Project_Name")
	private String proj_name;
	
	@Column(name="Finance_Id")  // , columnDefinition = "integer default 0")
	private long fin_Id ;     // =0;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="Start_Date")
	 @Temporal(TemporalType.DATE)
	private Date start_date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="End_Date")
	 @Temporal(TemporalType.DATE)
	private Date end_date;
	
	@Column(name="Billable")
	private String billable;
	
	@Column(name="Account_Managers")
	private String acc_managers;
	
	@Column(name="PMO_Submitter")
	private String pmo_submitter;
	
	@Column(columnDefinition = "varchar(10) default 'Active'")
    private String status = "Active";
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public long getProj_Pk() {
		return Proj_Pk;
	}

	public void setProj_Pk(long proj_Pk) {
		Proj_Pk = proj_Pk;
	}

	public String getBuss_unit() {
		return buss_unit;
	}

	public void setBuss_unit(String buss_unit) {
		this.buss_unit = buss_unit;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProj_Id() {
		return proj_Id;
	}

	public void setProj_Id(String proj_Id) {
		this.proj_Id = proj_Id;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public long getFin_Id() {
		return fin_Id;
	}

	public void setFin_Id(long fin_Id) {
		this.fin_Id = fin_Id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getBillable() {
		return billable;
	}

	public void setBillable(String billable) {
		this.billable = billable;
	}

	public String getAcc_managers() {
		return acc_managers;
	}

	public void setAcc_managers(String acc_managers) {
		this.acc_managers = acc_managers;
	}

	public String getPmo_submitter() {
		return pmo_submitter;
	}

	public void setPmo_submitter(String pmo_submitter) {
		this.pmo_submitter = pmo_submitter;
	}

	@Override
	public String toString() {
		return "Project [Proj_Pk=" + Proj_Pk + ", buss_unit=" + buss_unit + ", vertical=" + vertical + ", department="
				+ department + ", proj_Id=" + proj_Id + ", proj_name=" + proj_name + ", fin_Id=" + fin_Id
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", billable=" + billable + ", acc_managers="
				+ acc_managers + ", pmo_submitter=" + pmo_submitter + ", status=" + status + "]";
	}
	
	

	
}
