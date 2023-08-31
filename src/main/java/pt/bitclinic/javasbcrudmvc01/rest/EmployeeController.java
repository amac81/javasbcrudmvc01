package pt.bitclinic.javasbcrudmvc01.rest;

import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

/*	List<Employee> customers = new ArrayList<>();
	
		
	// Pre-process all web requests coming into our Controller
	// Pre-process every String form data; remove leading and trailing white space
	// if String only has white space... "trim" it to null
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		// removes whitespaces - leading and trailing
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/customers")
	public String showForm(Model theModel) {
		theModel.addAttribute("customers", customers);
		Employee customer = new Employee();
		customer.setId(0L);
		theModel.addAttribute("customer", customer);

		return "customer-form";
	}

	@PostMapping("/customers")
	public String processForm(@Valid @ModelAttribute("customer") Employee theCustomer, BindingResult theBindingResult) {
		
		if (!theBindingResult.hasErrors()) {
			theCustomer.setId(0L);
			customers.add(theCustomer);
			return "redirect:/customers";
		}
		else 
		{
			return "customer-form";
		}
				
	}*/
}
