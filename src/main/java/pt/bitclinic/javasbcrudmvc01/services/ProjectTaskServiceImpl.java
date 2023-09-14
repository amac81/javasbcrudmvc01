package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.ProjectTaskRepository;
import pt.bitclinic.javasbcrudmvc01.entities.ProjectTask;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService{

	private ProjectTaskRepository taskRepository;
	
	public ProjectTaskServiceImpl(ProjectTaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Transactional(readOnly = true)	
	public List<ProjectTask> findAll() {
		
		return taskRepository.findAll();
	}

	@Transactional(readOnly = true)	
	public ProjectTask findById(Long id) {
		Optional<ProjectTask> obj = taskRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public ProjectTask save(ProjectTask obj) {
		return taskRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			taskRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());		
		}
	}

	public ProjectTask update(Long id, ProjectTask obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			ProjectTask entity = taskRepository.getReferenceById(id);
			updateData(entity, obj);
			return taskRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(ProjectTask entity, ProjectTask obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setTaskGroup(obj.getTaskGroup());
		entity.setProject(obj.getProject());
		entity.setStatus(obj.getStatus());
		entity.setStartDate(obj.getStartDate());
		entity.setEndDate(obj.getEndDate());
	}

}
