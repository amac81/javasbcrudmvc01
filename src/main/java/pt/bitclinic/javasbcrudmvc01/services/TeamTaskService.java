package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.TeamTask;

public interface TeamTaskService {
	
	public List<TeamTask> findAll();
	public TeamTask findById(Long id);
		
	public TeamTask save(TeamTask obj);
	public void delete(Long id);
	public void delete(TeamTask obj);
	
}
