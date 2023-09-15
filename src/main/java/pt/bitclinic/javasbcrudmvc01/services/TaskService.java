package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.Task;

public interface TaskService {
	
	public List<Task> findAll();
	public Task findById(Long id);
	public Task save(Task obj);
	public void delete(Long id);
}
