package comp.is.controller.project.entities;

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

import comp.is.controller.project.entities.keys.WorkpackagePK;


/**
 * The persistent class for the WORKPACKAGE database table.
 * 
 */
@Entity
@Table(name="WORKPACKAGE")
public class Workpackage implements Serializable {
	private static final long serialVersionUID = 1L;
	private WorkpackagePK id;
	private String wpactivities;
	private String wpinputs;
	private Date wpissuedate;
	private String wpoutputs;
	private String wppurpose;
	private String wpstatus;
	private String wptitle;
	private List<Timesheetentry> timeSheetEntries;
	private Employee wpResponsibleEngineer;
	private Project project;
	private Workpackage wpParent;
	private List<Workpackage> wpChildren;
	private List<Employee> wpEmployeesAssigned;
	private List<Workpackagestatusreport> wpStatusReports;

    public Workpackage() {
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
	public List<Timesheetentry> getTimeSheetEntries() {
		return this.timeSheetEntries;
	}

	public void setTimeSheetEntries(List<Timesheetentry> timeSheetEntries) {
		this.timeSheetEntries = timeSheetEntries;
	}
	

	//uni-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="WPRESPONSIBLEENGINEERID")
	public Employee getWpResponsibleEngineer() {
		return this.wpResponsibleEngineer;
	}

	public void setWpResponsibleEngineer(Employee wpResponsibleEngineer) {
		this.wpResponsibleEngineer = wpResponsibleEngineer;
	}
	

	//bi-directional many-to-one association to Project
	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID", nullable=false, insertable=false, updatable=false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

	//bi-directional many-to-one association to Workpackage
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumns({
		@JoinColumn(name="PROJID", referencedColumnName="PROJID", nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="WPPARENTID", referencedColumnName="WPID", nullable=true, insertable=false, updatable=false)
		})
	public Workpackage getWpParent() {
		return this.wpParent;
	}
	
	public void setWpParent(Workpackage wpParent) {
		this.wpParent = wpParent;
	}
	
	//bi-directional many-to-one association to Workpackage
	@OneToMany(mappedBy="wpParent", cascade={CascadeType.ALL})
	public List<Workpackage> getWpChildren() {
		return this.wpChildren;
	}

	public void setWpChildren(List<Workpackage> wpChildren) {
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
	public List<Employee> getWpEmployeesAssigned() {
		return this.wpEmployeesAssigned;
	}

	public void setWpEmployeesAssigned(List<Employee> wpEmployeesAssigned) {
		this.wpEmployeesAssigned = wpEmployeesAssigned;
	}
	

	//bi-directional many-to-many association to Workpackagestatusreport
	@ManyToMany(mappedBy="workPackages")
	public List<Workpackagestatusreport> getWpStatusReports() {
		return this.wpStatusReports;
	}

	public void setWpStatusReports(List<Workpackagestatusreport> wpStatusReports) {
		this.wpStatusReports = wpStatusReports;
	}
	
}