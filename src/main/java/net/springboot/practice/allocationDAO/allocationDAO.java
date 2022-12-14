package net.springboot.practice.allocationDAO;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.springboot.practice.model.Allocations;
@Repository
public class allocationDAO {

	@Autowired  
    JdbcTemplate jdbc;  
	
	public List<Allocations> searchByProj(String projId) {
		String cmd = "select * from allocation where project_id=?";
		List<Allocations> projList=jdbc.query(cmd,new Object[] {projId}, new RowMapper<Allocations>() {

			@Override
			public Allocations mapRow(ResultSet rs, int arg1) throws SQLException {
				Allocations allocations = new Allocations();
				allocations.setAllocationId(rs.getLong("allocation_id"));
				allocations.setEmployeeId(rs.getLong("employee_id"));
				allocations.setPro_Id(rs.getString("project_id"));
				allocations.setEmp_Name(rs.getString("emp_name"));
				allocations.setPro_Name(rs.getString("project_name"));
				allocations.setAllocate_Percentage(rs.getInt("allocation_percentage"));
				allocations.setAllocate_StartDate(rs.getDate("allocation_start_date"));
				allocations.setAllocate_EndDate(rs.getDate("allocation_end_date"));
				allocations.setFin_id(rs.getInt("finance_project_id"));
				allocations.setCreated_Date(rs.getDate("created_date"));
				allocations.setUpdated_Date(rs.getDate("updated_date"));
				return allocations;
			}


			
		});
		return projList;
	}
	
	public List<Allocations> searchByEmp(long empId) {
		String cmd = "select * from allocation where employee_id=?";
		List<Allocations> projList=jdbc.query(cmd,new Object[] {empId}, new RowMapper<Allocations>() {

			@Override
			public Allocations mapRow(ResultSet rs, int arg1) throws SQLException {
				Allocations allocations = new Allocations();
				allocations.setAllocationId(rs.getLong("allocation_id"));
				allocations.setEmployeeId(rs.getLong("employee_id"));
				allocations.setPro_Id(rs.getString("project_id"));
				allocations.setEmp_Name(rs.getString("emp_name"));
				allocations.setPro_Name(rs.getString("project_name"));
				allocations.setAllocate_Percentage(rs.getInt("allocation_percentage"));
				allocations.setAllocate_StartDate(rs.getDate("allocation_start_date"));
				allocations.setFin_id(rs.getInt("finance_project_id"));
				allocations.setAllocate_EndDate(rs.getDate("allocation_end_date"));
				allocations.setCreated_Date(rs.getDate("created_date"));
				allocations.setUpdated_Date(rs.getDate("updated_date"));
				return allocations;
			}


			
		});
		return projList;
	}

	public List<Allocations> searchByDateFrame(long empId,String dateId) {
		String cmd = null ;

		LocalDate date = java.time.LocalDate.now();
		if(dateId.equals("-1"))
			cmd ="select * from allocation where employee_id=? AND allocation_end_date < ?";
		else if(dateId.equals("0"))
			cmd ="select * from allocation where employee_id=? AND ? between allocation_start_date AND allocation_end_date";
		else if(dateId.equals("1"))
			cmd ="select * from allocation where employee_id=? AND allocation_start_date > ?";
		List<Allocations> projList=jdbc.query(cmd,new Object[] {empId,date}, new RowMapper<Allocations>() {

			@Override
			public Allocations mapRow(ResultSet rs, int arg1) throws SQLException {
				Allocations allocations = new Allocations();
				allocations.setAllocationId(rs.getLong("allocation_id"));
				allocations.setEmployeeId(rs.getLong("employee_id"));
				allocations.setPro_Id(rs.getString("project_id"));
				allocations.setEmp_Name(rs.getString("emp_name"));
				allocations.setPro_Name(rs.getString("project_name"));
				allocations.setAllocate_Percentage(rs.getInt("allocation_percentage"));
				allocations.setAllocate_StartDate(rs.getDate("allocation_start_date"));
				allocations.setAllocate_EndDate(rs.getDate("allocation_end_date"));
				allocations.setFin_id(rs.getInt("finance_project_id"));
				allocations.setCreated_Date(rs.getDate("created_date"));
				allocations.setUpdated_Date(rs.getDate("updated_date"));
				return allocations;
			}


			
		});
		return  projList;
	
	}
	
	
	public List<Allocations> searchByCurrentFrame() {
		String cmd = null ;

		LocalDate date = java.time.LocalDate.now();
		
//		if(dateId == 0) {
			cmd ="select * from allocation where ? between allocation_start_date AND allocation_end_date";
//		}
		List<Allocations> projList=jdbc.query(cmd,new Object[] {date}, new RowMapper<Allocations>() {

			@Override
			public Allocations mapRow(ResultSet rs, int arg1) throws SQLException {
				Allocations allocations = new Allocations();
				allocations.setAllocationId(rs.getLong("allocation_id"));
				allocations.setEmployeeId(rs.getLong("employee_id"));
				allocations.setPro_Id(rs.getString("project_id"));
				allocations.setEmp_Name(rs.getString("emp_name"));
				allocations.setPro_Name(rs.getString("project_name"));
				allocations.setAllocate_Percentage(rs.getInt("allocation_percentage"));
				allocations.setAllocate_StartDate(rs.getDate("allocation_start_date"));
				allocations.setAllocate_EndDate(rs.getDate("allocation_end_date"));
				allocations.setFin_id(rs.getInt("finance_project_id"));
				allocations.setCreated_Date(rs.getDate("created_date"));
				allocations.setUpdated_Date(rs.getDate("updated_date"));
				return allocations;
			}


			
		});
		return  projList;
	
	}
	
	public void allocDelete(long allocId) {
        
        jdbc.update("Update allocations set status='Inactive' where allocation_id=?", new Object[] {allocId});
        return;
    }

}
