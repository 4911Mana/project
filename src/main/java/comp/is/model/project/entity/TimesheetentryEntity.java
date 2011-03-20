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
	//private ProjectEntity project;
	private TimesheetEntity timeSheet;
	private double frihours;
	private long id;
	private double monhours;
	private double sathours;
	private double sunhours;
	private double thuhours;
	private double tuehours;
	private double wedhours;
	private WorkpackageEntity workPackage;
	private String notes;

    public TimesheetentryEntity() {
    }


	//bi-directional many-to-one association to Project
//    @ManyToOne
//	@JoinColumn(name="PROJID", insertable=false, updatable=false)
//	public ProjectEntity getProject() {
//		return this.project;
//	}

	//bi-directional many-to-one association to Timesheet
    @ManyToOne
	@JoinColumn(name="TSID")
	public TimesheetEntity getTimeSheet() {
		return this.timeSheet;
	}


	@Column(precision=126)
	public double getFrihours() {
		return frihours;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getId() {
		return this.id;
	}


	@Column(precision=126)
	public double getMonhours() {
		return monhours;
	}

	@Column(precision=126)
	public double getSathours() {
		return sathours;
	}


	@Column(precision=126)
	public double getSunhours() {
		return sunhours;
	}

	@Column(precision=126)
	public double getThuhours() {
		return thuhours;
	}


	@Column(precision=126)
	public double getTuehours() {
		return tuehours;
	}

	@Column(precision=126)
	public double getWedhours() {
		return wedhours;
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

//	public void setProject(ProjectEntity project) {
//		this.project = project;
//	}


	public void setTimeSheet(TimesheetEntity timeSheet) {
		this.timeSheet = timeSheet;
	}

	public void setFrihours(double tsentryfrihours) {
		frihours = tsentryfrihours;
	}


	public void setId(long tsentryid) {
		this.id = tsentryid;
	}

	public void setMonhours(double tsentrymonhours) {
		monhours = tsentrymonhours;
	}


	public void setSathours(double tsentrysathours) {
		sathours = tsentrysathours;
	}

	public void setSunhours(double tsentrysunhours) {
		sunhours = tsentrysunhours;
	}
	

	public void setThuhours(double tsentrythuhours) {
		thuhours = tsentrythuhours;
	}

	public void setTuehours(double tsentrytuehours) {
		tuehours = tsentrytuehours;
	}
	

	public void setWedhours(double tsentrywedhours) {
		wedhours = tsentrywedhours;
	}

	public void setWorkPackage(WorkpackageEntity workPackage) {
		this.workPackage = workPackage;
	}

	@Column(length=1024)
    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }
	
}