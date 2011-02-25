package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import comp.is.model.project.key.WorkpackagePK;



/**
 * The persistent class for the WORKPACKAGE database table.
 * 
 */
@Entity
@Table(name="WORKPACKAGE")
public class WorkpackageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private WorkpackagePK id;
	private String wpactivities;
	private String wpinputs;
	private Date wpissuedate;
	private String wpoutputs;
	private String wppurpose;
	private String wpstatus;
	private String wptitle;
	private List<TimesheetentryEntity> timeSheetEntries;
	private EmployeeEntity wpResponsibleEngineer;
	private ProjectEntity project;
	private WorkpackageEntity wpParent;
	private List<WorkpackageEntity> wpChildren;
	private List<EmployeeEntity> wpEmployeesAssigned;
	private List<WorkpackagestatusreportEntity> wpStatusReports;

    public WorkpackageEntity() {
    }


	@EmbeddedId
	public WorkpackagePK getId() {
		return this.id;
	}

	public void setId(WorkpackagePK id) {
		this.id = id;
	}
	

	@Column(length=256)
	public String getWpactivities() {
		return this.wpactivities;
	}

	public void setWpactivities(String wpactivities) {
		this.wpactivities = wpactivities;
	}


	@Column(length=256)
	public String getWpinputs() {
		return this.wpinputs;
	}

	public void setWpinputs(String wpinputs) {
		this.wpinputs = wpinputs;
	}


    @Temporal( TemporalType.DATE)
	public Date getWpissuedate() {
		return this.wpissuedate;
	}

	public void setWpissuedate(Date wpissuedate) {
		this.wpissuedate = wpissuedate;
	}


	@Column(length=256)
	public String getWpoutputs() {
		return this.wpoutputs;
	}

	public void setWpoutputs(String wpoutputs) {
		this.wpoutputs = wpoutputs;
	}


	@Column(length=256)
	public String getWppurpose() {
		return this.wppurpose;
	}

	public void setWppurpose(String wppurpose) {
		this.wppurpose = wppurpose;
	}


	@Column(length=64)
	public String getWpstatus() {
		return this.wpstatus;
	}

	public void setWpstatus(String wpstatus) {
		this.wpstatus = wpstatus;
	}


	@Column(length=256)
	public String getWptitle() {
		return this.wptitle;
	}

	public void setWptitle(String wptitle) {
		this.wptitle = wptitle;
	}


	//bi-directional many-to-one association to Timesheetentry
	@OneToMany(mappedBy="workPackage")
	public List<TimesheetentryEntity> getTimeSheetEntries() {
		return this.timeSheetEntries;
	}

	public void setTimeSheetEntries(List<TimesheetentryEntity> timeSheetEntries) {
		this.timeSheetEntries = timeSheetEntries;
	}
	

	//uni-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="WPRESPONSIBLEENGINEERID")
	public EmployeeEntity getWpResponsibleEngineer() {
		return this.wpResponsibleEngineer;
	}

	public void setWpResponsibleEngineer(EmployeeEntity wpResponsibleEngineer) {
		this.wpResponsibleEngineer = wpResponsibleEngineer;
	}
	

	//bi-directional many-to-one association to Project
	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID", nullable=false, insertable=false, updatable=false)
	public ProjectEntity getProject() {
		return this.project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	

	//bi-directional many-to-one association to Workpackage
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumns({
		@JoinColumn(name="PROJID", referencedColumnName="PROJID", nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="WPPARENTID", referencedColumnName="WPID", nullable=true, insertable=false, updatable=false)
		})
	public WorkpackageEntity getWpParent() {
		return this.wpParent;
	}
	
	public void setWpParent(WorkpackageEntity wpParent) {
		this.wpParent = wpParent;
	}
	
	//bi-directional many-to-one association to Workpackage
	@OneToMany(mappedBy="wpParent", cascade={CascadeType.ALL})
	public List<WorkpackageEntity> getWpChildren() {
		return this.wpChildren;
	}

	public void setWpChildren(List<WorkpackageEntity> wpChildren) {
		this.wpChildren = wpChildren;
	}
	

	//bi-directional many-to-many association to Employee
	@ManyToMany
	@JoinTable(
		name="WORKPACKAGEEMPLOYEESASSIGNED"
		, joinColumns={
			@JoinColumn(name="PROJID", referencedColumnName="PROJID", nullable=false),
			@JoinColumn(name="WPID", referencedColumnName="WPID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="EMPID", nullable=false)
			}
		)
	public List<EmployeeEntity> getWpEmployeesAssigned() {
		return this.wpEmployeesAssigned;
	}

	public void setWpEmployeesAssigned(List<EmployeeEntity> wpEmployeesAssigned) {
		this.wpEmployeesAssigned = wpEmployeesAssigned;
	}
	

	//bi-directional many-to-many association to Workpackagestatusreport
	@ManyToMany(mappedBy="workPackages")
	public List<WorkpackagestatusreportEntity> getWpStatusReports() {
		return this.wpStatusReports;
	}

	public void setWpStatusReports(List<WorkpackagestatusreportEntity> wpStatusReports) {
		this.wpStatusReports = wpStatusReports;
	}


    public void init(WorkpackageEntity wp) {
        id = wp.id;;
        wpactivities = wp.wpactivities;
        wpinputs = wp.wpinputs;
        wpissuedate = wp.wpissuedate;
        wpoutputs = wp.wpoutputs;
        wppurpose = wp.wppurpose;
        wpstatus = wp.wpstatus;
        wptitle = wp.wptitle;
        timeSheetEntries = wp.timeSheetEntries;
        wpResponsibleEngineer = wp.wpResponsibleEngineer;
        project = wp.project;
        wpParent = wp.wpParent;
        wpChildren = wp.wpChildren;
        wpEmployeesAssigned = wp.wpEmployeesAssigned;
        wpStatusReports = wp.wpStatusReports;
        
    }
	 
	
}