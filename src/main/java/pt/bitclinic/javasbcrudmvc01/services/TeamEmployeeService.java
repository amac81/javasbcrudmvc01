package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.TeamEmployee;

public interface TeamEmployeeService {
	
	public List<TeamEmployee> findAll();
	public TeamEmployee findById(Long id);
		
	public TeamEmployee save(TeamEmployee obj);
	public void delete(Long id);
	public void delete(TeamEmployee obj);
	
}
