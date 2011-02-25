package comp.is.model.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	private long workpackagestatusreportid;
	private String wpsraccompthisperiod;
	private String wpsrcomments;
	private Date wpsrdate;
	private String wpsrdelactualcompletion;
	private String wpsrdeldesc;
	private BigDecimal wpsrdeldocnum;
	private String wpsrdelplannedcompletion;
	private String wpsrmileactualcompletion;
	private String wpsrmileearnedvalue;
	private String wpsrmileplannedcompletion;
	private String wpsrmileprojectedcompletion;
	private String wpsrproblemsanticcomm;
	private String wpsrproblemsthisperiodcomm;
	private Date wpsrreportingperiod;
	private String wpsrworkplannednextperiodcomm;
	private EmployeeEntity projectAssistant;
	private EmployeeEntity wpResponsibleEngineer;
	private EmployeeEntity projectManager;
	private List<WorkpackageEntity> workPackages;

    public WorkpackagestatusreportEntity() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	public long getWorkpackagestatusreportid() {
		return this.workpackagestatusreportid;
	}

	public void setWorkpackagestatusreportid(long workpackagestatusreportid) {
		this.workpackagestatusreportid = workpackagestatusreportid;
	}


	@Column(length=2048)
	public String getWpsraccompthisperiod() {
		return this.wpsraccompthisperiod;
	}

	public void setWpsraccompthisperiod(String wpsraccompthisperiod) {
		this.wpsraccompthisperiod = wpsraccompthisperiod;
	}


	@Column(length=2048)
	public String getWpsrcomments() {
		return this.wpsrcomments;
	}

	public void setWpsrcomments(String wpsrcomments) {
		this.wpsrcomments = wpsrcomments;
	}


    @Temporal( TemporalType.DATE)
	public Date getWpsrdate() {
		return this.wpsrdate;
	}

	public void setWpsrdate(Date wpsrdate) {
		this.wpsrdate = wpsrdate;
	}


	@Column(length=2048)
	public String getWpsrdelactualcompletion() {
		return this.wpsrdelactualcompletion;
	}

	public void setWpsrdelactualcompletion(String wpsrdelactualcompletion) {
		this.wpsrdelactualcompletion = wpsrdelactualcompletion;
	}


	@Column(length=1024)
	public String getWpsrdeldesc() {
		return this.wpsrdeldesc;
	}

	public void setWpsrdeldesc(String wpsrdeldesc) {
		this.wpsrdeldesc = wpsrdeldesc;
	}


	@Column(precision=10)
	public BigDecimal getWpsrdeldocnum() {
		return this.wpsrdeldocnum;
	}

	public void setWpsrdeldocnum(BigDecimal wpsrdeldocnum) {
		this.wpsrdeldocnum = wpsrdeldocnum;
	}


	@Column(length=2048)
	public String getWpsrdelplannedcompletion() {
		return this.wpsrdelplannedcompletion;
	}

	public void setWpsrdelplannedcompletion(String wpsrdelplannedcompletion) {
		this.wpsrdelplannedcompletion = wpsrdelplannedcompletion;
	}


	@Column(length=2048)
	public String getWpsrmileactualcompletion() {
		return this.wpsrmileactualcompletion;
	}

	public void setWpsrmileactualcompletion(String wpsrmileactualcompletion) {
		this.wpsrmileactualcompletion = wpsrmileactualcompletion;
	}


	@Column(length=2048)
	public String getWpsrmileearnedvalue() {
		return this.wpsrmileearnedvalue;
	}

	public void setWpsrmileearnedvalue(String wpsrmileearnedvalue) {
		this.wpsrmileearnedvalue = wpsrmileearnedvalue;
	}


	@Column(length=2048)
	public String getWpsrmileplannedcompletion() {
		return this.wpsrmileplannedcompletion;
	}

	public void setWpsrmileplannedcompletion(String wpsrmileplannedcompletion) {
		this.wpsrmileplannedcompletion = wpsrmileplannedcompletion;
	}


	@Column(length=2048)
	public String getWpsrmileprojectedcompletion() {
		return this.wpsrmileprojectedcompletion;
	}

	public void setWpsrmileprojectedcompletion(String wpsrmileprojectedcompletion) {
		this.wpsrmileprojectedcompletion = wpsrmileprojectedcompletion;
	}


	@Column(length=2048)
	public String getWpsrproblemsanticcomm() {
		return this.wpsrproblemsanticcomm;
	}

	public void setWpsrproblemsanticcomm(String wpsrproblemsanticcomm) {
		this.wpsrproblemsanticcomm = wpsrproblemsanticcomm;
	}


	@Column(length=2048)
	public String getWpsrproblemsthisperiodcomm() {
		return this.wpsrproblemsthisperiodcomm;
	}

	public void setWpsrproblemsthisperiodcomm(String wpsrproblemsthisperiodcomm) {
		this.wpsrproblemsthisperiodcomm = wpsrproblemsthisperiodcomm;
	}


    @Temporal( TemporalType.DATE)
	public Date getWpsrreportingperiod() {
		return this.wpsrreportingperiod;
	}

	public void setWpsrreportingperiod(Date wpsrreportingperiod) {
		this.wpsrreportingperiod = wpsrreportingperiod;
	}


	@Column(length=2048)
	public String getWpsrworkplannednextperiodcomm() {
		return this.wpsrworkplannednextperiodcomm;
	}

	public void setWpsrworkplannednextperiodcomm(String wpsrworkplannednextperiodcomm) {
		this.wpsrworkplannednextperiodcomm = wpsrworkplannednextperiodcomm;
	}


	//uni-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="PROJASSISTANTID")
	public EmployeeEntity getProjectAssistant() {
		return this.projectAssistant;
	}

	public void setProjectAssistant(EmployeeEntity projectAssistant) {
		this.projectAssistant = projectAssistant;
	}
	

	//uni-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="ENGINEERID")
	public EmployeeEntity getWpResponsibleEngineer() {
		return this.wpResponsibleEngineer;
	}

	public void setWpResponsibleEngineer(EmployeeEntity wpResponsibleEngineer) {
		this.wpResponsibleEngineer = wpResponsibleEngineer;
	}
	

	//uni-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="PROJMANAGERID")
	public EmployeeEntity getProjectManager() {
		return this.projectManager;
	}

	public void setProjectManager(EmployeeEntity projectManager) {
		this.projectManager = projectManager;
	}
	

	//bi-directional many-to-many association to Workpackage
	@ManyToMany
	@JoinTable(
		name="WORKPACKAGESTATUSREPORTS"
		, joinColumns={
			@JoinColumn(name="WORKPACKAGESTATUSREPORTID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="PROJID", referencedColumnName="PROJID", nullable=false),
			@JoinColumn(name="WPID", referencedColumnName="WPID", nullable=false)
			}
		)
	public List<WorkpackageEntity> getWorkPackages() {
		return this.workPackages;
	}

	public void setWorkPackages(List<WorkpackageEntity> workPackages) {
		this.workPackages = workPackages;
	}
	
}