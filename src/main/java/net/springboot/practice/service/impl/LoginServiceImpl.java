package net.springboot.practice.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.allocationDAO.employeeDAO;
import net.springboot.practice.allocationDAO.loginDAO;
import net.springboot.practice.exception.ResourceNotFoundException;
import net.springboot.practice.helper.LoginHelper;
import net.springboot.practice.model.Employee;
import net.springboot.practice.model.Login;
import net.springboot.practice.repository.EmployeeRepository;
import net.springboot.practice.repository.LoginRepository;
import net.springboot.practice.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private loginDAO loginDao = new loginDAO();

	public LoginServiceImpl(LoginRepository loginRepository) {
		super();
		this.loginRepository = loginRepository;
	}

//	@Override
//	public String authentication(String id, String password) {
//		// TODO Auto-generated method stub
//		return dao.authentication(id,password);
//	}
	@Override
	public List<Login> authentications(long empId, String password) {
		// TODO Auto-generated method stub
		return loginDao.authentications(empId, password);
	}

	@Override
	public Login saveInlogin(Login login) {
		return loginRepository.save(login);
	}

	@Override
	public List<Login> getAllInLogin() {
		return loginRepository.findAll();
	}

	@Override
	public Login findByEmpIdInLogin(long id) {
		Optional<Login> login = loginRepository.findById(id);
		if (login.isPresent()) {
			return login.get();
		} else {
			throw new ResourceNotFoundException("Employee", "id", id);
		}
	}

	@Override
	public Login updateLoginUser(Login login, long id) {
		// we need to check id exists

		Login existingLoginUser = loginRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

//		existingLoginUser.setUser_id(login.getUser_id());
//		existingLoginUser.setPassword(login.getPassword());
		existingLoginUser.setRole(login.getRole());
//		existingLoginUser.setStatus(login.getStatus());

		// save existing employee

		loginRepository.save(existingLoginUser);
		return existingLoginUser;

	}

	@Override
	public void deleteLoginUser(long id) {

		// check existing
		loginRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

		loginRepository.deleteById(id);

	}

	@Override
	public void changeStatus(long id) {
		loginDao.changeStatus(id);
	}

	@Override
	public void save(MultipartFile file) {
		 try {
			List<Login> logins = LoginHelper.convertExcelToListOfLogin(file.getInputStream());
			this.loginRepository.saveAll(logins);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Login> searchLoginData(String status) {
		// TODO Auto-generated method stub
		return loginDao.searchLoginData(status);
		
	}
	
	@Override
    public List<Login> searchLoginDataByRole(String role) {
        // TODO Auto-generated method stub
        return loginDao.searchLoginDataByRole(role);
        
    }



@Override
    public List<Login> searchLoginDataByRoleAndStatus(String role,String status) {
        // TODO Auto-generated method stub
        return loginDao.searchLoginDataByRoleAndStatus(role,status);
        
    }
}
