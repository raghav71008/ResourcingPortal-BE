package net.springboot.practice.allocationDAO;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;
@Repository
public class employeeDAO {
	@Autowired  
    JdbcTemplate jdbc = new JdbcTemplate(); 
	
    
	
	public List<Employee> searchByEmp(long empId) {
		String cmd = "select * from employee  where employee_id=?";
		List<Employee> empList=jdbc.query(cmd,new Object[] {empId}, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
				Employee employee = new Employee();
				
				employee.setEmployeeId(rs.getLong("employee_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setGrade(rs.getString("grade"));
				employee.setDateOfJoining(rs.getDate("date_of_joining"));
				employee.setSkill_Set(rs.getString("skill_set"));
				employee.setPro_Assign(rs.getString("project_assign"));
				employee.setDeputation(rs.getString("deputation"));
				employee.setTSR(rs.getString("tsr"));
				employee.setAR(rs.getString("ar"));
				employee.setLocation(rs.getString("location"));
				employee.setSupervisor_Name(rs.getString("supervisor_name"));
				employee.setBuss_Unit(rs.getString("business_unit"));
				employee.setStatus(rs.getString("status"));
				
				employee.setCreated_Date(rs.getDate("created_date"));
				employee.setUpdated_Date(rs.getDate("updated_date"));
				return employee;
			}


			
		});
		return empList;
	}



	public List<Employee> getAllEmployeesAlloc(String alloc) {
		String cmd = "select * from employee  where project_assign=?";
		List<Employee> empList=jdbc.query(cmd,new Object[] {alloc}, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
                Employee employee = new Employee();
				
				employee.setEmployeeId(rs.getLong("employee_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setGrade(rs.getString("grade"));
				employee.setDateOfJoining(rs.getDate("date_of_joining"));
				employee.setSkill_Set(rs.getString("skill_set"));
				employee.setPro_Assign(rs.getString("project_assign"));
				employee.setDeputation(rs.getString("deputation"));
				employee.setTSR(rs.getString("tsr"));
				employee.setAR(rs.getString("ar"));
				
				employee.setSupervisor_Name(rs.getString("supervisor_name"));
				employee.setBuss_Unit(rs.getString("business_unit"));
				
				
				employee.setCreated_Date(rs.getDate("created_date"));
				employee.setUpdated_Date(rs.getDate("updated_date"));
				return employee;
			}


			
		});
		return empList;
	}



	public List<Employee> getAllocationByDate(String dateFrom) {
		System.out.println("dao"+dateFrom);
		String cmd = "select * from employee  where date_of_joining=?";
		List<Employee> empList=jdbc.query(cmd,new Object[] {dateFrom}, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
                Employee employee = new Employee();
				
				employee.setEmployeeId(rs.getLong("employee_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setGrade(rs.getString("grade"));
				employee.setDateOfJoining(rs.getDate("date_of_joining"));
				employee.setSkill_Set(rs.getString("skill_set"));
				employee.setPro_Assign(rs.getString("project_assign"));
				employee.setDeputation(rs.getString("deputation"));
				employee.setTSR(rs.getString("tsr"));
				employee.setAR(rs.getString("ar"));
                
				employee.setSupervisor_Name(rs.getString("supervisor_name"));
				employee.setBuss_Unit(rs.getString("business_unit"));
				
				
				employee.setCreated_Date(rs.getDate("created_date"));
				employee.setUpdated_Date(rs.getDate("updated_date"));
				return employee;
			}


			
		});
		return empList;
	}



	public String addSearchFilter(long id) {
		String cmd = "select count(*) as cnt from employee  where employee_id=?";
		List str=jdbc.query(cmd,new Object[] {id}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getInt("cnt");
			}


			
		});
		return str.get(0).toString();
	}
	
	public void empDelete(long empId) {
        
        jdbc.update("Update employee set status='Inactive' where employee_id=?", new Object[] {empId});
        return;
    }
}
