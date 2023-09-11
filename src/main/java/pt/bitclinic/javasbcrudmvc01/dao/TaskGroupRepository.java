package pt.bitclinic.javasbcrudmvc01.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;

//we don't need to implement this interface, because springframework.data.jpa 
//already has a default implementation for this specific type <User, Long>

//@Repository optional (JpaRepository is already registered as a Spring component) 
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
		// add a method to sort by Description ASC.. Spring Data JPA magic!!!
		public List<TaskGroup> findAllByOrderByDescriptionAsc();
}
