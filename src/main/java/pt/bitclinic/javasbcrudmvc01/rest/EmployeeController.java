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
import pt.bitclinic.javasbcrudmvc01.entities.EmployeeDetail;
import pt.bitclinic.javasbcrudmvc01.services.EmployeeDetailService;
import pt.bitclinic.javasbcrudmvc01.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	private EmployeeDetailService employeeDetailService;

	// constructor injection of EmployeeService @Autowired optional, we just have
	// one constructor
	public EmployeeController(EmployeeService employeeService, EmployeeDetailService employeeDetailService) {
		this.employeeService = employeeService;
		this.employeeDetailService = employeeDetailService;
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

	@GetMapping("/leaders")
	public String leadersPage() {
	return "employees/leaders";
	}
	
	@GetMapping("/systems")
	public String systemsPage() {
	return "employees/systems";
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> employees = new ArrayList<>();

		employees = employeeService.findAll();

		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("employee", new Employee());
		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") Long theId, Model theModel) {

		// get the employee from the service
		Employee employee = employeeService.findById(theId);

		theModel.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") Long theId) {
		// delete the employee 
		employeeService.delete(theId);
		return "redirect:/employees/list";
	}
	
	@PostMapping("/save")
	public String processForm(@Valid @ModelAttribute("employee") Employee theEmployee, BindingResult theBindingResult) {
		if (!theBindingResult.hasErrors()) {
			// save the employee to DB
			employeeService.save(theEmployee);

			// use of redirect to prevent duplicate submissions
			return "redirect:/employees/list";
		} else {
			return "employees/employee-form";
		}
	}

	/* Employee Details*/
	@GetMapping("/showDetails")
	public String employeeDetails(@RequestParam("employeeId") Long employeeId, Model theModel) {

		// get the employee from the service
		Employee employee = employeeService.findById(employeeId);
		EmployeeDetail employeeDetail = employee.getEmployeeDetail();
		
		theModel.addAttribute("employeeId", employeeId);
		theModel.addAttribute("employeeDetail", employeeDetail);
	
		return "employees/employee-detail-form";
	}
	
	@GetMapping("/showFormForAddDetails")
	public String showFormForAddDetails(@RequestParam("employeeId") Long employeeId, Model theModel) {
		EmployeeDetail employeeDetail = new EmployeeDetail();
	
		theModel.addAttribute("employeeId", employeeId);
		theModel.addAttribute("employeeDetail", employeeDetail);
	
		return "employees/employee-detail-form";
	}	
	
	@PostMapping("/saveDetails")
	public String saveDetails(@Valid @ModelAttribute("employeeDetail") EmployeeDetail employeeDetail, 
			@ModelAttribute("employeeId") Long employeeId,	BindingResult theBindingResult) {
		if (!theBindingResult.hasErrors()) {
			
			employeeDetailService.save(employeeDetail);
			Employee employee = employeeService.findById(employeeId);
			employee.setEmployeeDetail(employeeDetail);
			employeeService.save(employee);
			
			// use of redirect to prevent duplicate submissions
			return "redirect:/employees/list";
		} else {
			return "employees/employee-form";
		}
	}
	

}
