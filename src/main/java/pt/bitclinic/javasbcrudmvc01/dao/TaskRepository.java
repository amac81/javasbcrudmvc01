package pt.bitclinic.javasbcrudmvc01.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javasbcrudmvc01.entities.Task;

//we don't need to implement this interface, because springframework.data.jpa 
//already has a default implementation for this specific type <User, Long>

//@Repository optional (JpaRepository is already registered as a Spring component) 
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	// add a method to sort by startDate .. Spring Data JPA magic!!!
		public List<Task> findAllByOrderByStartDateAsc();
		
}
