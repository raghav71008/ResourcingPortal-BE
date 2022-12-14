package net.springboot.practice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Project;

public interface ProjectService {

	Project saveProject(Project project);
	
	List<Project> getAllProject();
	
	Project getProjectById(long id) ;

//	Project updateProject(Project project, long id);

	void deleteProject(long id);

	List<Project> searchByBillable(String billable);

	Project searchByProjectId(String projId);

	Project updateProject(Project project, Long id);

	void save(MultipartFile file);
	void projectDelete(long projPk);

//	List<Project> searchStatus(String status);
	
}
