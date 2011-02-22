package comp.is.controller.project.entities;

import comp.is.controller.project.entities.keys.EmployeelabourchargeratePK;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employeelabourchargerate.class)
public abstract class Employeelabourchargerate_ {

	public static volatile SingularAttribute<Employeelabourchargerate, EmployeelabourchargeratePK> id;
	public static volatile SingularAttribute<Employeelabourchargerate, Timesheetweek> endTimeSheetWeek;
	public static volatile SingularAttribute<Employeelabourchargerate, Labourchargerate> labourChargerRate;
	public static volatile SingularAttribute<Employeelabourchargerate, Timesheetweek> startTimeSheetWeek;
	public static volatile SingularAttribute<Employeelabourchargerate, Employee> employee;

}

