package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import comp.is.model.project.ProjectPackage;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.key.WorkpackagePK;
import comp.is.model.project.entity.Package;

/**
 * The persistent class for the WORKPACKAGE database table.
 * 
 */
@Entity
@IdClass(WorkpackagePK.class)
@Table(name = "WORKPACKAGE")
// @AttributeOverride(name="id", column=@Column(unique=true, nullable=false,
// length=16))
public class WorkpackageEntity extends Package {
    // private List<TimesheetentryEntity> timeSheetEntries;
    protected List<EmployeeEntity> employeesAssigned;
    // private EmployeeEntity responsibleEngineerId;
    protected WorkpackageEntity parent;
    // private List<WorkpackagestatusreportEntity> statusReports;
    protected ProjectEntity project;
    protected String projid;

    public WorkpackageEntity(){}
    public WorkpackageEntity(WorkPackage candidate) {
        init(candidate);
        setParent(candidate.getParent());
        //setProject(candidate.getProject());
    }

    // bi-directional many-to-one association to Workpackage
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PROJID", referencedColumnName = "PROJID", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "PARENTID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false) })
    public WorkpackageEntity getParent() {
        return parent;
    }

    // // bi-directional many-to-many association to Workpackagestatusreport
    // @ManyToMany(mappedBy = "workPackages")
    // public List<WorkpackagestatusreportEntity> getStatusReports() {
    // return statusReports;
    // }
    //
    // public void setStatusReports(
    // List<WorkpackagestatusreportEntity> wpStatusReports) {
    // statusReports = wpStatusReports;
    // }
    //
    
    @ManyToOne
    @JoinColumn(name = "PROJID", nullable = false, insertable = false, updatable = false)
    public ProjectEntity getProject() {
        return this.project;
    }

    @Id
    @Column(nullable = false)
    public String getProjid() {
        return projid;
    }
    
    @Column(name="PARENTID", nullable = false, updatable = false)
    public String getParentId() {
        return parent.getId();
    }
    
    public void setParentId(String id) {
        if(parent != null){
        parent.setId(id);}
    }

    @Transient
    public String getTitle() {
        return getName();
    }

    // // bi-directional many-to-one association to Timesheetentry
    // @OneToMany(mappedBy = "workPackage")
    // public List<TimesheetentryEntity> getTimeSheetEntries() {
    // return this.timeSheetEntries;
    // }
    //
    // public void setTimeSheetEntries(List<TimesheetentryEntity>
    // timeSheetEntries) {
    // this.timeSheetEntries = timeSheetEntries;
    // }
    //
    // // uni-directional many-to-one association to Employee
    // @ManyToOne
    // @JoinColumn(name = "WPRESPONSIBLEENGINEERID")
    // public EmployeeEntity getResponsibleEngineer() {
    // return responsibleEngineerId;
    // }
    //
    // public void setResponsibleEngineer(EmployeeEntity wpResponsibleEngineer)
    // {
    // responsibleEngineerId = wpResponsibleEngineer;
    // }

    // bi-directional many-to-many association to Employee
    @ManyToMany
    @JoinTable(name = "WORKPACKAGEEMPLOYEESASSIGNED", joinColumns = {
            @JoinColumn(name = "PROJID", referencedColumnName = "PROJID", nullable = false),
            @JoinColumn(name = "WPID", referencedColumnName = "ID", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "EMPID", nullable = false) })
    public List<EmployeeEntity> getWpEmployeesAssigned() {
        return employeesAssigned;
    }

    public void setParent(WorkpackageEntity wpParent) {
        parent = wpParent;
        if(wpParent != null){
        projid = wpParent.getProjid();
        }
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
        this.projid = project.getId();
    }

    public void setProjid(String projid) {
        this.projid = projid;
    }

    public void setTitle(String wptitle) {
        setName(wptitle);
    }

    public void setWpEmployeesAssigned(List<EmployeeEntity> wpEmployeesAssigned) {
        employeesAssigned = wpEmployeesAssigned;
    }

    public String toString() {
        return getId();
    }

}