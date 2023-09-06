package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.EmployeeRepository;
import pt.bitclinic.javasbcrudmvc01.entities.Employee;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional(readOnly = true)	
	public List<Employee> findAll() {
		//employees sorted by last name
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Transactional(readOnly = true)	
	public Employee findById(Long id) {
		Optional<Employee> obj = employeeRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public Employee save(Employee obj) {
		return employeeRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			employeeRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());		
		}
	}

	public Employee update(Long id, Employee obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			Employee entity = employeeRepository.getReferenceById(id);
			updateData(entity, obj);
			return employeeRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(Employee entity, Employee obj) {
		entity.setFirstName(obj.getFirstName());
		entity.setLastName(obj.getLastName());
		entity.setEmail(obj.getEmail());
		entity.setHireDate(obj.getHireDate());
		entity.setSalary(obj.getSalary());
		entity.setDepartment(obj.getDepartment());
	}

	
}
