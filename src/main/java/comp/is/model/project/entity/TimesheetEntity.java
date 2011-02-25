package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TIMESHEET database table.
 * 
 */
@Entity
@Table(name="TIMESHEET")
public class TimesheetEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long tsid;
	private Date tsapprovedbyelecsignaturedate;
	private Date tsempelecsignaturedate;
	private String tsnotes;
	private EmployeeEntity timeSheetApprover;
	private EmployeeEntity employee;
	private TimesheetweekEntity timeSheetWeek;
	private List<TimesheetentryEntity> timeSheetEntries;

    public TimesheetEntity() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getTsid() {
		return this.tsid;
	}

	public void setTsid(long tsid) {
		this.tsid = tsid;
	}


    @Temporal( TemporalType.DATE)
	public Date getTsapprovedbyelecsignaturedate() {
		return this.tsapprovedbyelecsignaturedate;
	}

	public void setTsapprovedbyelecsignaturedate(Date tsapprovedbyelecsignaturedate) {
		this.tsapprovedbyelecsignaturedate = tsapprovedbyelecsignaturedate;
	}


    @Temporal( TemporalType.DATE)
	public Date getTsempelecsignaturedate() {
		return this.tsempelecsignaturedate;
	}

	public void setTsempelecsignaturedate(Date tsempelecsignaturedate) {
		this.tsempelecsignaturedate = tsempelecsignaturedate;
	}


	@Column(length=1024)
	public String getTsnotes() {
		return this.tsnotes;
	}

	public void setTsnotes(String tsnotes) {
		this.tsnotes = tsnotes;
	}


	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="TSAPPROVERID")
	public EmployeeEntity getTimeSheetApprover() {
		return this.timeSheetApprover;
	}

	public void setTimeSheetApprover(EmployeeEntity timeSheetApprover) {
		this.timeSheetApprover = timeSheetApprover;
	}
	

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="EMPID")
	public EmployeeEntity getEmployee() {
		return this.employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}
	

	//bi-directional many-to-one association to Timesheetweek
    @ManyToOne
	@JoinColumn(name="TSWEEKID")
	public TimesheetweekEntity getTimeSheetWeek() {
		return this.timeSheetWeek;
	}

	public void setTimeSheetWeek(TimesheetweekEntity timeSheetWeek) {
		this.timeSheetWeek = timeSheetWeek;
	}
	

	//bi-directional many-to-one association to Timesheetentry
	@OneToMany(mappedBy="timeSheet")
	public List<TimesheetentryEntity> getTimeSheetEntries() {
		return this.timeSheetEntries;
	}

	public void setTimeSheetEntries(List<TimesheetentryEntity> timeSheetEntries) {
		this.timeSheetEntries = timeSheetEntries;
	}
	
}