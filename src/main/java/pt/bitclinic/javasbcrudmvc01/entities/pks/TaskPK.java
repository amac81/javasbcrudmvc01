package pt.bitclinic.javasbcrudmvc01.entities.pks;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pt.bitclinic.javasbcrudmvc01.entities.Project;
import pt.bitclinic.javasbcrudmvc01.entities.Team;

@Embeddable
public class TaskPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(project, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskPK other = (TaskPK) obj;
		return Objects.equals(project, other.project) && Objects.equals(team, other.team);
	}
}
