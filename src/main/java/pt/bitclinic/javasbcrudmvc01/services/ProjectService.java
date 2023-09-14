package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.Project;

public interface ProjectService {
	
	public List<Project> findAll();	
	public Project findById(Long id);
	public Project save(Project obj);
	public void delete(Long id);
}
