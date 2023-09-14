package pt.bitclinic.javasbcrudmvc01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javasbcrudmvc01.entities.ProjectTask;

//we don't need to implement this interface, because springframework.data.jpa 
//already has a default implementation for this specific type <User, Long>

//@Repository optional (JpaRepository is already registered as a Spring component) 
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
	
	
}
