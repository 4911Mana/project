package comp.is.model.project.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import comp.is.model.project.key.EmployeelabourchargeratePK;


/**
 * The persistent class for the EMPLOYEELABOURCHARGERATE database table.
 * 
 */
@Entity
@Table(name="EMPLOYEELABOURCHARGERATE")
public class EmployeelabourchargerateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private EmployeeEntity employee;
	private TimesheetweekEntity endTimeSheetWeek;
	private EmployeelabourchargeratePK id;
	private LabourchargerateEntity labourChargerRate;
	private TimesheetweekEntity startTimeSheetWeek;

    public EmployeelabourchargerateEntity() {
    }


	//bi-directional many-to-one association to Employee
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="EMPID", nullable=false, insertable=false, updatable=false)
	public EmployeeEntity getEmployee() {
		return this.employee;
	}

	//bi-directional many-to-one association to Timesheetweek
    @ManyToOne
	@JoinColumn(name="ENDCHGRATETSWEEKID")
	public TimesheetweekEntity getEndTimeSheetWeek() {
		return this.endTimeSheetWeek;
	}
	

	@EmbeddedId
	public EmployeelabourchargeratePK getId() {
		return this.id;
	}

	//uni-directional many-to-one association to Labourchargerate
    @ManyToOne
	@JoinColumn(name="LABOURCHARGERATEID", nullable=false, insertable=false, updatable=false)
	public LabourchargerateEntity getLabourChargerRate() {
		return this.labourChargerRate;
	}
	

	//bi-directional many-to-one association to Timesheetweek
    @ManyToOne
	@JoinColumn(name="STARTCHGRATETSWEEKID")
	public TimesheetweekEntity getStartTimeSheetWeek() {
		return this.startTimeSheetWeek;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}
	

	public void setEndTimeSheetWeek(TimesheetweekEntity timeSheetWeek) {
		this.endTimeSheetWeek = timeSheetWeek;
	}

	public void setId(EmployeelabourchargeratePK id) {
		this.id = id;
	}
	

	public void setLabourChargerRate(LabourchargerateEntity labourChargerRate) {
		this.labourChargerRate = labourChargerRate;
	}

	public void setStartTimeSheetWeek(TimesheetweekEntity timeSheetWeek) {
		this.startTimeSheetWeek = timeSheetWeek;
	}
	
}