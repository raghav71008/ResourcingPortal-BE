package net.springboot.practice.allocationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.springboot.practice.model.Project;
@Repository
public class ProjectDAO {

	
	@Autowired  
    JdbcTemplate jdbc;  
	
	
	public Project searchByProjectId(String projId) {
		String cmd = "select * from project where Project_Id=?";
		List<Project> projList=jdbc.query(cmd,new Object[] {projId}, new RowMapper<Project>() {

			@Override
			public Project mapRow(ResultSet rs, int arg1) throws SQLException {
				Project project = new Project();
				
				project.setAcc_managers(rs.getString("Account_Managers"));
				project.setProj_Pk(rs.getLong("Proj_Pk"));
				project.setFin_Id(rs.getLong("Finance_Id"));
				project.setBillable(rs.getString("Billable"));
				project.setBuss_unit(rs.getString("business_unit"));
				project.setDepartment(rs.getString("Department"));
				project.setProj_Id(rs.getString("Project_Id"));
				project.setPmo_submitter(rs.getString("PMO_Submitter"));
				project.setProj_name(rs.getString("Project_Name"));
				project.setStart_date(rs.getDate("Start_Date"));
				project.setEnd_date(rs.getDate("End_Date"));
		        project.setVertical(rs.getString("Vertical_Horizontal"));
		        project.setStatus(rs.getNString("Status"));
				return project;
			}


			
		});
		return   projList.get(0);
	}
	
	public List<Project> searchByBillable(String billable) {
		String cmd = "select * from project where Billable=?";
		List<Project> projList=jdbc.query(cmd,new Object[] {billable}, new RowMapper<Project>() {

			@Override
			public Project mapRow(ResultSet rs, int arg1) throws SQLException {
				Project project = new Project();
				
				project.setAcc_managers(rs.getString("Account_Managers"));
				project.setProj_Pk(rs.getLong("Proj_Pk"));
				project.setFin_Id(rs.getLong("Finance_Id"));
				project.setBillable(rs.getString("Billable"));
				project.setBuss_unit(rs.getString("business_unit"));
				project.setDepartment(rs.getString("Department"));
				project.setProj_Id(rs.getString("Project_Id"));
				project.setPmo_submitter(rs.getString("PMO_Submitter"));
				project.setProj_name(rs.getString("Project_Name"));
				project.setStart_date(rs.getDate("Start_Date"));
				project.setEnd_date(rs.getDate("End_Date"));
		        project.setVertical(rs.getString("Vertical_Horizontal"));
		        project.setStatus(rs.getNString("Status"));
				return project;
			}


			
		});
		return projList;
	}
	
	public void projectDelete(long projPk) {
        
        jdbc.update("Update project set status='Inactive' where proj_pk=?", new Object[] {projPk});
        return;
    }

	public List<Project> searchStatus(String status) {
		// TODO Auto-generated method stub
		String cmd = "select * from project where status=?";
		List<Project> projList=jdbc.query(cmd,new Object[] {status}, new RowMapper<Project>() {

			@Override
			public Project mapRow(ResultSet rs, int arg1) throws SQLException {
				Project project = new Project();
				
				project.setAcc_managers(rs.getString("Account_Managers"));
				project.setProj_Pk(rs.getLong("Proj_Pk"));
				project.setFin_Id(rs.getLong("Finance_Id"));
				project.setBillable(rs.getString("Billable"));
				project.setBuss_unit(rs.getString("business_unit"));
				project.setDepartment(rs.getString("Department"));
				project.setProj_Id(rs.getString("Project_Id"));
				project.setPmo_submitter(rs.getString("PMO_Submitter"));
				project.setProj_name(rs.getString("Project_Name"));
				project.setStart_date(rs.getDate("Start_Date"));
				project.setEnd_date(rs.getDate("End_Date"));
		        project.setVertical(rs.getString("Vertical_Horizontal"));
		        project.setStatus(rs.getNString("Status"));
				return project;
			}


			
		});
		return projList;

	}
	
}
