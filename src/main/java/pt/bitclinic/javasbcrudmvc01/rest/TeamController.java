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
import pt.bitclinic.javasbcrudmvc01.entities.Team;
import pt.bitclinic.javasbcrudmvc01.entities.TeamItem;
import pt.bitclinic.javasbcrudmvc01.services.EmployeeService;
import pt.bitclinic.javasbcrudmvc01.services.TeamItemService;
import pt.bitclinic.javasbcrudmvc01.services.TeamService;

@Controller
@RequestMapping("/teams")
public class TeamController {

	private TeamService teamService;
	private EmployeeService employeeService;
	private TeamItemService teamItemService;

	// constructor injection of teamService @Autowired optional, we just have
	// one constructor
	public TeamController(TeamService teamService, EmployeeService employeeService, TeamItemService teamItemService) {
		this.teamService = teamService;
		this.employeeService = employeeService;
		this.teamItemService = teamItemService;
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
	public String listTeams(Model theModel) {
		List<Team> teams = new ArrayList<>();

		teams = teamService.findAll();
		theModel.addAttribute("teams", teams);

		return "teams/list-teams";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Team team = new Team();
		List <Employee> allEmployees = employeeService.findAll();
		
		theModel.addAttribute("team", team);
		theModel.addAttribute("allEmployees", allEmployees);
				
		return "teams/team-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("teamId") Long theId, Model theModel) {

		// get the team from the service
		Team team = teamService.findById(theId);
		
		List <Employee> allEmployees = employeeService.findAll();
		theModel.addAttribute("allEmployees", allEmployees);
		
		theModel.addAttribute("team", team);
		return "teams/team-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("teamId") Long theId) {
		// delete the team 
		teamService.delete(theId);
		return "redirect:/teams/list";
	}
	
	@PostMapping("/save")
	public String processForm(@Valid @ModelAttribute("team") Team team, BindingResult theBindingResult) {
		if (!theBindingResult.hasErrors()) {
			
			// save the team to DB
			teamService.save(team);

			// use of redirect to prevent duplicate submissions
			return "redirect:/teams/list";
		} else {
			return "teams/team-form";
		}
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(@RequestParam("teamId") Long teamId, @RequestParam("employeeId") Long employeeId) { 
			
			Employee employee = employeeService.findById(employeeId);
			Team team = teamService.findById(teamId);
			
			TeamItem teamItem = new TeamItem(team, employee);
			
			// save the teamItem to DB
			teamItemService.save(teamItem);

			// use of redirect to prevent duplicate submissions
			return "redirect:/teams/list";
		
	}

}

