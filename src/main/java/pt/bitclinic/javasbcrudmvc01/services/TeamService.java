package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.Team;

public interface TeamService {
	
	public List<Team> findAll();
	public Team findById(Long id);
	public Team save(Team obj);
	public void delete(Long id);
}
