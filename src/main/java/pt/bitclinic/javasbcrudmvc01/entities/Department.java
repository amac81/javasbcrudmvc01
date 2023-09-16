package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_department" )
public class Department implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "is required")
    private String name;
	
    private String description;
    
    //unidirectional relationship
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  	@JoinColumn(name="hod_employee_id")
    private Employee headOfDepartment; // Reference to the department head
    
  	private LocalDate establishedDate;
    
  	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  	private List<Employee> employees = new ArrayList<>();

  	public Department() {}
  	
	public Department(Long id, String name, String description,
			Employee headOfDepartment, LocalDate establishedDate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.headOfDepartment = headOfDepartment;
		this.establishedDate = establishedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(Employee headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public LocalDate getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", description=" + description + ", headOfDepartment="
				+ headOfDepartment + ", establishedDate=" + establishedDate + ", employees=" + employees + "]";
	}

}
