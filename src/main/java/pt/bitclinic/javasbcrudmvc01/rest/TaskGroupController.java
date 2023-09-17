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
import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;
import pt.bitclinic.javasbcrudmvc01.services.TaskGroupService;

@Controller
@RequestMapping("/taskgroups")
public class TaskGroupController {

	private TaskGroupService taskGroupService;

	// constructor injection of TaskGroupService @Autowired optional, we just have
	// one constructor
	public TaskGroupController(TaskGroupService taskGroupService) {
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
	public String listTaskGroups(Model theModel) {
		List<TaskGroup> taskGroups = new ArrayList<>();

		taskGroups = taskGroupService.findAll();

		theModel.addAttribute("taskGroups", taskGroups);

		return "taskgroups/list-taskgroups";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("taskGroup", new TaskGroup());
		return "taskgroups/taskgroup-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("taskGroupId") Long theId, Model theModel) {

		// get the taskGroup from the service
		TaskGroup taskGroup = taskGroupService.findById(theId);

		theModel.addAttribute("taskGroup", taskGroup);
		return "taskgroups/taskgroup-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("taskGroupId") Long theId) {
		// delete the taskGroup 
		taskGroupService.delete(theId);
		return "redirect:/taskgroups/list";
	}
	
	@PostMapping("/save")
	public String processForm(@Valid @ModelAttribute("taskGroup") TaskGroup taskGroup, BindingResult theBindingResult) {
		if (!theBindingResult.hasErrors()) {
			// save the taskGroup to DB
			taskGroupService.save(taskGroup);

			// use of redirect to prevent duplicate submissions
			return "redirect:/taskgroups/list";
		} else {
			return "clients/taskgroup-form";
		}
	}

}
