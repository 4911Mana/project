package comp.is.controller.project.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Timesheetweek.class)
public abstract class Timesheetweek_ {

	public static volatile SingularAttribute<Timesheetweek, Long> tsweekid;
	public static volatile ListAttribute<Timesheetweek, Timesheet> timeSheets;
	public static volatile SingularAttribute<Timesheetweek, Date> tsweekend;
	public static volatile ListAttribute<Timesheetweek, Employeelabourchargerate> employeeLabourChargeRates;
	public static volatile SingularAttribute<Timesheetweek, BigDecimal> tsweeknum;

}

