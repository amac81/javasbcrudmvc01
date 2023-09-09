package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import pt.bitclinic.javasbcrudmvc01.entities.enums.TaskStatus;

@Entity
@Table(name = "tb_project_task")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "is required")
	private String name;

	@NotNull(message = "is required")
	private String description;

	@NotNull(message = "is required")
	private String groupDescription;

	@NotNull(message = "is required")
	private LocalDateTime endDate;
	private LocalDateTime startDate;

	private Integer status;
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	// unidirectional relationship
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id")
	private Team team;

	public Task() {
	}

	public Task(Long id, String name, String groupDescription, String description, LocalDateTime startDate,
			LocalDateTime endDate, Team team, Project project, TaskStatus status) {
		this.id = id;
		this.name = name;
		this.groupDescription = groupDescription;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.project = project;
		this.team = team;
		setStatus(status);
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

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String group) {
		this.groupDescription = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public TaskStatus getStatus() {
		return TaskStatus.valueOf(status);
	}

	public void setStatus(TaskStatus status) {
		this.status = status.getCode();
	}

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
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", groupDescription="
				+ groupDescription + ", endDate=" + endDate + ", startDate=" + startDate + ", status=" + status
				+ ", team=" + team + "]";
	}

}
