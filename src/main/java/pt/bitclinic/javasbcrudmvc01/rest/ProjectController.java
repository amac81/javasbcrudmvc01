package pt.bitclinic.javasbcrudmvc01.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import pt.bitclinic.javasbcrudmvc01.entities.Client;
import pt.bitclinic.javasbcrudmvc01.entities.Project;
import pt.bitclinic.javasbcrudmvc01.entities.Task;
import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;
import pt.bitclinic.javasbcrudmvc01.services.ClientService;
import pt.bitclinic.javasbcrudmvc01.services.ProjectService;
import pt.bitclinic.javasbcrudmvc01.services.TaskGroupService;
import pt.bitclinic.javasbcrudmvc01.services.TaskService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	private ProjectService projectService;
	private TaskService taskService;
	private ClientService clientService;
	private TaskGroupService taskGroupService;
	
	// constructor injection of ProjectService @Autowired optional, we just have
	// one constructor
	public ProjectController(ProjectService projectService, TaskService taskService, ClientService clientService, TaskGroupService taskGroupService) {
		this.projectService = projectService;
		this.taskService = taskService;
		this.clientService = clientService;
		this.taskGroupService = taskGroupService;
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
	
	@GetMapping("/showEmployeeProjects")
	public String listEmployeeProjects(@RequestParam("employeeId") Long employeeId, Model theModel) {
		List<Project> projects = new ArrayList<>();
		
		// get the employee from the service
		//Employee employee = employeeService.findById(employeeId);

	//	projects = projectService.findAllByEmployee(employee);
		
		theModel.addAttribute("projects", projects);
		theModel.addAttribute("employeeId", employeeId);

		return "projects/list-projects";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Project project = new Project();	
		List <Client> clients = clientService.findAll();
				
		theModel.addAttribute("project", project);		
		theModel.addAttribute("clients", clients);
				
		return "projects/project-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("projectId") Long projectId, Model theModel) {

		// get the project from the service
		Project project = projectService.findById(projectId);
		List <Client> clients = clientService.findAll();
		
		theModel.addAttribute("project", project);
		theModel.addAttribute("clients", clients);

		return "projects/project-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("projectId") Long theId) {
		// delete the project 
		projectService.delete(theId);
		return "redirect:/projects/list";
	}
	
	@PostMapping("/save")
	public String processForm(@Valid @ModelAttribute("project") Project project, BindingResult theBindingResult, Model theModel) {
		if (!theBindingResult.hasErrors()) {

			// save the project to DB
			projectService.save(project);
			
			// use of redirect to prevent duplicate submissions
			return "redirect:/projects/list";
		} else {
			List <Client> clients = clientService.findAll();

			theModel.addAttribute("clients", clients);
			return "projects/project-form";
		}
	}
	
	@GetMapping("/showFormForAddTask")
	public String showFormForAddTask(@RequestParam("projectId") Long projectId, Model theModel) {
		
		Task task = new Task();	
		task.setProject(projectService.findById(projectId));
		List <TaskGroup> taskGroups = taskGroupService.findAll();
		//assertEquals(EXPECTED_LIST, result); TEST!!
				
		theModel.addAttribute("task", task);
		theModel.addAttribute("taskGroups", taskGroups);
		
		return "projects/project-task-form";
	}
	
	@PostMapping("/taskSave")
	public String processTaskForm(@Valid @ModelAttribute("task") Task task, BindingResult theBindingResult, Model theModel) {
		if (!theBindingResult.hasErrors()) {

			// save the task to DB
			taskService.save(task);
			
			// use of redirect to prevent duplicate submissions
			return "redirect:/projects/list";
		} else {
			List <TaskGroup> taskGroups = taskGroupService.findAll();
			
			theModel.addAttribute("taskGroups", taskGroups);
			return "projects/project-task-form";
		}
	}

}
