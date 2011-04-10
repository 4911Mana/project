package comp.is.model.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the WORKPACKAGESTATUSREPORT database table.
 * 
 */
@Entity
@Table(name="WORKPACKAGESTATUSREPORT")
public class WorkpackagestatusreportEntity implements Serializable {
   
	private static final long serialVersionUID = 1L;
//	private EmployeeEntity projectAssistant;
//	private EmployeeEntity projectManager;
	private Set<WorkpackageEntity> workPackages;
	private long id;
	private EmployeeEntity responsibleEngineer;
//	private String wpsraccompthisperiod;
//	private String wpsrcomments;
//	private Date wpsrdate;
//	private String wpsrdelactualcompletion;
//	private String wpsrdeldesc;
//	private BigDecimal wpsrdeldocnum;
//	private String wpsrdelplannedcompletion;
//	private String wpsrmileactualcompletion;
//	private String wpsrmileearnedvalue;
//	private String wpsrmileplannedcompletion;
//	private String wpsrmileprojectedcompletion;
//	private String wpsrproblemsanticcomm;
//	private String wpsrproblemsthisperiodcomm;
	private Date wpsrreportingperiod;
//	private String wpsrworkplannednextperiodcomm;
	private Set<RespEngReportBudgetEntryEntity> entries;
    public WorkpackagestatusreportEntity() {
    }


//	//uni-directional many-to-one association to Employee
//    @ManyToOne
//	@JoinColumn(name="PROJASSISTANTID")
//	public EmployeeEntity getProjectAssistant() {
//		return this.projectAssistant;
//	}
//
//	//uni-directional many-to-one association to Employee
//    @ManyToOne
//	@JoinColumn(name="PROJMANAGERID")
//	public EmployeeEntity getProjectManager() {
//		return this.projectManager;
//	}


	//bi-directional many-to-many association to Workpackage
	@ManyToMany
	@JoinTable(
		name="WORKPACKAGESTATUSREPORTS"
		, joinColumns={
			@JoinColumn(name="REPORTID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="PROJID", referencedColumnName="PROJID", nullable=false),
			@JoinColumn(name="WPID", referencedColumnName="ID", nullable=false)
			}
		)
	public Set<WorkpackageEntity> getWorkPackages() {
		return this.workPackages;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	public long getId() {
		return this.id;
	}


	//uni-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="ENGINEERID")
	public EmployeeEntity getEngineer() {
		return this.responsibleEngineer;
	}

//	@Column(length=2048)
//	public String getWpsraccompthisperiod() {
//		return this.wpsraccompthisperiod;
//	}
//
//
//    @Column(length=2048)
//	public String getWpsrcomments() {
//		return this.wpsrcomments;
//	}
//
//	@Temporal( TemporalType.DATE)
//	public Date getREPORTINGPERIOD() {
//		return this.wpsrdate;
//	}

//
//	@Column(length=2048)
//	public String getWpsrdelactualcompletion() {
//		return this.wpsrdelactualcompletion;
//	}
//
//	@Column(length=1024)
//	public String getWpsrdeldesc() {
//		return this.wpsrdeldesc;
//	}
//
//
//	@Column(precision=10)
//	public BigDecimal getWpsrdeldocnum() {
//		return this.wpsrdeldocnum;
//	}
//
//	@Column(length=2048)
//	public String getWpsrdelplannedcompletion() {
//		return this.wpsrdelplannedcompletion;
//	}
//
//
//	@Column(length=2048)
//	public String getWpsrmileactualcompletion() {
//		return this.wpsrmileactualcompletion;
//	}
//
//	@Column(length=2048)
//	public String getWpsrmileearnedvalue() {
//		return this.wpsrmileearnedvalue;
//	}
//
//
//	@Column(length=2048)
//	public String getWpsrmileplannedcompletion() {
//		return this.wpsrmileplannedcompletion;
//	}
//
//	@Column(length=2048)
//	public String getWpsrmileprojectedcompletion() {
//		return this.wpsrmileprojectedcompletion;
//	}
//
//
//	@Column(length=2048)
//	public String getWpsrproblemsanticcomm() {
//		return this.wpsrproblemsanticcomm;
//	}
//
//	@Column(length=2048)
//	public String getWpsrproblemsthisperiodcomm() {
//		return this.wpsrproblemsthisperiodcomm;
//	}


	@Temporal( TemporalType.DATE)
	public Date getReportingPeriod() {
		return this.wpsrreportingperiod;
	}

//	@Column(length=2048)
//	public String getWpsrworkplannednextperiodcomm() {
//		return this.wpsrworkplannednextperiodcomm;
//	}
//
//
//	public void setProjectAssistant(EmployeeEntity projectAssistant) {
//		this.projectAssistant = projectAssistant;
//	}
//
//	public void setProjectManager(EmployeeEntity projectManager) {
//		this.projectManager = projectManager;
//	}


	public void setWorkPackages(Set<WorkpackageEntity> workPackages) {
		this.workPackages = workPackages;
	}

	public void setId(long workpackagestatusreportid) {
		id = workpackagestatusreportid;
	}


	public void setEngineer(EmployeeEntity wpResponsibleEngineer) {
		this.responsibleEngineer = wpResponsibleEngineer;
	}

//	public void setWpsraccompthisperiod(String wpsraccompthisperiod) {
//		this.wpsraccompthisperiod = wpsraccompthisperiod;
//	}
//
//
//	public void setWpsrcomments(String wpsrcomments) {
//		this.wpsrcomments = wpsrcomments;
//	}
//
//	public void setWpsrdate(Date wpsrdate) {
//		this.wpsrdate = wpsrdate;
//	}
//
//
//    public void setWpsrdelactualcompletion(String wpsrdelactualcompletion) {
//		this.wpsrdelactualcompletion = wpsrdelactualcompletion;
//	}
//
//	public void setWpsrdeldesc(String wpsrdeldesc) {
//		this.wpsrdeldesc = wpsrdeldesc;
//	}
//
//
//	public void setWpsrdeldocnum(BigDecimal wpsrdeldocnum) {
//		this.wpsrdeldocnum = wpsrdeldocnum;
//	}
//
//	public void setWpsrdelplannedcompletion(String wpsrdelplannedcompletion) {
//		this.wpsrdelplannedcompletion = wpsrdelplannedcompletion;
//	}
//
//
//	public void setWpsrmileactualcompletion(String wpsrmileactualcompletion) {
//		this.wpsrmileactualcompletion = wpsrmileactualcompletion;
//	}
//
//	public void setWpsrmileearnedvalue(String wpsrmileearnedvalue) {
//		this.wpsrmileearnedvalue = wpsrmileearnedvalue;
//	}
//	
//
//	public void setWpsrmileplannedcompletion(String wpsrmileplannedcompletion) {
//		this.wpsrmileplannedcompletion = wpsrmileplannedcompletion;
//	}
//
//	public void setWpsrmileprojectedcompletion(String wpsrmileprojectedcompletion) {
//		this.wpsrmileprojectedcompletion = wpsrmileprojectedcompletion;
//	}
//	
//
//	public void setWpsrproblemsanticcomm(String wpsrproblemsanticcomm) {
//		this.wpsrproblemsanticcomm = wpsrproblemsanticcomm;
//	}
//
//	public void setWpsrproblemsthisperiodcomm(String wpsrproblemsthisperiodcomm) {
//		this.wpsrproblemsthisperiodcomm = wpsrproblemsthisperiodcomm;
//	}
//	

	public void setReportingPeriod(Date wpsrreportingperiod) {
		this.wpsrreportingperiod = wpsrreportingperiod;
	}

	@OneToMany
    public Set<RespEngReportBudgetEntryEntity> getEntries() {
        return entries;
    }


    public void setEntries(Set<RespEngReportBudgetEntryEntity> entries) {
        this.entries = entries;
    }

//	public void setWpsrworkplannednextperiodcomm(String wpsrworkplannednextperiodcomm) {
//		this.wpsrworkplannednextperiodcomm = wpsrworkplannednextperiodcomm;
//	}
	
}