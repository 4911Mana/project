package comp.is.controller.project.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> emplastname;
	public static volatile SingularAttribute<Employee, Double> empaccumvacation;
	public static volatile ListAttribute<Employee, Employee> employeeSupervisors;
	public static volatile ListAttribute<Employee, Employee> timeSheetApprovers;
	public static volatile ListAttribute<Employee, Projectsummary> projectSummaries;
	public static volatile ListAttribute<Employee, Ratesheet> rateSheets;
	public static volatile ListAttribute<Employee, Employeerole> employeeRoles;
	public static volatile SingularAttribute<Employee, Integer> empid;
	public static volatile ListAttribute<Employee, Project> projects;
	public static volatile ListAttribute<Employee, Timesheet> timeSheets;
	public static volatile ListAttribute<Employee, Workpackage> employeesWorkPackages;
	public static volatile SingularAttribute<Employee, Double> empaccumflextime;
	public static volatile SingularAttribute<Employee, String> empfirstname;
	public static volatile SingularAttribute<Employee, Double> emppercentfulltime;
	public static volatile ListAttribute<Employee, Employeelabourchargerate> employeeLabourChargeRates;

}

