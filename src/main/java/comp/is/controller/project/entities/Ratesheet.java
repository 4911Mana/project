package comp.is.controller.project.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RATESHEET database table.
 * 
 */
@Entity
@Table(name="RATESHEET")
public class Ratesheet implements Serializable {
	private static final long serialVersionUID = 1L;
	private long ratesheetid;
	private Employee projectManager;
	private Project project;

    public Ratesheet() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getRatesheetid() {
		return this.ratesheetid;
	}

	public void setRatesheetid(long ratesheetid) {
		this.ratesheetid = ratesheetid;
	}


	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="PROJMANAGERID")
	public Employee getProjectManager() {
		return this.projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}
	

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}