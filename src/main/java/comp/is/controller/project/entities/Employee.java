package comp.is.controller.project.entities;

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
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private int empid;
	private double empaccumflextime;
	private double empaccumvacation;
	private String empfirstname;
	private String emplastname;
	private double emppercentfulltime;
	private List<Employeelabourchargerate> employeeLabourChargeRates;
	private List<Employeerole> employeeRoles;
	private List<Employee> employeeSupervisors;
	private List<Project> projects;
	private List<Projectsummary> projectSummaries;
	private List<Ratesheet> rateSheets;
	private List<Timesheet> timeSheets;
	private List<Employee> timeSheetApprovers;
	private List<Workpackage> employeesWorkPackages;

    public Employee() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public int getEmpid() {
		return this.empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}


	@Column(precision=126)
	public double getEmpaccumflextime() {
		return this.empaccumflextime;
	}

	public void setEmpaccumflextime(double empaccumflextime) {
		this.empaccumflextime = empaccumflextime;
	}


	@Column(precision=126)
	public double getEmpaccumvacation() {
		return this.empaccumvacation;
	}

	public void setEmpaccumvacation(double empaccumvacation) {
		this.empaccumvacation = empaccumvacation;
	}


	@Column(length=64)
	public String getEmpfirstname() {
		return this.empfirstname;
	}

	public void setEmpfirstname(String empfirstname) {
		this.empfirstname = empfirstname;
	}


	@Column(length=64)
	public String getEmplastname() {
		return this.emplastname;
	}

	public void setEmplastname(String emplastname) {
		this.emplastname = emplastname;
	}


	@Column(precision=126)
	public double getEmppercentfulltime() {
		return this.emppercentfulltime;
	}

	public void setEmppercentfulltime(double emppercentfulltime) {
		this.emppercentfulltime = emppercentfulltime;
	}


	//bi-directional many-to-one association to Employeelabourchargerate
	@OneToMany(mappedBy="employee", cascade={CascadeType.ALL})
	public List<Employeelabourchargerate> getEmployeeLabourChargeRates() {
		return this.employeeLabourChargeRates;
	}

	public void setEmployeeLabourChargeRates(List<Employeelabourchargerate> employeeLabourChargeRates) {
		this.employeeLabourChargeRates = employeeLabourChargeRates;
	}
	

	//bi-directional many-to-one association to Employeerole
	@OneToMany(mappedBy="employee")
	public List<Employeerole> getEmployeeRoles() {
		return this.employeeRoles;
	}

	public void setEmployeeRoles(List<Employeerole> employeeRoles) {
		this.employeeRoles = employeeRoles;
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
	public List<Employee> getEmployeeSupervisors() {
		return this.employeeSupervisors;
	}

	public void setEmployeeSupervisors(List<Employee> employeeSupervisors) {
		this.employeeSupervisors = employeeSupervisors;
	}
	

	//bi-directional many-to-many association to Project
	@ManyToMany(mappedBy="projectEmployees")
	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	

	//bi-directional many-to-one association to Projectsummary
	@OneToMany(mappedBy="projectManager")
	public List<Projectsummary> getProjectSummaries() {
		return this.projectSummaries;
	}

	public void setProjectSummaries(List<Projectsummary> projectSummaries) {
		this.projectSummaries = projectSummaries;
	}
	

	//bi-directional many-to-one association to Ratesheet
	@OneToMany(mappedBy="projectManager")
	public List<Ratesheet> getRateSheets() {
		return this.rateSheets;
	}

	public void setRateSheets(List<Ratesheet> rateSheets) {
		this.rateSheets = rateSheets;
	}	

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="employee")
	public List<Timesheet> getTimeSheets() {
		return this.timeSheets;
	}

	public void setTimeSheets(List<Timesheet> timeSheets) {
		this.timeSheets = timeSheets;
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
	public List<Employee> getTimeSheetApprovers() {
		return this.timeSheetApprovers;
	}

	public void setTimeSheetApprovers(List<Employee> timeSheetApprovers) {
		this.timeSheetApprovers = timeSheetApprovers;
	}
	

	//bi-directional many-to-many association to Workpackage
	@ManyToMany(mappedBy="wpEmployeesAssigned")
	public List<Workpackage> getEmployeesWorkPackages() {
		return this.employeesWorkPackages;
	}

	public void setEmployeesWorkPackages(List<Workpackage> employeesWorkPackages) {
		this.employeesWorkPackages = employeesWorkPackages;
	}
	
}