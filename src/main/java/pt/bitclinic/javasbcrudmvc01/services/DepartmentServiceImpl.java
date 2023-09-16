package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.DepartmentRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Department;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Transactional(readOnly = true)	
	public List<Department> findAll() {
		//department sorted by name
		return departmentRepository.findAllByOrderByNameAsc();
	}

	@Transactional(readOnly = true)	
	public Department findById(Long id) {
		Optional<Department> obj = departmentRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public Department save(Department obj) {
		return departmentRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			departmentRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());		
		}
	}

	public Department update(Long id, Department obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			Department entity = departmentRepository.getReferenceById(id);
			updateData(entity, obj);
			return departmentRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Department entity, Department obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setEstablishedDate(obj.getEstablishedDate());
		entity.setHeadOfDepartment(obj.getHeadOfDepartment());
	}
	
}
