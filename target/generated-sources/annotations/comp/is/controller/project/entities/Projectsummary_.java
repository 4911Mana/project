package comp.is.controller.project.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Projectsummary.class)
public abstract class Projectsummary_ {

	public static volatile SingularAttribute<Projectsummary, Project> project;
	public static volatile SingularAttribute<Projectsummary, String> projsumanticprognextprdcomm;
	public static volatile SingularAttribute<Projectsummary, String> projsumprobencounteredcomm;
	public static volatile SingularAttribute<Projectsummary, Date> projsumreportdate;
	public static volatile SingularAttribute<Projectsummary, Long> projsumreportid;
	public static volatile SingularAttribute<Projectsummary, String> projsumapproval;
	public static volatile SingularAttribute<Projectsummary, Employee> projectManager;
	public static volatile SingularAttribute<Projectsummary, Date> projsummonthending;
	public static volatile SingularAttribute<Projectsummary, String> projsumprogdurperiodcomm;

}

