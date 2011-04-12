package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import comp.is.model.project.ProjectPackage;

/**
 * The persistent class for the PROJECT database table.
 * 
 */
@Entity
@Table(name = "PROJECT")
// @AttributeOverride(name="id", column=@Column(unique=true, nullable=false,
// length=16))
public class ProjectEntity extends Package implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String customer;
    //protected double labourratemarkup;
    protected Set<WorkpackageEntity> workPackages;
    protected Set<EmployeeEntity> projectEmployees;
    protected ProjectbudgetEntity initBudget;
    protected Set<ProjectsummaryEntity> projectSummaries;
    protected Set<TimesheetentryEntity> timeSheetEntries;
    protected Set<EmployeeroleEntity> employeeRoles;
    protected Set<RatesheetEntity> rateSheets;

    public ProjectEntity(){
        workPackages = new HashSet<WorkpackageEntity>();
        projectEmployees = new HashSet<EmployeeEntity>();
        projectSummaries = new HashSet<ProjectsummaryEntity>();
        timeSheetEntries = new HashSet<TimesheetentryEntity>();
        employeeRoles =  new HashSet<EmployeeroleEntity>();
        rateSheets = new HashSet<RatesheetEntity>();
    }
    public ProjectEntity(ProjectPackage pp) {
        init(pp);
        customer = pp.customer;
        workPackages = pp.workPackages;
        projectEmployees = pp.projectEmployees;
        initBudget = pp.initBudget;
        projectSummaries = pp.projectSummaries;
        timeSheetEntries = pp.timeSheetEntries;
        employeeRoles =  pp.employeeRoles;
        rateSheets = pp.rateSheets;
    }

    @Column(length = 256)
    public String getCustomer() {
        return customer;
    }

//    @Column(precision = 126)
//    public double getLabourratemarkup() {
//        return labourratemarkup;
//    }

    // bi-directional many-to-one association to Workpackage
    @OneToMany(mappedBy = "project")
    public Set<WorkpackageEntity> getWorkPackages() {
        return this.workPackages;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

     //bi-directional many-to-one association to Employeerole
     @OneToMany(mappedBy="project", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
     public Set<EmployeeroleEntity> getEmployeeRoles() {
     return this.employeeRoles;
     }
    
     public void setEmployeeRoles(Set<EmployeeroleEntity> employeeRoles) {
     this.employeeRoles = employeeRoles;
     }
    
    
     //bi-directional one-to-one association to Projectbudget
     @OneToOne(mappedBy="project")
     public ProjectbudgetEntity getProjectBudget() {
     return this.initBudget;
     }
    
     public void setProjectBudget(ProjectbudgetEntity projectBudget) {
     this.initBudget = projectBudget;
     }
    
    
     //bi-directional many-to-many association to Employee
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PROJECTEMPLOYEE", 
            joinColumns = { @JoinColumn(name = "PROJID", nullable = false) }, 
            inverseJoinColumns = { @JoinColumn(name = "EMPID", nullable = false) })
    public Set<EmployeeEntity> getProjectEmployees() {
        return this.projectEmployees;
    }

    public void setProjectEmployees(Set<EmployeeEntity> projectEmployees) {
        this.projectEmployees = projectEmployees;
    }

    
    
     //bi-directional many-to-one association to Projectsummary
     @OneToMany(mappedBy="project")
     public Set<ProjectsummaryEntity> getProjectSummaries() {
     return this.projectSummaries;
     }
    
     public void setProjectSummaries(Set<ProjectsummaryEntity>
     projectSummaries) {
     this.projectSummaries = projectSummaries;
     }
    
    
//     //bi-directional many-to-one association to Ratesheet
//     @OneToMany(mappedBy="project")
//     public Set<RatesheetEntity> getRateSheets() {
//     return this.rateSheets;
//     }
//    
//     public void setRateSheets(Set<RatesheetEntity> rateSheets) {
//     this.rateSheets = rateSheets;
//     }
    
    
//     //bi-directional many-to-one association to Timesheetentry
//     @OneToMany(mappedBy="project")
//     public Set<TimesheetentryEntity> getTimeSheetEntries() {
//     return this.timeSheetEntries;
//     }
//    
//     public void setTimeSheetEntries(Set<TimesheetentryEntity>
//     timeSheetEntries) {
//     this.timeSheetEntries = timeSheetEntries;
//     }
    

//    public void setLabourratemarkup(double labourratemarkup) {
//        this.labourratemarkup = labourratemarkup;
//    }

    public void setWorkPackages(Set<WorkpackageEntity> workPackages) {
        this.workPackages = workPackages;
    }
}