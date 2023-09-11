package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.TaskGroupRepository;
import pt.bitclinic.javasbcrudmvc01.entities.TaskGroup;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class TaskGroupServiceImpl implements TaskGroupService{

	private TaskGroupRepository taskGroupRepository;
	
	public TaskGroupServiceImpl(TaskGroupRepository taskGroupRepository) {
		this.taskGroupRepository = taskGroupRepository;
	}

	@Transactional(readOnly = true)	
	public List<TaskGroup> findAll() {
		//employees sorted by last name
		return taskGroupRepository.findAllByOrderByDescriptionAsc();
	}

	@Transactional(readOnly = true)	
	public TaskGroup findById(Long id) {
		Optional<TaskGroup> obj = taskGroupRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public TaskGroup save(TaskGroup obj) {
		return taskGroupRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			taskGroupRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());		
		}
	}

	public TaskGroup update(Long id, TaskGroup obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			TaskGroup entity = taskGroupRepository.getReferenceById(id);
			updateData(entity, obj);
			return taskGroupRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(TaskGroup entity, TaskGroup obj) {
		entity.setDescription(obj.getDescription());		
	}
	
}
