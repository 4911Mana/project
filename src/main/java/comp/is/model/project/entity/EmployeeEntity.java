package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import comp.is.model.admin.Employee;


/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	protected double accumflextime;
	protected double accumvacation;
	protected String firstname;
	protected int id;
	protected String lastname;
	protected List<EmployeelabourchargerateEntity> labourChargeRates;
	protected List<EmployeeroleEntity> roles;
	protected List<EmployeeEntity> supervisors;
	protected List<WorkpackageEntity> workPackages;
	protected double percentfulltime;
	//protected List<ProjectEntity> projects;
	protected List<ProjectsummaryEntity> projectSummaries;
	protected List<RatesheetEntity> rateSheets;
	protected List<EmployeeEntity> timeSheetApprovers;
	protected List<TimesheetEntity> timeSheets;

    public EmployeeEntity() {
    }


	public EmployeeEntity(Employee e) {
	    if(e == null){return;}
	    firstname = e.firstname;
	    id = e.id;
	    lastname = e.lastname;
    }


    @Column(precision=126)
	public double getAccumflextime() {
		return this.accumflextime;
	}

	@Column(precision=126)
	public double getAccumvacation() {
		return this.accumvacation;
	}


	@Column(length=64)
	public String getFirstname() {
		return this.firstname;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public int getId() {
		return this.id;
	}


	@Column(length=64)
	public String getLastname() {
		return this.lastname;
	}

	//bi-directional many-to-one association to Employeelabourchargerate
	@OneToMany(mappedBy="employee", cascade={CascadeType.ALL})
	public List<EmployeelabourchargerateEntity> getLabourChargeRates() {
		return this.labourChargeRates;
	}


	//bi-directional many-to-one association to Employeerole
	@OneToMany(mappedBy="employee")
	public List<EmployeeroleEntity> getRoles() {
		return this.roles;
	}

	//uni-directional many-to-many association to Employee
	@ManyToMany
	@JoinTable(
		name="EMPLOYEESUPERVISOR"
		, joinColumns={
			@JoinColumn(name="ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="SUPERVISORID", nullable=false)
			}
		)
	public List<EmployeeEntity> getSupervisors() {
		return this.supervisors;
	}


	//bi-directional many-to-many association to Workpackage
	@ManyToMany(mappedBy="employeesAssigned")
	public List<WorkpackageEntity> getWorkPackages() {
		return this.workPackages;
	}

	@Column(precision=126)
	public double getPercentfulltime() {
		return this.percentfulltime;
	}


	//bi-directional many-to-one association to Projectsummary
	@OneToMany(mappedBy="projectManager")
	public List<ProjectsummaryEntity> getProjectSummaries() {
		return this.projectSummaries;
	}

	//bi-directional many-to-one association to Ratesheet
	@OneToMany(mappedBy="projectManager")
	public List<RatesheetEntity> getRateSheets() {
		return this.rateSheets;
	}


	//uni-directional many-to-many association to Employee
	@ManyToMany
	@JoinTable(
		name="TIMESHEETAPPROVER"
		, joinColumns={
			@JoinColumn(name="ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="TSAPPROVERID", nullable=false)
			}
		)
	public List<EmployeeEntity> getTimeSheetApprovers() {
		return this.timeSheetApprovers;
	}

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="employee")
	public List<TimesheetEntity> getTimeSheets() {
		return this.timeSheets;
	}
	

	public void setAccumflextime(double empaccumflextime) {
		this.accumflextime = empaccumflextime;
	}

	public void setAccumvacation(double empaccumvacation) {
		this.accumvacation = empaccumvacation;
	}
	

	public void setFirstname(String empfirstname) {
		this.firstname = empfirstname;
	}

	public void setId(int empid) {
		this.id = empid;
	}
	

//	//bi-directional many-to-many association to Project
//	@ManyToMany(mappedBy="projectEmployees")
//	public List<ProjectEntity> getProjects() {
//		return this.projects;
//	}
//
//	public void setProjects(List<ProjectEntity> projects) {
//		this.projects = projects;
//	}
//	

	public void setLastname(String emplastname) {
		this.lastname = emplastname;
	}

	public void setLabourChargeRates(List<EmployeelabourchargerateEntity> employeeLabourChargeRates) {
		this.labourChargeRates = employeeLabourChargeRates;
	}
	

	public void setRoles(List<EmployeeroleEntity> employeeRoles) {
		this.roles = employeeRoles;
	}

	public void setSupervisors(List<EmployeeEntity> employeeSupervisors) {
		this.supervisors = employeeSupervisors;
	}	

	public void setWorkPackages(List<WorkpackageEntity> employeesWorkPackages) {
		this.workPackages = employeesWorkPackages;
	}

	public void setPercentfulltime(double emppercentfulltime) {
		this.percentfulltime = emppercentfulltime;
	}
	

	public void setProjectSummaries(List<ProjectsummaryEntity> projectSummaries) {
		this.projectSummaries = projectSummaries;
	}

	public void setRateSheets(List<RatesheetEntity> rateSheets) {
		this.rateSheets = rateSheets;
	}
	

	public void setTimeSheetApprovers(List<EmployeeEntity> timeSheetApprovers) {
		this.timeSheetApprovers = timeSheetApprovers;
	}

	public void setTimeSheets(List<TimesheetEntity> timeSheets) {
		this.timeSheets = timeSheets;
	}
	
}