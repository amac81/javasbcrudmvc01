package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javasbcrudmvc01.dao.EmployeeDetailRepository;
import pt.bitclinic.javasbcrudmvc01.entities.EmployeeDetail;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.DatabaseException;
import pt.bitclinic.javasbcrudmvc01.services.exceptions.ResourceNotFoundException;

@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService{

	private EmployeeDetailRepository employeeDetailRepository;
	
	public EmployeeDetailServiceImpl(EmployeeDetailRepository employeeDetailRepository) {
		this.employeeDetailRepository = employeeDetailRepository;
	}

	@Transactional(readOnly = true)	
	public List<EmployeeDetail> findAll() {
		return employeeDetailRepository.findAll();
	}

	@Transactional(readOnly = true)	
	public EmployeeDetail findById(Long id) {
		Optional<EmployeeDetail> obj = employeeDetailRepository.findById(id);
		return obj.orElseThrow(()->  new ResourceNotFoundException(id));
	}
	
	public EmployeeDetail save(EmployeeDetail obj) {
		return employeeDetailRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			employeeDetailRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());		
		}
	}

	public EmployeeDetail update(Long id, EmployeeDetail obj) {
		try {
			//getReferenceById more efficient than findById
			//getReferenceById only "prepares" the monitored object 
			EmployeeDetail entity = employeeDetailRepository.getReferenceById(id);
			updateData(entity, obj);
			return employeeDetailRepository.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}
	
	private void updateData(EmployeeDetail entity, EmployeeDetail obj) {
		entity.setDriveLicenseNumber(obj.getDriveLicenseNumber());
		entity.setHobbies(obj.getHobbies());
		entity.setAditionalNotes(obj.getAditionalNotes());		
	}	
}
