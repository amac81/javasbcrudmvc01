package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	public Employee findById(Long id);
	public Employee save(Employee obj);
	public void delete(Long id);
	public Employee update(Long id, Employee obj) ;
	
}
