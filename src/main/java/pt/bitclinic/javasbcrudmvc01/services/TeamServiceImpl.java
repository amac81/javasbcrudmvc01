package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.TeamRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Employee;
import pt.bitclinic.javasbcrudmvc01.entities.Team;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class TeamServiceImpl implements TeamService{

	private TeamRepository teamRepository;
	
	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Transactional(readOnly = true)	
	public List<Team> findAll() {
		return teamRepository.findAllByOrderByNameAsc();
	}

	@Transactional(readOnly = true)	
	public Team findById(Long id) {
		Optional<Team> obj = teamRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public Team save(Team obj) {
		return teamRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			teamRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());		
		}
	}

	public Team update(Long id, Team obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			Team entity = teamRepository.getReferenceById(id);
			updateData(entity, obj);
			return teamRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Team entity, Team obj) {
		entity.setActive(obj.getActive());
		entity.setName(obj.getName());
		entity.setCreatedAt(obj.getCreatedAt());		
	}

	@Override
	public void addEmployee(Long id, Employee employee) {
		Team team = findById(id);
		team.getEmployees().add(employee);
		save(team);
	}

	@Override
	public void removeEmployee(Long id, Employee employee) {
		Team team = findById(id);
		team.getEmployees().remove(employee);
		save(team);		
	}
	
}
