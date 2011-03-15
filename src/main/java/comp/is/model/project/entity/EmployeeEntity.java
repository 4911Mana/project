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


/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private double empaccumflextime;
	private double empaccumvacation;
	private String empfirstname;
	private int empid;
	private String emplastname;
	private List<EmployeelabourchargerateEntity> employeeLabourChargeRates;
	private List<EmployeeroleEntity> employeeRoles;
	private List<EmployeeEntity> employeeSupervisors;
	private List<WorkpackageEntity> employeesWorkPackages;
	private double emppercentfulltime;
	//private List<ProjectEntity> projects;
	private List<ProjectsummaryEntity> projectSummaries;
	private List<RatesheetEntity> rateSheets;
	private List<EmployeeEntity> timeSheetApprovers;
	private List<TimesheetEntity> timeSheets;

    public EmployeeEntity() {
    }


	@Column(precision=126)
	public double getEmpaccumflextime() {
		return this.empaccumflextime;
	}

	@Column(precision=126)
	public double getEmpaccumvacation() {
		return this.empaccumvacation;
	}


	@Column(length=64)
	public String getEmpfirstname() {
		return this.empfirstname;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public int getEmpid() {
		return this.empid;
	}


	@Column(length=64)
	public String getEmplastname() {
		return this.emplastname;
	}

	//bi-directional many-to-one association to Employeelabourchargerate
	@OneToMany(mappedBy="employee", cascade={CascadeType.ALL})
	public List<EmployeelabourchargerateEntity> getEmployeeLabourChargeRates() {
		return this.employeeLabourChargeRates;
	}


	//bi-directional many-to-one association to Employeerole
	@OneToMany(mappedBy="employee")
	public List<EmployeeroleEntity> getEmployeeRoles() {
		return this.employeeRoles;
	}

	//uni-directional many-to-many association to Employee
	@ManyToMany
	@JoinTable(
		name="EMPLOYEESUPERVISOR"
		, joinColumns={
			@JoinColumn(name="EMPID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="SUPERVISORID", nullable=false)
			}
		)
	public List<EmployeeEntity> getEmployeeSupervisors() {
		return this.employeeSupervisors;
	}


	//bi-directional many-to-many association to Workpackage
	@ManyToMany(mappedBy="wpEmployeesAssigned")
	public List<WorkpackageEntity> getEmployeesWorkPackages() {
		return this.employeesWorkPackages;
	}

	@Column(precision=126)
	public double getEmppercentfulltime() {
		return this.emppercentfulltime;
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
			@JoinColumn(name="EMPID", nullable=false)
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
	

	public void setEmpaccumflextime(double empaccumflextime) {
		this.empaccumflextime = empaccumflextime;
	}

	public void setEmpaccumvacation(double empaccumvacation) {
		this.empaccumvacation = empaccumvacation;
	}
	

	public void setEmpfirstname(String empfirstname) {
		this.empfirstname = empfirstname;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
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

	public void setEmplastname(String emplastname) {
		this.emplastname = emplastname;
	}

	public void setEmployeeLabourChargeRates(List<EmployeelabourchargerateEntity> employeeLabourChargeRates) {
		this.employeeLabourChargeRates = employeeLabourChargeRates;
	}
	

	public void setEmployeeRoles(List<EmployeeroleEntity> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}

	public void setEmployeeSupervisors(List<EmployeeEntity> employeeSupervisors) {
		this.employeeSupervisors = employeeSupervisors;
	}	

	public void setEmployeesWorkPackages(List<WorkpackageEntity> employeesWorkPackages) {
		this.employeesWorkPackages = employeesWorkPackages;
	}

	public void setEmppercentfulltime(double emppercentfulltime) {
		this.emppercentfulltime = emppercentfulltime;
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