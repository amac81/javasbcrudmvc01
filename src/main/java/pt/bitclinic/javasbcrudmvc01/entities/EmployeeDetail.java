package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_employee_detail" )
public class EmployeeDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//Use for bidirectional relationship
	/*@OneToOne //independent class
	@MapsId
	private Employee employee;*/

	@Column(columnDefinition = "TEXT") //more than 255 characters
	private String hobbies;
	private String driveLicenseNumber;
	@Column(columnDefinition = "TEXT") //more than 255 characters 
	private String projectExperience;
	@Column(columnDefinition = "TEXT") //more than 255 characters	   
	private String aditionalNotes;	
	
	@Column(columnDefinition = "TEXT") //more than 255 characters	   
	private String skills; //TODO create object? / entity?

	public EmployeeDetail() {
	}

	public EmployeeDetail(Long id, String hobbies, String driveLicenseNumber, String projectExperience,
			String aditionalNotes) {
		this.id = id;
		this.hobbies = hobbies;
		this.driveLicenseNumber = driveLicenseNumber;
		this.projectExperience = projectExperience;
		this.aditionalNotes = aditionalNotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getDriveLicenseNumber() {
		return driveLicenseNumber;
	}

	public void setDriveLicenseNumber(String driveLicenseNumber) {
		this.driveLicenseNumber = driveLicenseNumber;
	}

	public String getProjectExperience() {
		return projectExperience;
	}

	public void setProjectExperience(String projectExperience) {
		this.projectExperience = projectExperience;
	}

	public String getAditionalNotes() {
		return aditionalNotes;
	}

	public void setAditionalNotes(String aditionalNotes) {
		this.aditionalNotes = aditionalNotes;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
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
		EmployeeDetail other = (EmployeeDetail) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EmployeeDetail [id=" + id + ", hobbies=" + hobbies + ", driveLicenseNumber=" + driveLicenseNumber
				+ ", projectExperience=" + projectExperience + ", aditionalNotes=" + aditionalNotes + ", skills="
				+ skills + "]";
	}

	
}
