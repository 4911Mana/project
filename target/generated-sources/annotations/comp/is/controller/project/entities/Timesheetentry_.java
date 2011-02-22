package comp.is.controller.project.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Timesheetentry.class)
public abstract class Timesheetentry_ {

	public static volatile SingularAttribute<Timesheetentry, Workpackage> workPackage;
	public static volatile SingularAttribute<Timesheetentry, Project> project;
	public static volatile SingularAttribute<Timesheetentry, Double> tsentrysathours;
	public static volatile SingularAttribute<Timesheetentry, Double> tsentrysunhours;
	public static volatile SingularAttribute<Timesheetentry, Double> tsentrytuehours;
	public static volatile SingularAttribute<Timesheetentry, Long> tsentryid;
	public static volatile SingularAttribute<Timesheetentry, Timesheet> timeSheet;
	public static volatile SingularAttribute<Timesheetentry, Double> tsentrywedhours;
	public static volatile SingularAttribute<Timesheetentry, Double> tsentrymonhours;
	public static volatile SingularAttribute<Timesheetentry, Double> tsentryfrihours;
	public static volatile SingularAttribute<Timesheetentry, Double> tsentrythuhours;

}

