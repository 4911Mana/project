package comp.is.controller.project.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Project.class)
public abstract class Project_ {

	public static volatile ListAttribute<Project, Employeerole> employeeRoles;
	public static volatile SingularAttribute<Project, Projectbudget> projectBudget;
	public static volatile ListAttribute<Project, Workpackage> workPackages;
	public static volatile SingularAttribute<Project, String> projcustomer;
	public static volatile ListAttribute<Project, Timesheetentry> timeSheetEntries;
	public static volatile ListAttribute<Project, Projectsummary> projectSummaries;
	public static volatile SingularAttribute<Project, String> projname;
	public static volatile SingularAttribute<Project, String> projid;
	public static volatile SingularAttribute<Project, Double> projlabourratemarkup;
	public static volatile ListAttribute<Project, Ratesheet> rateSheets;
	public static volatile ListAttribute<Project, Employee> projectEmployees;

}

