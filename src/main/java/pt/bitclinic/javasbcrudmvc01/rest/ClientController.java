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
import pt.bitclinic.javasbcrudmvc01.services.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

	private ClientService clientService;

	// constructor injection of ClientService @Autowired optional, we just have
	// one constructor
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
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
	public String listClients(Model theModel) {
		List<Client> clients = new ArrayList<>();

		clients = clientService.findAll();

		theModel.addAttribute("clients", clients);

		return "clients/list-clients";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("client", new Client());
		return "clients/client-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("clientId") Long theId, Model theModel) {

		// get the client from the service
		Client client = clientService.findById(theId);

		theModel.addAttribute("client", client);
		return "clients/client-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("clientId") Long theId) {
		// delete the client 
		clientService.delete(theId);
		return "redirect:/clients/list";
	}
	
	@PostMapping("/save")
	public String processForm(@Valid @ModelAttribute("client") Client client, BindingResult theBindingResult) {
		if (!theBindingResult.hasErrors()) {
			// save the client to DB
			clientService.save(client);

			// use of redirect to prevent duplicate submissions
			return "redirect:/clients/list";
		} else {
			return "clients/client-form";
		}
	}

}
