package comp.is.controller.project.entities;

import java.io.Serializable;
import javax.persistence.*;

import comp.is.controller.project.entities.keys.EmployeelabourchargeratePK;


/**
 * The persistent class for the EMPLOYEELABOURCHARGERATE database table.
 * 
 */
@Entity
@Table(name="EMPLOYEELABOURCHARGERATE")
public class Employeelabourchargerate implements Serializable {
	private static final long serialVersionUID = 1L;
	private EmployeelabourchargeratePK id;
	private Employee employee;
	private Labourchargerate labourChargerRate;
	private Timesheetweek endTimeSheetWeek;
	private Timesheetweek startTimeSheetWeek;

    public Employeelabourchargerate() {
    }


	@EmbeddedId
	public EmployeelabourchargeratePK getId() {
		return this.id;
	}

	public void setId(EmployeelabourchargeratePK id) {
		this.id = id;
	}
	

	//bi-directional many-to-one association to Employee
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="EMPID", nullable=false, insertable=false, updatable=false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	//uni-directional many-to-one association to Labourchargerate
    @ManyToOne
	@JoinColumn(name="LABOURCHARGERATEID", nullable=false, insertable=false, updatable=false)
	public Labourchargerate getLabourChargerRate() {
		return this.labourChargerRate;
	}

	public void setLabourChargerRate(Labourchargerate labourChargerRate) {
		this.labourChargerRate = labourChargerRate;
	}
	

	//bi-directional many-to-one association to Timesheetweek
    @ManyToOne
	@JoinColumn(name="ENDCHGRATETSWEEKID")
	public Timesheetweek getEndTimeSheetWeek() {
		return this.endTimeSheetWeek;
	}

	public void setEndTimeSheetWeek(Timesheetweek timeSheetWeek) {
		this.endTimeSheetWeek = timeSheetWeek;
	}
	

	//bi-directional many-to-one association to Timesheetweek
    @ManyToOne
	@JoinColumn(name="STARTCHGRATETSWEEKID")
	public Timesheetweek getStartTimeSheetWeek() {
		return this.startTimeSheetWeek;
	}

	public void setStartTimeSheetWeek(Timesheetweek timeSheetWeek) {
		this.startTimeSheetWeek = timeSheetWeek;
	}
	
}