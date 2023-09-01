package pt.bitclinic.javasbcrudmvc01.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pt.bitclinic.javasbcrudmvc01.entities.Employee;
import pt.bitclinic.javasbcrudmvc01.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	 
	//private List <Employee> employees;
	
	private EmployeeService employeeService;
	
	//constructor injection of EmployeeService @Autowired optional, we just have one constructor
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService =  employeeService;
	}
	
	/*@PostConstruct
	private void loadInMemoryData() {
		//create test data
		Employee employee1 = new Employee(1L, "John", "Doe", "doe1122@yahoo.com", 60000.00, LocalDate.of(2020, 5, 15), "HR");
		Employee employee2 = new Employee(2L, "Jane", "Smith", "jane.smith@mail.org", 75000.00, LocalDate.of(2019, 8, 22), "Finance");
		Employee employee3 = new Employee(3L, "Michael", "Johnson", "mj222@gmail.com", 55000.00, LocalDate.of(2021, 3, 10), "IT");
		Employee employee4 = new Employee(4L, "Emily", "Davis", "edavis@yoooo.com", 80000.00, LocalDate.of(2018, 11, 5), "Marketing");
		Employee employee5 = new Employee(5L, "David", "Wilson", "wilsond@sss.com", 70000.00, LocalDate.of(2022, 2, 18), "Sales");
		Employee employee6 = new Employee(6L, "Sarah", "Brown", "sara@mail.com", 62000.00, LocalDate.of(2020, 9, 30), "Customer Service");

		//create list
		employees = new ArrayList<>();
		
		employees.addAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6));
	}*/
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List <Employee> employees = new ArrayList<> ();
		
		employees = employeeService.findAll();
		
		theModel.addAttribute("employees", employees);
		
		return "list-employees";
	}

}
