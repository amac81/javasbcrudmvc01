package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.ProjectRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Project;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService{

	private ProjectRepository projectRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Transactional(readOnly = true)	
	public List<Project> findAll() {
		//employees sorted by last name
		return projectRepository.findAllByOrderByNameAsc();
	}

	@Transactional(readOnly = true)	
	public Project findById(Long id) {
		Optional<Project> obj = projectRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public Project save(Project obj) {
		return projectRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			projectRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());		
		}
	}

	public Project update(Long id, Project obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			Project entity = projectRepository.getReferenceById(id);
			updateData(entity, obj);
			return projectRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Project entity, Project obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setStatus(obj.getStatus());
		entity.setClient(obj.getClient());	
	}
	
}
