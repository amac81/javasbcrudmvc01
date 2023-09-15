package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import pt.bitclinic.javasbcrudmvc01.entities.enums.Status;

@Entity
@Table(name = "tb_task")
public class Task implements Serializable, Comparable<Task>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotNull(message = "is required")
	private String name;

	@Column(columnDefinition = "TEXT") // more than 255 characters
	@NotNull(message = "is required")
	private String description;

	// unidirectional relationship
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "taskgroup_id")
	private TaskGroup taskGroup;

	@NotNull(message = "is required")
	//@FutureOrPresent (message = "must be a date/time in the present or future")
	//@Column(nullable = false, updatable = false)
	private LocalDateTime startDate;
	
	@NotNull(message = "is required")
	//ensures that the endDate contain a value representing a date and time that is in the future.
	//@Future(message = "must be a future date/time")
	//@Column(nullable = false, updatable = false)
	private LocalDateTime endDate;

	@NotNull(message = "is required")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade= {CascadeType.PERSIST, 
				CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //do not cascade Deletes
	private Set<Team> teams = new HashSet<>();
	
	public Task() {
		setStatus(Status.PLANNING); // initial state
	}
	
	public Task(String name, TaskGroup taskGroup, String description, LocalDateTime startDate,
			LocalDateTime endDate, Status status, Project project) {
		
		this.name = name;
		this.taskGroup = taskGroup;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		setStatus(status);
		this.project = project;
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

	public TaskGroup getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
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

	public Status getStatus() {
		return Status.valueOf(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCode();
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Set<Team> getTeams() {
		return teams;
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
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", taskGroup=" + taskGroup
				+ ", endDate=" + endDate + ", startDate=" + startDate + ", status=" + status + ", project=" + project
				+ ", team=" + teams + "]";
	}

	@Override //compare by startDate
	public int compareTo(Task otherTask) {
		return this.startDate.compareTo(otherTask.startDate);
	}
		
}