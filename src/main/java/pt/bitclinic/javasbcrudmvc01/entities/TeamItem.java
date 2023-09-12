package pt.bitclinic.javasbcrudmvc01.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pt.bitclinic.javasbcrudmvc01.entities.pks.TeamItemPK;

@Entity
@Table(name = "tb_team_item")
public class TeamItem {
	
	@EmbeddedId
	private TeamItemPK id = new TeamItemPK();
	
	public TeamItem() {
	}

	public TeamItem(Team team, Employee employee ) {
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
		TeamItem other = (TeamItem) obj;
		return Objects.equals(id, other.id);
	}
}