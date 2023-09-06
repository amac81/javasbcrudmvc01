package pt.bitclinic.javasbcrudmvc01.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import pt.bitclinic.javasbcrudmvc01.entities.Project;
import pt.bitclinic.javasbcrudmvc01.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	// private List <Employee> employees;

	private ProjectService projectService;

	// constructor injection of EmployeeService @Autowired optional, we just have
	// one constructor
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	// Pre-process all web requests coming into our Controller
	// Pre-process every String form data; remove leading and trailing white space
	// if String only has white space... "trim" it to null
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		// removes whitespaces - leading and trailing
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	
	
	@GetMapping("/list")
	public String listProjects(Model theModel) {
		List<Project> projects = new ArrayList<>();

		projects = projectService.findAll();

		theModel.addAttribute("projects", projects);

		return "projects/list-projects";
	}

	

}
