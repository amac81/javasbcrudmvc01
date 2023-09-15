package pt.bitclinic.javasbcrudmvc01.entities.pks;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pt.bitclinic.javasbcrudmvc01.entities.Task;
import pt.bitclinic.javasbcrudmvc01.entities.Team;

@Embeddable
public class TeamTaskPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public int hashCode() {
		return Objects.hash(task, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamTaskPK other = (TeamTaskPK) obj;
		return Objects.equals(task, other.task) && Objects.equals(team, other.team);
	}

}

