package comp.is.controller.project.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class Timesheetweek implements Serializable {
	private static final long serialVersionUID = 1L;
	private long tsweekid;
	private Date tsweekend;
	private BigDecimal tsweeknum;
	private List<Employeelabourchargerate> employeeLabourChargeRates;
	private List<Timesheet> timeSheets;

    public Timesheetweek() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getTsweekid() {
		return this.tsweekid;
	}

	public void setTsweekid(long tsweekid) {
		this.tsweekid = tsweekid;
	}


    @Temporal( TemporalType.DATE)
	public Date getTsweekend() {
		return this.tsweekend;
	}

	public void setTsweekend(Date tsweekend) {
		this.tsweekend = tsweekend;
	}


	@Column(precision=2)
	public BigDecimal getTsweeknum() {
		return this.tsweeknum;
	}

	public void setTsweeknum(BigDecimal tsweeknum) {
		this.tsweeknum = tsweeknum;
	}


	//bi-directional many-to-one association to Employeelabourchargerate
	@OneToMany(mappedBy="startTimeSheetWeek")
	public List<Employeelabourchargerate> getEmployeeLabourChargeRates() {
		return this.employeeLabourChargeRates;
	}

	public void setEmployeeLabourChargeRates(List<Employeelabourchargerate> employeeLabourChargeRates) {
		this.employeeLabourChargeRates = employeeLabourChargeRates;
	}	

	//bi-directional many-to-one association to Timesheet
	@OneToMany(mappedBy="timeSheetWeek")
	public List<Timesheet> getTimeSheets() {
		return this.timeSheets;
	}

	public void setTimeSheets(List<Timesheet> timeSheets) {
		this.timeSheets = timeSheets;
	}
	
}