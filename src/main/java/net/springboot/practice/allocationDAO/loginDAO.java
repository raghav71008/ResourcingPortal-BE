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

import net.springboot.practice.model.Employee;
import net.springboot.practice.model.Login;
@Repository 
public class loginDAO {
	@Autowired  
    JdbcTemplate jdbc = new JdbcTemplate();
	
//	public String authentication(String id, String password) {
//		String cmd = "select count(*) as cnt from login  where user_id=? AND password=?";
//		List str=jdbc.query(cmd,new Object[] {id,password}, new RowMapper() {
//
//			@Override
//			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
//                return rs.getInt("cnt");
//			}	
//		});
//		return str.get(0).toString();
//	}

	public List<Login> authentications(long empId, String password) {
		String cmd = "select count(*) as cnt,role,status from login  where emp_id=? AND password=?";
		List<Login> str=jdbc.query(cmd,new Object[] {empId,password}, new RowMapper<Login>() {

			@Override
			public Login mapRow(ResultSet rs, int arg1) throws SQLException {
				Login login = new Login();
				login.setRole(rs.getString("role"));
				login.setStatus(rs.getString("status"));
				login.setValid(rs.getInt("cnt"));
			
                return login;
			}	
		});
		return str;
	}

	public void changeStatus(long id) {
		// TODO Auto-generated method stub
		//String cmd = "Update login set status='Inactive' where emp_id=?";
		jdbc.update("Update login set status='Inactive' where emp_id=?", new Object[] {id});
		return;
	}

	public List<Login> searchLoginData(String status) {
		// TODO Auto-generated method stub
		String cmd = "select emp_id,role,status from login  where status=?";
		List<Login> str=jdbc.query(cmd,new Object[] {status}, new RowMapper<Login>() {

			@Override
			public Login mapRow(ResultSet rs, int arg1) throws SQLException {
				Login login = new Login();
				login.setRole(rs.getString("role"));
				login.setStatus(rs.getString("status"));
				login.setEmpId(rs.getLong("emp_id"));
			
                return login;
			}	
		});
		return str;
	}
	
	public List<Login> searchLoginDataByRole(String role) {
        // TODO Auto-generated method stub
        String cmd = "select emp_id,role,status from login  where role=?";
        List<Login> str=jdbc.query(cmd,new Object[] {role}, new RowMapper<Login>() {



           @Override
            public Login mapRow(ResultSet rs, int arg1) throws SQLException {
                Login login = new Login();
                login.setRole(rs.getString("role"));
                login.setStatus(rs.getString("status"));
                login.setEmpId(rs.getLong("emp_id"));
            
                return login;
            }    
        });
        return str;
    }



public List<Login> searchLoginDataByRoleAndStatus(String role, String status) {
        // TODO Auto-generated method stub
        String cmd = "select emp_id,role,status from login  where role=? and status=?";
        List<Login> str=jdbc.query(cmd,new Object[] {role,status}, new RowMapper<Login>() {



           @Override
            public Login mapRow(ResultSet rs, int arg1) throws SQLException {
                Login login = new Login();
                login.setRole(rs.getString("role"));
                login.setStatus(rs.getString("status"));
                login.setEmpId(rs.getLong("emp_id"));
            
                return login;
            }    
        });
        return str;
    }
	
		
}
