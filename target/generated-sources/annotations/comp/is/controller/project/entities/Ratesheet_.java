package comp.is.controller.project.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Ratesheet.class)
public abstract class Ratesheet_ {

	public static volatile SingularAttribute<Ratesheet, Long> ratesheetid;
	public static volatile SingularAttribute<Ratesheet, Project> project;
	public static volatile SingularAttribute<Ratesheet, Employee> projectManager;

}

