package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_employee" )
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "is required")
	private String firstName;
	private String lastName;
	
	@NotNull(message = "is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
	private String email;
	private double salary;
		
	private LocalDate hireDate;
	
	@Size(min = 1, message = "is required")
	private String department;
	
	//bidirectional relationship
	@OneToOne (mappedBy = "employee", cascade = CascadeType.ALL) //dependent class
	private EmployeeDetail employeeDetail;

	public Employee() {
	}

	public Employee(Long id, String firstName, String lastName, String email, double salary, LocalDate hireDate, String department) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;
		this.hireDate = hireDate;
		this.department = department;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public EmployeeDetail getEmployeeDetail() {
		return employeeDetail;
	}

	public void setEmployeeDetail(EmployeeDetail employeeDetail) {
		this.employeeDetail = employeeDetail;
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
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", salary=" + salary + ", hireDate=" + hireDate + ", department=" + department + ", employeeDetail="
				+ employeeDetail + "]";
	}


	
	/*
	 * private Long id;
	 * 
	 * @NotNull(message = "is required")
	 * 
	 * @Size(min = 1, message = "is required") private String firstName;
	 * 
	 * @NotNull(message = "is required")
	 * 
	 * @Size(min = 1, message = "is required") private String lastName;
	 * 
	 * @NotNull(message = "is required")
	 * 
	 * @Min(value = 0, message = "must be greater than or equal to zero")
	 * 
	 * @Max(value = 10, message = "must be less than or equal to ten") private
	 * Integer freePasses;
	 * 
	 * @NotNull(message = "is required")
	 * 
	 * @Pattern(regexp = "^\\d{4}-\\d{3}$", message="must be in the form ####-###")
	 * // PT postal code ####-### private String postalCode;
	 * 
	 * @CourseCode(value="TIC", message="must start with TIC") private String
	 * courseCode;
	 * 
	 * 
	 * 
	 */
}
