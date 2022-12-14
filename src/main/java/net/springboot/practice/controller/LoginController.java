package net.springboot.practice.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.helper.LoginHelper;
import net.springboot.practice.model.Login;
import net.springboot.practice.service.LoginService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
//	@CrossOrigin("http://localhost:3000")
//	@GetMapping("/authentication/{user}/{password}")
//	public String auth(@PathVariable String user,@PathVariable String password) {
//		return loginService.authentication(user,password);
//	}
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	public ResponseEntity<Login> saveLoginUser(@RequestBody Login login){
		
		return new ResponseEntity<Login>(loginService.saveInlogin(login), HttpStatus.CREATED);
	}
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	public List<Login> getAllLoginUser(){
		return loginService.getAllInLogin();
	}
	@CrossOrigin("http://localhost:3000*")
	@GetMapping("{id}")
	public ResponseEntity<Login> getLoginById(@PathVariable("id") long employeeid){
		return new ResponseEntity<Login>(loginService.findByEmpIdInLogin(employeeid), HttpStatus.OK);
		
	}
	@CrossOrigin("http://localhost:3000")
	@PutMapping("{id}")
	public ResponseEntity<Login>updateLoginInfo(@PathVariable("id") long id , @RequestBody Login login){
		
		return new ResponseEntity<Login>(loginService.updateLoginUser(login , id), HttpStatus.OK); 
		
	}
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteLoginUser(@PathVariable("id") long id){
		
		loginService.deleteLoginUser(id);
		
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/authentications/{empId}/{password}")
	public List<Login> authas(@PathVariable Long empId,@PathVariable String password) {
		return loginService.authentications(empId,password);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/updateStatus/{uid}")
	public void updateStatus(@PathVariable("uid") long empId) {
		System.out.println(empId);
		loginService.changeStatus(empId);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/searchLoginData/{status}")
	
	public List<Login> searchLoginData(@PathVariable String status) {
		return loginService.searchLoginData(status);
	}
	
	
	@CrossOrigin("http://login:3000")
	@PostMapping("/bulkupload")
	public ResponseEntity<?> uploadLoginData(@RequestParam("file") MultipartFile file) {
        if (LoginHelper.checkExcelFormat(file)) {
            //true

            this.loginService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }
	
	@CrossOrigin("http://localhost:3000")
    @GetMapping("/searchLoginDataByRole/{role}")
    
    public List<Login> searchLoginDataByRole(@PathVariable("role") String role) {
        return loginService.searchLoginDataByRole(role);
    }



@CrossOrigin("http://localhost:3000")
    @GetMapping("/searchLoginDataByRoleAndStatus/{role}/{status}")
    
    public List<Login> searchLoginDataByRoleAndStatus(@PathVariable("role") String role,@PathVariable("status") String status) {
        return loginService.searchLoginDataByRoleAndStatus(role,status);
    }



	
	
	
	
	
	
	
	
}
