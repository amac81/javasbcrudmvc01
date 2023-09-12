package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.TeamItem;

public interface TeamItemService {
	
	public List<TeamItem> findAll();
	public TeamItem findById(Long id);
	public TeamItem save(TeamItem obj);
	public void delete(Long id);
	
}
