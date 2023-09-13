package pt.bitclinic.javasbcrudmvc01.entities;

import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pt.bitclinic.javasbcrudmvc01.entities.pks.TeamEmployeePK;

//Join table between tb_team and tb_employee	
@Entity
@Table(name = "tb_team_employee")
public class TeamEmployee {
	
	@EmbeddedId
	private TeamEmployeePK id = new TeamEmployeePK();
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant createdAt;
	
	public TeamEmployee() {
	}

	public TeamEmployee(Team team, Employee employee ) {
		id.setTeam(team);
		id.setEmployee(employee);
	}
	
	//in JEE what matters is the get method; to avoid "loop"
	@JsonIgnore
	public Team getTeam() {
		return id.getTeam();
	}
	
	public void setTeam(Team team) {
		id.setTeam(team);
	}
	
	public Employee getEmployee() {
		return id.getEmployee();
	}

	public void setEmployee(Employee employee) {
		id.setEmployee(employee);
	}
	
	public Instant getCreatedAt() {
		return createdAt;
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
		TeamEmployee other = (TeamEmployee) obj;
		return Objects.equals(id, other.id);
	}
}