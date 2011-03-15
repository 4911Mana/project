package comp.is.model.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the TIMESHEETENTRY database table.
 * 
 */
@Entity
@Table(name="TIMESHEETENTRY")
public class TimesheetentryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProjectEntity project;
	private TimesheetEntity timeSheet;
	private double tsentryfrihours;
	private long tsentryid;
	private double tsentrymonhours;
	private double tsentrysathours;
	private double tsentrysunhours;
	private double tsentrythuhours;
	private double tsentrytuehours;
	private double tsentrywedhours;
	private WorkpackageEntity workPackage;

    public TimesheetentryEntity() {
    }


	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID", insertable=false, updatable=false)
	public ProjectEntity getProject() {
		return this.project;
	}

	//bi-directional many-to-one association to Timesheet
    @ManyToOne
	@JoinColumn(name="TSID")
	public TimesheetEntity getTimeSheet() {
		return this.timeSheet;
	}


	@Column(precision=126)
	public double getTsentryfrihours() {
		return this.tsentryfrihours;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getTsentryid() {
		return this.tsentryid;
	}


	@Column(precision=126)
	public double getTsentrymonhours() {
		return this.tsentrymonhours;
	}

	@Column(precision=126)
	public double getTsentrysathours() {
		return this.tsentrysathours;
	}


	@Column(precision=126)
	public double getTsentrysunhours() {
		return this.tsentrysunhours;
	}

	@Column(precision=126)
	public double getTsentrythuhours() {
		return this.tsentrythuhours;
	}


	@Column(precision=126)
	public double getTsentrytuehours() {
		return this.tsentrytuehours;
	}

	@Column(precision=126)
	public double getTsentrywedhours() {
		return this.tsentrywedhours;
	}


	//bi-directional many-to-one association to Workpackage
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="PROJID", referencedColumnName="PROJID"),
		@JoinColumn(name="WPID", referencedColumnName="ID")
		})
	public WorkpackageEntity getWorkPackage() {
		return this.workPackage;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}


	public void setTimeSheet(TimesheetEntity timeSheet) {
		this.timeSheet = timeSheet;
	}

	public void setTsentryfrihours(double tsentryfrihours) {
		this.tsentryfrihours = tsentryfrihours;
	}


	public void setTsentryid(long tsentryid) {
		this.tsentryid = tsentryid;
	}

	public void setTsentrymonhours(double tsentrymonhours) {
		this.tsentrymonhours = tsentrymonhours;
	}


	public void setTsentrysathours(double tsentrysathours) {
		this.tsentrysathours = tsentrysathours;
	}

	public void setTsentrysunhours(double tsentrysunhours) {
		this.tsentrysunhours = tsentrysunhours;
	}
	

	public void setTsentrythuhours(double tsentrythuhours) {
		this.tsentrythuhours = tsentrythuhours;
	}

	public void setTsentrytuehours(double tsentrytuehours) {
		this.tsentrytuehours = tsentrytuehours;
	}
	

	public void setTsentrywedhours(double tsentrywedhours) {
		this.tsentrywedhours = tsentrywedhours;
	}

	public void setWorkPackage(WorkpackageEntity workPackage) {
		this.workPackage = workPackage;
	}
	
}