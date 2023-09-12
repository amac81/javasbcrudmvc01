package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
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

	private Instant createdAt;
	private Boolean active;

	// One team can have many employees
    @OneToMany(mappedBy = "team")
	private List<Employee> employees;

	public Team() {
		createdAt = Instant.now();
		employees = new ArrayList<>();
	}

	public Team(Long id, @NotNull(message = "is required") String name, Instant createdAt, Boolean active) {
		this.id = id;
		this.name = name;
		this.createdAt = Instant.now();
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

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
		Team other = (Team) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", active=" + active
				+ ", employees=" + employees + "]";
	}

}