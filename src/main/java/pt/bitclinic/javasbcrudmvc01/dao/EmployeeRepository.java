package pt.bitclinic.javasbcrudmvc01.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javasbcrudmvc01.entities.Employee;

//we don't need to implement this interface, because springframework.data.jpa 
//already has a default implementation for this specific type <User, Long>

//@Repository optional (JpaRepository is already registered as a Spring component) 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	// add a method to sort by last name.. Spring Data JPA magic!!!
	public List<Employee> findAllByOrderByLastNameAsc();
}
