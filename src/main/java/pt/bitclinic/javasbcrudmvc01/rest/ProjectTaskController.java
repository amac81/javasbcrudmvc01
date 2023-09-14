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
import pt.bitclinic.javasbcrudmvc01.entities.Employee;
import pt.bitclinic.javasbcrudmvc01.entities.Project;
import pt.bitclinic.javasbcrudmvc01.entities.ProjectTask;
import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;
import pt.bitclinic.javasbcrudmvc01.entities.Team;
import pt.bitclinic.javasbcrudmvc01.services.EmployeeService;
import pt.bitclinic.javasbcrudmvc01.services.ProjectService;
import pt.bitclinic.javasbcrudmvc01.services.ProjectTaskService;
import pt.bitclinic.javasbcrudmvc01.services.TaskGroupService;
import pt.bitclinic.javasbcrudmvc01.services.TeamService;

@Controller
@RequestMapping("/tasks")
public class ProjectTaskController {

	private ProjectTaskService projectTaskService;
	private EmployeeService employeeService;
	private TeamService teamService;
	private TaskGroupService taskGroupService;
	private ProjectService projectService;

	// constructor injection of ProjectService @Autowired optional, we just have
	// one constructor
	public ProjectTaskController(ProjectTaskService projectTaskService, TeamService teamService,
			EmployeeService employeeService, TaskGroupService taskGroupService, ProjectService projectService) {
		this.projectTaskService = projectTaskService;
		this.teamService = teamService;
		this.employeeService = employeeService;
		this.taskGroupService = taskGroupService;
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
	public String listProjectTasks(Model theModel) {
		List<ProjectTask> projectTasks = new ArrayList<>();

		projectTasks = projectTaskService.findAll();

		theModel.addAttribute("projectTasks", projectTasks);

		return "tasks/list-project-tasks";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("projectId") Long projectId, Model theModel) {
		
		ProjectTask projectTask = new ProjectTask();
		Project project = projectService.findById(projectId);
			
		projectTask.setProject(project);
		
		List <TaskGroup> taskGroups = taskGroupService.findAll();
		List <Employee> employees = employeeService.findAll();
				
		theModel.addAttribute("projectTask", projectTask);
		theModel.addAttribute("taskGroups", taskGroups);
		theModel.addAttribute("employees", employees);
		
		return "tasks/project-task-form";
	}
		
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("projectTaskId") Long projectTaskId, Model theModel) {

		// get the projectTask from the service
		ProjectTask projectTask = projectTaskService.findById(projectTaskId);
		List <Team> allTeams = teamService.findAll();
		
		theModel.addAttribute("projectTask", projectTask);
		theModel.addAttribute("allTeams", allTeams);

		return "projects/project-form";
	}
		

	@PostMapping("/save")
	public String processProjectTaskForm(@Valid @ModelAttribute("projectTask") ProjectTask projectTask,
			BindingResult theBindingResult, Model theModel) {
		if (!theBindingResult.hasErrors()) {

			
			System.out.println("############################ projecTask SAVE: " + projectTask);
			
			
			// save the task to DB
			projectTaskService.save(projectTask);

			// use of redirect to prevent duplicate submissions
			return "redirect:/tasks/list";
		} else {
			List<TaskGroup> taskGroups = taskGroupService.findAll();
			List<Employee> employees = employeeService.findAll();

			theModel.addAttribute("employees", employees);
			theModel.addAttribute("taskGroups", taskGroups);
			return "tasks/project-task-form";
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("projectTaskId") Long theId) {
		// delete the project task
		projectTaskService.delete(theId);
		return "redirect:/tasks/list";
	}

}