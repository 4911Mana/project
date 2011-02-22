package comp.is.controller.project.entities;

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
public class Timesheetentry implements Serializable {
	private static final long serialVersionUID = 1L;
	private long tsentryid;
	private double tsentryfrihours;
	private double tsentrymonhours;
	private double tsentrysathours;
	private double tsentrysunhours;
	private double tsentrythuhours;
	private double tsentrytuehours;
	private double tsentrywedhours;
	private Project project;
	private Timesheet timeSheet;
	private Workpackage workPackage;

    public Timesheetentry() {
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
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

	//bi-directional many-to-one association to Timesheet
    @ManyToOne
	@JoinColumn(name="TSID")
	public Timesheet getTimeSheet() {
		return this.timeSheet;
	}

	public void setTimeSheet(Timesheet timeSheet) {
		this.timeSheet = timeSheet;
	}
	

	//bi-directional many-to-one association to Workpackage
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="PROJID", referencedColumnName="PROJID"),
		@JoinColumn(name="WPID", referencedColumnName="WPID")
		})
	public Workpackage getWorkPackage() {
		return this.workPackage;
	}

	public void setWorkPackage(Workpackage workPackage) {
		this.workPackage = workPackage;
	}
	
}