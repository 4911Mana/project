package comp.is.controller.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the PROJECT database table.
 * 
 */
@Entity
@Table(name="PROJECT")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	private String projid;
	private String projcustomer;
	private double projlabourratemarkup;
	private String projname;
	private List<Employeerole> employeeRoles;
	private Projectbudget projectBudget;
	private List<Employee> projectEmployees;
	private List<Projectsummary> projectSummaries;
	private List<Ratesheet> rateSheets;
	private List<Timesheetentry> timeSheetEntries;
	private List<Workpackage> workPackages;

    public Project() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=16)
	public String getProjid() {
		return this.projid;
	}

	public void setProjid(String projid) {
		this.projid = projid;
	}


	@Column(length=256)
	public String getProjcustomer() {
		return this.projcustomer;
	}

	public void setProjcustomer(String projcustomer) {
		this.projcustomer = projcustomer;
	}


	@Column(precision=126)
	public double getProjlabourratemarkup() {
		return this.projlabourratemarkup;
	}

	public void setProjlabourratemarkup(double projlabourratemarkup) {
		this.projlabourratemarkup = projlabourratemarkup;
	}


	@Column(length=64)
	public String getProjname() {
		return this.projname;
	}

	public void setProjname(String projname) {
		this.projname = projname;
	}


	//bi-directional many-to-one association to Employeerole
	@OneToMany(mappedBy="project")
	public List<Employeerole> getEmployeeRoles() {
		return this.employeeRoles;
	}

	public void setEmployeeRoles(List<Employeerole> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}
	

	//bi-directional one-to-one association to Projectbudget
	@OneToOne(mappedBy="project")
	public Projectbudget getProjectBudget() {
		return this.projectBudget;
	}

	public void setProjectBudget(Projectbudget projectBudget) {
		this.projectBudget = projectBudget;
	}
	

	//bi-directional many-to-many association to Employee
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="PROJECTEMPLOYEE"
		, joinColumns={
			@JoinColumn(name="PROJID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="EMPID", nullable=false)
			}
		)
	public List<Employee> getProjectEmployees() {
		return this.projectEmployees;
	}

	public void setProjectEmployees(List<Employee> projectEmployees) {
		this.projectEmployees = projectEmployees;
	}
	

	//bi-directional many-to-one association to Projectsummary
	@OneToMany(mappedBy="project")
	public List<Projectsummary> getProjectSummaries() {
		return this.projectSummaries;
	}

	public void setProjectSummaries(List<Projectsummary> projectSummaries) {
		this.projectSummaries = projectSummaries;
	}
	

	//bi-directional many-to-one association to Ratesheet
	@OneToMany(mappedBy="project")
	public List<Ratesheet> getRateSheets() {
		return this.rateSheets;
	}

	public void setRateSheets(List<Ratesheet> rateSheets) {
		this.rateSheets = rateSheets;
	}
	

	//bi-directional many-to-one association to Timesheetentry
	@OneToMany(mappedBy="project")
	public List<Timesheetentry> getTimeSheetEntries() {
		return this.timeSheetEntries;
	}

	public void setTimeSheetEntries(List<Timesheetentry> timeSheetEntries) {
		this.timeSheetEntries = timeSheetEntries;
	}
	

	//bi-directional many-to-one association to Workpackage
	@OneToMany(mappedBy="project")
	public List<Workpackage> getWorkPackages() {
    	//for (Workpackage wp : workPackages) {
    	//	System.err.println("getWorkPackages raw values: " + wp.getId().getWpid() + " : " + wp.getId().getProjid() + " : " + wp.getWptitle());
    	//}
		return this.workPackages;
	}

	public void setWorkPackages(List<Workpackage> workPackages) {
		this.workPackages = workPackages;
	}
	
}