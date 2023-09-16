package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	@NotNull(message = "is required")
	private Client client;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade=CascadeType.ALL)
	private List<Task> tasks = new ArrayList<>();
		
	public Project() {
		setStatus(Status.IN_PROGRESS); // initial state
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	//use this method to set attribute status of project
	// count # number of tasks
	// IF (TOTAL # number of tasks status == CANCELED(5))
	//  	=> project.Status = CANCELED
	// ELSE IF (any task status of total # tasks == PLANNING(1) || IN_PROGRESS(2) || ON_HOLD(3))  
	//     => project.Status = IN_PROGRESS 
	// ELSE
	//     => project.Status = COMPLETED
	private void checkTasksAndUpdateProjectStatus() {
		List<Status> allTasksStatus = new ArrayList<>();
		
		for(Task t: tasks) {
			allTasksStatus.add(t.getStatus());
		}
		
		int cancelled = 0;
		int completed = 0;
		int other = 0;
		int all = allTasksStatus.size();
		
		for (Status s: allTasksStatus) {
			if(s.equals(Status.CANCELED)) {
				cancelled ++;
			}
			else if (s.equals(Status.COMPLETED)) {
				completed ++;
			}
			else {
				other ++;
			}
		}
		
		if(all == cancelled) {
			this.setStatus(Status.CANCELED);
		}
		else if((other == 0) && (all == completed + cancelled)) {
			this.setStatus(Status.COMPLETED);
		}
		else {
			this.setStatus(Status.IN_PROGRESS);
		}			
		
		System.out.print("########## AQUI: " + this.getStatus());
		//TODO SAVE TO BD!!?!?
		
	}
		
	public List<Task> getTasks() {
		
		checkTasksAndUpdateProjectStatus();
		// Sort the tasks list by Task.startDate
        Collections.sort(tasks, Comparator.comparing(Task::getStartDate));
        
        // Return the sorted list		
		return tasks;
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