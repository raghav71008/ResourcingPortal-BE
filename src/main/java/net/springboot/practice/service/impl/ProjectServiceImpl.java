package net.springboot.practice.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.allocationDAO.ProjectDAO;
//import net.springboot.practice.allocationDAO.allocationDAO;
import net.springboot.practice.exception.ResourceNotFoundException;
import net.springboot.practice.helper.AllocationsHelper;
import net.springboot.practice.helper.ProjectHelper;
import net.springboot.practice.model.Allocations;
//import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Project;
//import net.springboot.practice.repository.AllocationRepository;
import net.springboot.practice.repository.ProjectRepository;
//import net.springboot.practice.service.AllocationService;
import net.springboot.practice.service.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService {
	
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectDAO dao;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}


	@Override
	public List<Project> getAllProject() {
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(long id) {
		Optional<Project> project = projectRepository.findById(id);
		if(project.isPresent()) {
			return project.get();
		}else {
			throw new ResourceNotFoundException("project", "id", id);
		}
	}
	
	@Override
	public Project updateProject(Project project, Long id) {
		Project existingProject = projectRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("project","Id",id) );
		
//		existingProject.setProj_Pk(project.getProj_Pk());
		existingProject.setBuss_unit(project.getBuss_unit());
		existingProject.setVertical(project.getVertical());
		existingProject.setDepartment(project.getDepartment());
		existingProject.setProj_Id(project.getProj_Id());
		existingProject.setProj_name(project.getProj_name());
		existingProject.setFin_Id(project.getFin_Id());
		existingProject.setStart_date(project.getStart_date());
		existingProject.setEnd_date(project.getEnd_date());
		existingProject.setBillable(project.getBillable());
		existingProject.setAcc_managers(project.getAcc_managers());
		existingProject.setPmo_submitter(project.getPmo_submitter());
		// save existing allocations
		
		projectRepository.save(existingProject);
		return existingProject;
	}
	

	@Override
	public void deleteProject(long id) {
		// TODO Auto-generated method stub
		projectRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Project","Id",id) );
		
		projectRepository.deleteById(id);
	}
	
	@Override
	public Project searchByProjectId(String projId) {
		// TODO Auto-generated method stub
		return dao.searchByProjectId(projId);
	}


	@Override
	public List<Project> searchByBillable(String billable) {
		// TODO Auto-generated method stub
		return dao.searchByBillable(billable);
	}

	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		 try {
				List<Project> project = ProjectHelper.convertExcelToListOfProject(file.getInputStream());
				this.projectRepository.saveAll(project);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
    public void projectDelete(long projPk) {
        dao.projectDelete(projPk);
    }


//	@Override
//	public List<Project> searchStatus(String status) {
//		// TODO Auto-generated method stub
//	return	dao.searchStatus(status);
//	
//	
//	}




}
