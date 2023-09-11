package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;

public interface TaskGroupService {
	
	public List<TaskGroup> findAll();
	public TaskGroup findById(Long id);
	public TaskGroup save(TaskGroup obj);
	public void delete(Long id);
}
