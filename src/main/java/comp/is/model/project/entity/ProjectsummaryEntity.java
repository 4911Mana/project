package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PROJECTSUMMARY database table.
 * 
 */
@Entity
@Table(name="PROJECTSUMMARY")
public class ProjectsummaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long projsumreportid;
	private String projsumanticprognextprdcomm;
	private String projsumapproval;
	private Date projsummonthending;
	private String projsumprobencounteredcomm;
	private String projsumprogdurperiodcomm;
	private Date projsumreportdate;
	private EmployeeEntity projectManager;
	private ProjectEntity project;

    public ProjectsummaryEntity() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getProjsumreportid() {
		return this.projsumreportid;
	}

	public void setProjsumreportid(long projsumreportid) {
		this.projsumreportid = projsumreportid;
	}


	@Column(length=1024)
	public String getProjsumanticprognextprdcomm() {
		return this.projsumanticprognextprdcomm;
	}

	public void setProjsumanticprognextprdcomm(String projsumanticprognextprdcomm) {
		this.projsumanticprognextprdcomm = projsumanticprognextprdcomm;
	}


	@Column(length=64)
	public String getProjsumapproval() {
		return this.projsumapproval;
	}

	public void setProjsumapproval(String projsumapproval) {
		this.projsumapproval = projsumapproval;
	}


    @Temporal( TemporalType.DATE)
	public Date getProjsummonthending() {
		return this.projsummonthending;
	}

	public void setProjsummonthending(Date projsummonthending) {
		this.projsummonthending = projsummonthending;
	}


	@Column(length=1024)
	public String getProjsumprobencounteredcomm() {
		return this.projsumprobencounteredcomm;
	}

	public void setProjsumprobencounteredcomm(String projsumprobencounteredcomm) {
		this.projsumprobencounteredcomm = projsumprobencounteredcomm;
	}


	@Column(length=1024)
	public String getProjsumprogdurperiodcomm() {
		return this.projsumprogdurperiodcomm;
	}

	public void setProjsumprogdurperiodcomm(String projsumprogdurperiodcomm) {
		this.projsumprogdurperiodcomm = projsumprogdurperiodcomm;
	}


    @Temporal( TemporalType.DATE)
	public Date getProjsumreportdate() {
		return this.projsumreportdate;
	}

	public void setProjsumreportdate(Date projsumreportdate) {
		this.projsumreportdate = projsumreportdate;
	}


	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="PROJMANAGERID")
	public EmployeeEntity getProjectManager() {
		return this.projectManager;
	}

	public void setProjectManager(EmployeeEntity projectManager) {
		this.projectManager = projectManager;
	}
	

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID")
	public ProjectEntity getProject() {
		return this.project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	
}