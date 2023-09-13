package pt.bitclinic.javasbcrudmvc01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.TeamEmployeeRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Employee;
import pt.bitclinic.javasbcrudmvc01.entities.Team;
import pt.bitclinic.javasbcrudmvc01.entities.TeamEmployee;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class TeamEmployeeServiceImpl implements TeamEmployeeService {

	private TeamEmployeeRepository teamItemRepository;

	public TeamEmployeeServiceImpl(TeamEmployeeRepository teamItemRepository) {
		this.teamItemRepository = teamItemRepository;
	}

	@Transactional(readOnly = true)
	public List<TeamEmployee> findAll() {
		return teamItemRepository.findAll();
	}

	@Transactional(readOnly = true)
	public TeamEmployee findById(Long id) {
		Optional<TeamEmployee> obj = teamItemRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public TeamEmployee save(TeamEmployee obj) {
		return teamItemRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			teamItemRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());
		}
	}

	public TeamEmployee update(Long id, TeamEmployee obj) {
		try {
			// getReferenceById more efficient than findById
			// getReferenceById only "prepares" the monitored object
			TeamEmployee entity = teamItemRepository.getReferenceById(id);
			updateData(entity, obj);
			return teamItemRepository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(TeamEmployee entity, TeamEmployee obj) {
		entity.setEmployee(obj.getEmployee());
		entity.setTeam(obj.getTeam());
	}

	@Override
	public void delete(TeamEmployee obj) {
		try {
			teamItemRepository.delete(obj);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(obj);
		} catch (DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public TeamEmployee findByIds(Team team, Employee employee) {
		
		
		return null;
				
		
	}
	

}
