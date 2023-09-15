package pt.bitclinic.javasbcrudmvc01.entities;

import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pt.bitclinic.javasbcrudmvc01.entities.pks.TeamTaskPK;

//Join table between tb_team and tb_task
//Team could work at more than one ProjectTask
//ProjectTask could have more than one Team Working on
@Entity
@Table(name = "tb_team_task")
public class TeamTask {
	
	@EmbeddedId
	private TeamTaskPK id = new TeamTaskPK();
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Instant createdAt;
	
	public TeamTask() {
	}

	public TeamTask(Team team, Task task ) {
		id.setTeam(team);
		id.setTask(task);
	}
	
	//in JEE what matters is the get method; to avoid "loop"
	@JsonIgnore
	public Team getTeam() {
		return id.getTeam();
	}
	
	public void setTeam(Team team) {
		id.setTeam(team);
	}
	
	public Task getTask() {
		return id.getTask();
	}
	
	public void setTask(Task task) {
		id.setTask(task);
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
		TeamTask other = (TeamTask) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TeamTask [Team=" + id.getTeam().getName() + ", Task= " + id.getTask().getName() +"]";
	}
	
	
}