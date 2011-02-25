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
	private long tsentryid;
	private double tsentryfrihours;
	private double tsentrymonhours;
	private double tsentrysathours;
	private double tsentrysunhours;
	private double tsentrythuhours;
	private double tsentrytuehours;
	private double tsentrywedhours;
	private ProjectEntity project;
	private TimesheetEntity timeSheet;
	private WorkpackageEntity workPackage;

    public TimesheetentryEntity() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getTsentryid() {
		return this.tsentryid;
	}

	public void setTsentryid(long tsentryid) {
		this.tsentryid = tsentryid;
	}


	@Column(precision=126)
	public double getTsentryfrihours() {
		return this.tsentryfrihours;
	}

	public void setTsentryfrihours(double tsentryfrihours) {
		this.tsentryfrihours = tsentryfrihours;
	}


	@Column(precision=126)
	public double getTsentrymonhours() {
		return this.tsentrymonhours;
	}

	public void setTsentrymonhours(double tsentrymonhours) {
		this.tsentrymonhours = tsentrymonhours;
	}


	@Column(precision=126)
	public double getTsentrysathours() {
		return this.tsentrysathours;
	}

	public void setTsentrysathours(double tsentrysathours) {
		this.tsentrysathours = tsentrysathours;
	}


	@Column(precision=126)
	public double getTsentrysunhours() {
		return this.tsentrysunhours;
	}

	public void setTsentrysunhours(double tsentrysunhours) {
		this.tsentrysunhours = tsentrysunhours;
	}


	@Column(precision=126)
	public double getTsentrythuhours() {
		return this.tsentrythuhours;
	}

	public void setTsentrythuhours(double tsentrythuhours) {
		this.tsentrythuhours = tsentrythuhours;
	}


	@Column(precision=126)
	public double getTsentrytuehours() {
		return this.tsentrytuehours;
	}

	public void setTsentrytuehours(double tsentrytuehours) {
		this.tsentrytuehours = tsentrytuehours;
	}


	@Column(precision=126)
	public double getTsentrywedhours() {
		return this.tsentrywedhours;
	}

	public void setTsentrywedhours(double tsentrywedhours) {
		this.tsentrywedhours = tsentrywedhours;
	}


	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID", insertable=false, updatable=false)
	public ProjectEntity getProject() {
		return this.project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	

	//bi-directional many-to-one association to Timesheet
    @ManyToOne
	@JoinColumn(name="TSID")
	public TimesheetEntity getTimeSheet() {
		return this.timeSheet;
	}

	public void setTimeSheet(TimesheetEntity timeSheet) {
		this.timeSheet = timeSheet;
	}
	

	//bi-directional many-to-one association to Workpackage
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="PROJID", referencedColumnName="PROJID"),
		@JoinColumn(name="WPID", referencedColumnName="WPID")
		})
	public WorkpackageEntity getWorkPackage() {
		return this.workPackage;
	}

	public void setWorkPackage(WorkpackageEntity workPackage) {
		this.workPackage = workPackage;
	}
	
}