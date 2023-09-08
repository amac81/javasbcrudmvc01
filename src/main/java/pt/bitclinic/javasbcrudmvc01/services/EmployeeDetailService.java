package pt.bitclinic.javasbcrudmvc01.services;
import java.util.List;

import pt.bitclinic.javasbcrudmvc01.entities.EmployeeDetail;

public interface EmployeeDetailService {
	
	public List<EmployeeDetail> findAll();
	public EmployeeDetail findById(Long id);
	public EmployeeDetail save(EmployeeDetail obj);
	public void delete(Long id);
}
