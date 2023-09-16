package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.Department;

public interface DepartmentService {
	
	public List<Department> findAll();
	public Department findById(Long id);
	public Department save(Department obj);
	public void delete(Long id);
}
