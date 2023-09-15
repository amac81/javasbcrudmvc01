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
import pt.bitclinic.javasbcrudmvc01.entities.Project;
import pt.bitclinic.javasbcrudmvc01.entities.Task;
import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;
import pt.bitclinic.javasbcrudmvc01.entities.Team;
import pt.bitclinic.javasbcrudmvc01.services.ProjectService;
import pt.bitclinic.javasbcrudmvc01.services.TaskService;
import pt.bitclinic.javasbcrudmvc01.services.TaskGroupService;
import pt.bitclinic.javasbcrudmvc01.services.TeamService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private TaskService taskService;
	private TeamService teamService;
	private TaskGroupService taskGroupService;
	private ProjectService projectService;

	// constructor injection of ProjectService @Autowired optional, we just have
	// one constructor
	public TaskController(TaskService taskService, TeamService teamService,
			TaskGroupService taskGroupService, ProjectService projectService) {
		this.taskService = taskService;
		this.teamService = teamService;
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
	public String listtasks(Model theModel) {
		List<Task> tasks = new ArrayList<>();

		tasks = taskService.findAll();

		theModel.addAttribute("tasks", tasks);

		return "tasks/list-tasks";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("projectId") Long projectId, Model theModel) {
		
		Task task = new Task();
		Project project = projectService.findById(projectId);
		
		task.setProject(project);

		List <Team> allTeams = teamService.findAll();
		List <TaskGroup> taskGroups = taskGroupService.findAll();
							
		theModel.addAttribute("task", task);
		theModel.addAttribute("taskGroups", taskGroups);
		theModel.addAttribute("allTeams", allTeams);
		
		return "tasks/task-form";
	}
		
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("taskId") Long taskId, Model theModel) {

		// get the task from the service
		Task task = taskService.findById(taskId);
		List <Team> allTeams = teamService.findAll();
		List <TaskGroup> taskGroups = taskGroupService.findAll();
		
		theModel.addAttribute("task", task);
		theModel.addAttribute("allTeams", allTeams);
		theModel.addAttribute("taskGroups", taskGroups);
		
		return "tasks/task-form";
	}
		

	@PostMapping("/save")
	public String processProjectTaskForm(@Valid @ModelAttribute("task") Task task,
			BindingResult theBindingResult, Model theModel) {
		if (!theBindingResult.hasErrors()) {

			// save the task to DB
			taskService.save(task);

			// use of redirect to prevent duplicate submissions
			//back to projects list
			return "redirect:/projects/list";
			
		} else {
			List <Team> allTeams = teamService.findAll();
			List <TaskGroup> taskGroups = taskGroupService.findAll();
	
			theModel.addAttribute("allTeams", allTeams);
			theModel.addAttribute("taskGroups", taskGroups);
			
			return "tasks/task-form";
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("taskId") Long theId) {
		// delete the project task
		taskService.delete(theId);
		
		//back to projects list
		return "redirect:/projects/list";
	}

}