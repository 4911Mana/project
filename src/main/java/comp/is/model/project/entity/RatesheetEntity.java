package comp.is.model.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the RATESHEET database table.
 * 
 */
@Entity
@Table(name="RATESHEET")
public class RatesheetEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProjectEntity project;
	private EmployeeEntity projectManager;
	private long ratesheetid;

    public RatesheetEntity() {
    }


	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID")
	public ProjectEntity getProject() {
		return this.project;
	}

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="PROJMANAGERID")
	public EmployeeEntity getProjectManager() {
		return this.projectManager;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getRatesheetid() {
		return this.ratesheetid;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	

	public void setProjectManager(EmployeeEntity projectManager) {
		this.projectManager = projectManager;
	}

	public void setRatesheetid(long ratesheetid) {
		this.ratesheetid = ratesheetid;
	}
	
}