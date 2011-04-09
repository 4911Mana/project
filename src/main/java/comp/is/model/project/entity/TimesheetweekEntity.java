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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TIMESHEETWEEK database table.
 * 
 */
@Entity
@Table(name="TIMESHEETWEEK")
public class TimesheetweekEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Set<EmployeelabourchargerateEntity> employeeLabourChargeRates;
	private Set<TimesheetEntity> timeSheets;
	private Date weekend;
	private long id;
	private Integer weeknum;

    public TimesheetweekEntity() {
    }


	//bi-directional many-to-one association to Employeelabourchargerate
	@OneToMany(mappedBy="startTimeSheetWeek")
	public Set<EmployeelabourchargerateEntity> getEmployeeLabourChargeRates() {
		return this.employeeLabourChargeRates;
	}

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="timeSheetWeek")
	public Set<TimesheetEntity> getTimeSheets() {
		return this.timeSheets;
	}


    @Temporal( TemporalType.DATE)
	public Date getWeekend() {
		return this.weekend;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getId() {
		return id;
	}


	@Column(precision=2)
	public Integer getWeeknum() {
		return weeknum;
	}

	public void setEmployeeLabourChargeRates(Set<EmployeelabourchargerateEntity> employeeLabourChargeRates) {
		this.employeeLabourChargeRates = employeeLabourChargeRates;
	}


	public void setTimeSheets(Set<TimesheetEntity> timeSheets) {
		this.timeSheets = timeSheets;
	}

	public void setWeekend(Date tsweekend) {
		weekend = tsweekend;
	}	

	public void setId(long tsweekid) {
		id = tsweekid;
	}

	public void setWeeknum(Integer tsweeknum) {
		weeknum = tsweeknum;
	}
	
}