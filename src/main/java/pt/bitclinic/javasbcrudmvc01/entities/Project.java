package pt.bitclinic.javasbcrudmvc01.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import pt.bitclinic.javasbcrudmvc01.entities.enums.ProjectStatus;

@Entity
@Table(name = "tb_project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "is required")
	private String name;
	
	private Integer projectStatus;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="project", 
			cascade= {CascadeType.ALL}) 
	private List <ProjectPhase> phases;
	
	public Project() {
	}

	public Project(long id, String name, ProjectStatus projectStatus) {
		this.id = id;
		this.name = name;
		setProjectStatus(projectStatus);
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
	
	public ProjectStatus getProjectStatus() {
		return ProjectStatus.valueOf(projectStatus);
	}
	
	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus.getCode();
	}
	
	public List<ProjectPhase> getPhases() {
		return phases;
	}

	public void setPhases(List<ProjectPhase> phases) {
		this.phases = phases;
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
