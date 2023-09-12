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
import pt.bitclinic.javasbcrudmvc01.entities.ProjectTask;
import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;
import pt.bitclinic.javasbcrudmvc01.services.EmployeeService;
import pt.bitclinic.javasbcrudmvc01.services.ProjectService;
import pt.bitclinic.javasbcrudmvc01.services.ProjectTaskService;
import pt.bitclinic.javasbcrudmvc01.services.TaskGroupService;

@Controller
@RequestMapping("/tasks")
public class ProjectTaskController {

	private ProjectTaskService projectTaskService;
	private ProjectService projectService;
	private EmployeeService employeeService;
	private TaskGroupService taskGroupService;

	// constructor injection of ProjectService @Autowired optional, we just have
	// one constructor
	public ProjectTaskController(ProjectTaskService projectTaskService, ProjectService projectService,
			EmployeeService employeeService, TaskGroupService taskGroupService) {
		this.projectTaskService = projectTaskService;
		this.projectService = projectService;
		this.employeeService = employeeService;
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
	public String listProjectTasks(Model theModel) {
		List<ProjectTask> projectTasks = new ArrayList<>();

		projectTasks = projectTaskService.findAll();

		theModel.addAttribute("projectTasks", projectTasks);

		return "tasks/list-project-tasks";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("projectId") Long projectId, Model theModel) {
		
		ProjectTask projectTask = new ProjectTask();
		
		
		
		List <TaskGroup> taskGroups = taskGroupService.findAll();
		List <Employee> employees = employeeService.findAll();
				
		theModel.addAttribute("projectTask", projectTask);
		theModel.addAttribute("taskGroups", taskGroups);
		theModel.addAttribute("employees", employees);
		
		return "tasks/project-task-form";
	}

	@PostMapping("/save")
	public String processProjectTaskForm(@Valid @ModelAttribute("projectTask") ProjectTask projectTask,
			BindingResult theBindingResult, Model theModel) {
		if (!theBindingResult.hasErrors()) {

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