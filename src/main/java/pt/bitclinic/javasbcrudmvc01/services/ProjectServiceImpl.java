package pt.bitclinic.javasbcrudmvc01.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.ProjectRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Project;
import pt.bitclinic.javasbcrudmvc01.entities.Task;
import pt.bitclinic.javasbcrudmvc01.entities.enums.Status;
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
		//projects sorted by name
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
	
	public void checkTasksAndUpdateProjectStatus(Long id) {
		Project project = findById(id);
		
		List<Status> allTasksStatus = new ArrayList<>();
		
		for(Task t: project.getTasks()) {
			allTasksStatus.add(t.getStatus());
		}
		
		int cancelled = 0;
		int completed = 0;
		int other = 0;
		int all = allTasksStatus.size();
		
		for (Status s: allTasksStatus) {
			if(s.equals(Status.CANCELED)) {
				cancelled ++;
			}
			else if (s.equals(Status.COMPLETED)) {
				completed ++;
			}
			else {
				other ++;
			}
		}
		
		if(all == cancelled) {
			project.setStatus(Status.CANCELED);
		}
		else if((other == 0) && (all == completed + cancelled)) {
			project.setStatus(Status.COMPLETED);
		}
		else {
			project.setStatus(Status.IN_PROGRESS);
		}		
		
		save(project); //save to DB
		
	}
	
}
