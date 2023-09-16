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
import pt.bitclinic.javasbcrudmvc01.entities.Department;
import pt.bitclinic.javasbcrudmvc01.services.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	private DepartmentService departmentService;

	// constructor injection of departmentService @Autowired optional, we just have
	// one constructor
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
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
	public String listDepartments(Model theModel) {
		List<Department> departments = new ArrayList<>();

		departments = departmentService.findAll();

		theModel.addAttribute("departments", departments);

		return "departments/list-departments";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("department", new Department());
		return "departments/department-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("clientId") Long theId, Model theModel) {

		// get the department from the service
		Department department = departmentService.findById(theId);

		theModel.addAttribute("department", department);
		return "departments/department-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("departmentId") Long theId) {
		// delete the department 
		departmentService.delete(theId);
		return "redirect:/departments/list";
	}
	
	@PostMapping("/save")
	public String processForm(@Valid @ModelAttribute("department") Department department, BindingResult theBindingResult) {
		if (!theBindingResult.hasErrors()) {
			// save the department to DB
			departmentService.save(department);

			// use of redirect to prevent duplicate submissions
			return "redirect:/departments/list";
		} else {
			return "departments/department-form";
		}
	}

}
