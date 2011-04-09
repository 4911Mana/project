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
	private ProjectEntity project;
	private EmployeeEntity projectManager;
//	private String projsumanticprognextprdcomm;
//	private String projsumapproval;
//	private Date projsummonthending;
//	private String projsumprobencounteredcomm;
//	private String projsumprogdurperiodcomm;
	private Date reportdate;
	private long reportid;

    public ProjectsummaryEntity() {
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


//	@Column(length=1024)
//	public String getProjsumanticprognextprdcomm() {
//		return this.projsumanticprognextprdcomm;
//	}
//
//	@Column(length=64)
//	public String getProjsumapproval() {
//		return this.projsumapproval;
//	}
//
//
//	@Temporal( TemporalType.DATE)
//	public Date getProjsummonthending() {
//		return this.projsummonthending;
//	}
//
//	@Column(length=1024)
//	public String getProjsumprobencounteredcomm() {
//		return this.projsumprobencounteredcomm;
//	}
//
//
//    @Column(length=1024)
//	public String getProjsumprogdurperiodcomm() {
//		return this.projsumprogdurperiodcomm;
//	}
//
	@Temporal( TemporalType.DATE)
	public Date getReportdate() {
		return reportdate;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getReportid() {
		return reportid;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}


	public void setProjectManager(EmployeeEntity projectManager) {
		this.projectManager = projectManager;
	}

//	public void setProjsumanticprognextprdcomm(String projsumanticprognextprdcomm) {
//		this.projsumanticprognextprdcomm = projsumanticprognextprdcomm;
//	}
//
//
//    public void setProjsumapproval(String projsumapproval) {
//		this.projsumapproval = projsumapproval;
//	}
//
//	public void setProjsummonthending(Date projsummonthending) {
//		this.projsummonthending = projsummonthending;
//	}
//
//
//	public void setProjsumprobencounteredcomm(String projsumprobencounteredcomm) {
//		this.projsumprobencounteredcomm = projsumprobencounteredcomm;
//	}
//
//	public void setProjsumprogdurperiodcomm(String projsumprogdurperiodcomm) {
//		this.projsumprogdurperiodcomm = projsumprogdurperiodcomm;
//	}
//	

	public void setReportdate(Date projsumreportdate) {
		reportdate = projsumreportdate;
	}

	public void setReportid(long projsumreportid) {
		reportid = projsumreportid;
	}
	
}