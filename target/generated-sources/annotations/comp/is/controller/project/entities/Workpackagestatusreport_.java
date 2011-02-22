package comp.is.controller.project.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Workpackagestatusreport.class)
public abstract class Workpackagestatusreport_ {

	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrproblemsthisperiodcomm;
	public static volatile SingularAttribute<Workpackagestatusreport, BigDecimal> wpsrdeldocnum;
	public static volatile SingularAttribute<Workpackagestatusreport, Date> wpsrdate;
	public static volatile ListAttribute<Workpackagestatusreport, Workpackage> workPackages;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsraccompthisperiod;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrmileearnedvalue;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrmileprojectedcompletion;
	public static volatile SingularAttribute<Workpackagestatusreport, Employee> projectManager;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrcomments;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrworkplannednextperiodcomm;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrmileactualcompletion;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrproblemsanticcomm;
	public static volatile SingularAttribute<Workpackagestatusreport, Employee> wpResponsibleEngineer;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrmileplannedcompletion;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrdeldesc;
	public static volatile SingularAttribute<Workpackagestatusreport, Employee> projectAssistant;
	public static volatile SingularAttribute<Workpackagestatusreport, Date> wpsrreportingperiod;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrdelactualcompletion;
	public static volatile SingularAttribute<Workpackagestatusreport, String> wpsrdelplannedcompletion;
	public static volatile SingularAttribute<Workpackagestatusreport, Long> workpackagestatusreportid;

}

