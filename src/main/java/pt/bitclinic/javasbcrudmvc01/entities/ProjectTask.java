package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import pt.bitclinic.javasbcrudmvc01.entities.enums.Status;

@Entity
@Table(name = "tb_project_task")
public class ProjectTask implements Serializable {

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
	private LocalDateTime endDate;

	@NotNull(message = "is required")
	private LocalDateTime startDate;

	@NotNull(message = "is required")
	private Integer status;
	
	public ProjectTask() {
		setStatus(Status.PLANNING); // initial state
	}

	public ProjectTask(String name, TaskGroup taskGroup, String description, LocalDateTime startDate,
			LocalDateTime endDate, Status status) {
		
		this.name = name;
		this.taskGroup = taskGroup;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
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
		ProjectTask other = (ProjectTask) obj;
		return Objects.equals(id, other.id);
	}

}