package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import pt.bitclinic.javasbcrudmvc01.entities.enums.Status;

@Entity
@Table(name = "tb_project")
public class Project implements Serializable {

	//TODO this is the many-to-many association entity!!!!
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "is required")
	private String name;
	
	@NotNull(message = "is required")
	@Column(columnDefinition = "TEXT") //more than 255 characters 
	private String description;
	
	private Integer status;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "project", 
			cascade= {CascadeType.ALL}) 
	private List <Task> tasks;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	@NotNull(message = "is required")
	private Client client;
	
	public Project() {
		tasks = new ArrayList<Task>();
		setStatus(Status.PLANNING); // initial state
	}

	public Project(long id, String name, Status status, Client client) {
		this.id = id;
		this.name = name;
		setStatus(status);
		this.client = client;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Status getStatus() {
		return Status.valueOf(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCode();
	}

	public List<Task> getTasks() {
		return tasks;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public boolean removeTask(Task task) {
		return tasks.remove(task);
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
		Project other = (Project) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + "]";
	}
	
}
