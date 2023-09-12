package pt.bitclinic.javasbcrudmvc01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.TeamItemRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Employee;
import pt.bitclinic.javasbcrudmvc01.entities.Team;
import pt.bitclinic.javasbcrudmvc01.entities.TeamItem;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class TeamItemServiceImpl implements TeamItemService {

	private TeamItemRepository teamItemRepository;

	public TeamItemServiceImpl(TeamItemRepository teamItemRepository) {
		this.teamItemRepository = teamItemRepository;
	}

	@Transactional(readOnly = true)
	public List<TeamItem> findAll() {
		return teamItemRepository.findAll();
	}

	@Transactional(readOnly = true)
	public TeamItem findById(Long id) {
		Optional<TeamItem> obj = teamItemRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public TeamItem save(TeamItem obj) {
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

	public TeamItem update(Long id, TeamItem obj) {
		try {
			// getReferenceById more efficient than findById
			// getReferenceById only "prepares" the monitored object
			TeamItem entity = teamItemRepository.getReferenceById(id);
			updateData(entity, obj);
			return teamItemRepository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(TeamItem entity, TeamItem obj) {
		entity.setEmployee(obj.getEmployee());
		entity.setTeam(obj.getTeam());
	}

	@Override
	public void delete(TeamItem obj) {
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
	public TeamItem findByIds(Team team, Employee employee) {
		TeamItem temp = new TeamItem(team, employee);
		
		
		return null;
				
		
	}
	

}
