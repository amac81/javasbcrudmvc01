package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.ProjectTask;

public interface ProjectTaskService {
	
	public List<ProjectTask> findAll();
	public ProjectTask findById(Long id);
	public ProjectTask save(ProjectTask obj);
	public void delete(Long id);
}
