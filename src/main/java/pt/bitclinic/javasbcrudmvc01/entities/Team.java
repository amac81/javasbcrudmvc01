package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_team")
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "is required")
	private String name;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant createdAt;
	
	private Boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.team")
	private Set<TeamEmployee> teamItems = new HashSet<>();

	public Team() {		
	}

	public Team(Long id, String name, Boolean active) {
		this.id = id;
		this.name = name;
		this.active = active;
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	//in JEE what matters is the "get" word (to serialize to Json)
	@JsonIgnore //to avoid "loop"
	public Set<Employee> getEmployees() {
		Set <Employee> employees = new HashSet<> ();
		
		for(TeamEmployee ti: teamItems){
			employees.add(ti.getEmployee());
		}
		
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
		Team other = (Team) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", active=" + active + "]";
	}

}