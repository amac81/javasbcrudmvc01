package pt.bitclinic.javasbcrudmvc01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.TeamTaskRepository;
import pt.bitclinic.javasbcrudmvc01.entities.TeamTask;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class TeamTaskServiceImpl implements TeamTaskService {

	private TeamTaskRepository teamTaskRepository;

	public TeamTaskServiceImpl(TeamTaskRepository teamTaskRepository) {
		this.teamTaskRepository = teamTaskRepository;
	}

	@Transactional(readOnly = true)
	public List<TeamTask> findAll() {
		return teamTaskRepository.findAll();
	}

	@Transactional(readOnly = true)
	public TeamTask findById(Long id) {
		Optional<TeamTask> obj = teamTaskRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public TeamTask save(TeamTask obj) {
		return teamTaskRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			teamTaskRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());
		}
	}

	public TeamTask update(Long id, TeamTask obj) {
		try {
			// getReferenceById more efficient than findById
			// getReferenceById only "prepares" the monitored object
			TeamTask entity = teamTaskRepository.getReferenceById(id);
			updateData(entity, obj);
			return teamTaskRepository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(TeamTask entity, TeamTask obj) {
		entity.setTeam(obj.getTeam());
		entity.setTask(obj.getTask());
	}

	@Override
	public void delete(TeamTask obj) {
		try {
			teamTaskRepository.delete(obj);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(obj);
		} catch (DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());
		}
	}
}
