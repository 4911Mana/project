package comp.is.controller.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PROJECTSUMMARY database table.
 * 
 */
@Entity
@Table(name="PROJECTSUMMARY")
public class Projectsummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private long projsumreportid;
	private String projsumanticprognextprdcomm;
	private String projsumapproval;
	private Date projsummonthending;
	private String projsumprobencounteredcomm;
	private String projsumprogdurperiodcomm;
	private Date projsumreportdate;
	private Employee projectManager;
	private Project project;

    public Projectsummary() {
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