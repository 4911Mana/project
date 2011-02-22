package comp.is.controller.project.entities;

import comp.is.controller.project.entities.keys.WorkpackagePK;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Workpackage.class)
public abstract class Workpackage_ {

	public static volatile ListAttribute<Workpackage, Workpackage> wpChildren;
	public static volatile SingularAttribute<Workpackage, String> wppurpose;
	public static volatile ListAttribute<Workpackage, Timesheetentry> timeSheetEntries;
	public static volatile SingularAttribute<Workpackage, Employee> wpResponsibleEngineer;
	public static volatile SingularAttribute<Workpackage, WorkpackagePK> id;
	public static volatile SingularAttribute<Workpackage, Workpackage> wpParent;
	public static volatile SingularAttribute<Workpackage, String> wpinputs;
	public static volatile SingularAttribute<Workpackage, String> wpoutputs;
	public static volatile SingularAttribute<Workpackage, Project> project;
	public static volatile SingularAttribute<Workpackage, String> wpactivities;
	public static volatile ListAttribute<Workpackage, Workpackagestatusreport> wpStatusReports;
	public static volatile ListAttribute<Workpackage, Employee> wpEmployeesAssigned;
	public static volatile SingularAttribute<Workpackage, String> wpstatus;
	public static volatile SingularAttribute<Workpackage, String> wptitle;
	public static volatile SingularAttribute<Workpackage, Date> wpissuedate;

}

