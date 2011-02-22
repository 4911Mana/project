package comp.is.controller.project.entities;

import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Timesheet.class)
public abstract class Timesheet_ {

	public static volatile SingularAttribute<Timesheet, Date> tsapprovedbyelecsignaturedate;
	public static volatile SingularAttribute<Timesheet, Date> tsempelecsignaturedate;
	public static volatile ListAttribute<Timesheet, Timesheetentry> timeSheetEntries;
	public static volatile SingularAttribute<Timesheet, Timesheetweek> timeSheetWeek;
	public static volatile SingularAttribute<Timesheet, Employee> employee;
	public static volatile SingularAttribute<Timesheet, Long> tsid;
	public static volatile SingularAttribute<Timesheet, Employee> timeSheetApprover;
	public static volatile SingularAttribute<Timesheet, String> tsnotes;

}

