package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.TaskRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Task;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class TaskServiceImpl implements TaskService{

	private TaskRepository taskRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Transactional(readOnly = true)	
	public List<Task> findAll() {
		
		return taskRepository.findAll();
	}

	@Transactional(readOnly = true)	
	public Task findById(Long id) {
		Optional<Task> obj = taskRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public Task save(Task obj) {
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

	public Task update(Long id, Task obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			Task entity = taskRepository.getReferenceById(id);
			updateData(entity, obj);
			return taskRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Task entity, Task obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setStartDate(obj.getStartDate());
		entity.setEndDate(obj.getEndDate());
		entity.setGroupDescription(obj.getGroupDescription());
		entity.setStatus(obj.getStatus());
	}

	
}
