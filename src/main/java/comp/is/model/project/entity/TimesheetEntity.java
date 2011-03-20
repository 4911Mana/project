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
	private EmployeeEntity employee;
	private EmployeeEntity approver;
	private List<TimesheetentryEntity> timeSheetEntries;
	private TimesheetweekEntity timeSheetWeek;
	private Date approvedbyelecsignaturedate;
	private Date empelecsignaturedate;
	private long id;

    public TimesheetEntity() {
    }


	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="EMPID")
	public EmployeeEntity getEmployee() {
		return this.employee;
	}

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="APPROVERID")
	public EmployeeEntity getApprover() {
		return approver;
	}


    //bi-directional many-to-one association to Timesheetentry
	@OneToMany(mappedBy="timeSheet")
	public List<TimesheetentryEntity> getTimeSheetEntries() {
		return this.timeSheetEntries;
	}

	//bi-directional many-to-one association to Timesheetweek
    @ManyToOne
	@JoinColumn(name="WEEKID")
	public TimesheetweekEntity getTimeSheetWeek() {
		return this.timeSheetWeek;
	}


    @Temporal( TemporalType.DATE)
	public Date getApprovedbyelecsignaturedate() {
		return approvedbyelecsignaturedate;
	}

	@Temporal( TemporalType.DATE)
	public Date getEmpelecsignaturedate() {
		return empelecsignaturedate;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getId() {
		return id;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public void setApprover(EmployeeEntity timeSheetApprover) {
		approver = timeSheetApprover;
	}
	

	public void setTimeSheetEntries(List<TimesheetentryEntity> timeSheetEntries) {
		this.timeSheetEntries = timeSheetEntries;
	}

	public void setTimeSheetWeek(TimesheetweekEntity timeSheetWeek) {
		this.timeSheetWeek = timeSheetWeek;
	}
	

	public void setApprovedbyelecsignaturedate(Date tsapprovedbyelecsignaturedate) {
		approvedbyelecsignaturedate = tsapprovedbyelecsignaturedate;
	}

	public void setEmpelecsignaturedate(Date tsempelecsignaturedate) {
		empelecsignaturedate = tsempelecsignaturedate;
	}
	

	public void setId(long tsid) {
		id = tsid;
	}

}