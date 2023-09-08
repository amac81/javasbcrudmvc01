package pt.bitclinic.javasbcrudmvc01.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javasbcrudmvc01.entities.Employee;
import pt.bitclinic.javasbcrudmvc01.entities.Project;

//we don't need to implement this interface, because springframework.data.jpa 
//already has a default implementation for this specific type <User, Long>

//@Repository optional (JpaRepository is already registered as a Spring component) 
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	// add a method to sort by name.. Spring Data JPA magic!!!
	public List<Project> findAllByOrderByNameAsc();
	
	public List<Project> findAllByEmployee(Employee employee);
}
