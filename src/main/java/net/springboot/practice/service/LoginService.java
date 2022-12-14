package net.springboot.practice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.model.Login;

public interface LoginService {

//	String authentication(String id, String password);
	List<Login> authentications(long empId, String password);

	Login saveInlogin(Login login);
	
	void save(MultipartFile file);

	List<Login> getAllInLogin();

	Login findByEmpIdInLogin(long id);

	Login updateLoginUser(Login login, long id);

	void deleteLoginUser(long id);

	void changeStatus(long id);

    List<Login> searchLoginData(String status);
    List<Login> searchLoginDataByRole(String role);



    List<Login> searchLoginDataByRoleAndStatus(String role,String status);
}
