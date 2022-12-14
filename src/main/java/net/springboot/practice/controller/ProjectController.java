package net.springboot.practice.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import net.springboot.practice.helper.AllocationsHelper;
import net.springboot.practice.helper.ProjectHelper;
import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Project;
import net.springboot.practice.repository.AllocationRepository;
import net.springboot.practice.repository.ProjectRepository;
import net.springboot.practice.service.AllocationService;
import net.springboot.practice.service.ProjectService;
import net.springboot.practice.service.impl.AllocationFilter;
import net.springboot.practice.service.impl.ProjectFilter;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

	
	private ProjectService projectService;
	@Autowired
	private ProjectRepository projrepo;
	public ProjectController(ProjectService projectService) {
		super();
		// TODO Auto-generated constructor stub
		this.projectService = projectService;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	////
//build create employee Rest API

@CrossOrigin("http://localhost:3000")
@PostMapping
public ResponseEntity<Project> saveProject(@RequestBody Project project){

return new ResponseEntity<Project>(projectService.saveProject(project), HttpStatus.CREATED);
}

/////////////////////////// /////////////// ///////////////

@CrossOrigin("http://localhost:3000")
@GetMapping
public List<Project> getAllProject(){
 
try {

    return projectService.getAllProject();
}catch(NoSuchElementException e) {
    return (List<Project>) new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
}
}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@CrossOrigin("http://localhost:3000")
@RequestMapping("/projectId/{id}")
//public List<Project> getProjectById(@PathVariable String id) {
//try {
//
//    return projectService.searchByProjectId(id);
//     } catch(NoSuchElementException e) {
//      return (List<Project>) new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
//}
//}
public Project getProjectById(@PathVariable String id) {


    return projectService.searchByProjectId(id);
//    try {
//
//    	return projectService.searchByProjectId(id);
//    }catch(NoSuchElementException e) {
//    	return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }

}

/////////////////////////////////// //////////////////////  //////////////////////////////

@RequestMapping("/billable/{value}")
public List<Project> getProjectByBillable(@PathVariable String value) {
try {

    return projectService.searchByBillable(value);
}catch(NoSuchElementException e) {
    return (List<Project>) new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
}
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////

@CrossOrigin("http://localhost:3000")
@PutMapping("{id}")
//   DOUBT String id
public ResponseEntity<Project> updateProject(@PathVariable("id") long id , @RequestBody Project project){

return new ResponseEntity<Project>(projectService.updateProject(project , id), HttpStatus.OK); 

}

//
@CrossOrigin("http://localhost:3000")
@DeleteMapping("{id}")
public ResponseEntity<String> deleteProject(@PathVariable("id") long id){

projectService.deleteProject(id);

return new ResponseEntity<String>("Project deleted successfully!.", HttpStatus.OK);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////



@CrossOrigin("http://login:3000")
@PostMapping("/bulkuploadProject")
public ResponseEntity<?> uploadProjectData(@RequestParam("file") MultipartFile file) {
    if (ProjectHelper.checkExcelFormat(file)) {
        //true
    	try {
        this.projectService.save(file);

        return ResponseEntity.ok(Map.of("message", "Projects File is Uploaded and Data is Saved to DB"));}
    	catch(Exception e) {
     	   return ResponseEntity.ok(Map.of("message","SQL Error, please recheck the Uploaded file!!"));
        }
     


    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
}

@CrossOrigin("http://localhost:3000")
@GetMapping("/deleteProject/{projPk}")
public void updateStatus(@PathVariable("projPk") long projPk) {
    System.out.println(projPk);
    projectService.projectDelete(projPk);
}

//@CrossOrigin("http://localhost:3000")
//@GetMapping("/searchByStatus/{status}")
//public List<Project> showStatus(@PathVariable("status") String status) {
////    System.out.println(status);
//    
//    
//    
//    try {
//
//        return projectService.searchStatus(status);
//    }catch(NoSuchElementException e) {
//        return (List<Project>) new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
//    }
//    
//}


///////////////////************************/////////////////////////////////////////////

@CrossOrigin("http://localhost:3000")
@RequestMapping("/searchproj")
public List<Project> list(
@RequestParam(value="pro_Name",required=false) String proj_name,
@RequestParam(value="pro_Id",required=false) String proj_Id,
@RequestParam(value="billable",required=false) String billable,
@RequestParam(value="Finance_ProjectId",required=false) Integer fin_Id,
@RequestParam(value="status",required=false) String status
){

Specification<Project> specification = ProjectFilter.getSpec(proj_name,proj_Id,billable,fin_Id,status);  //employeeId);
return projrepo.findAll(specification);
}

////////////////////////////////////////////////////////////////////////////////////



}
