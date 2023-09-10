package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.Client;

public interface ClientService {
	
	public List<Client> findAll();
	public Client findById(Long id);
	public Client save(Client obj);
	public void delete(Long id);
}
