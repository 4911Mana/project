package comp.is.controller.project.entities;

import comp.is.controller.project.entities.keys.EmployeerolePK;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employeerole.class)
public abstract class Employeerole_ {

	public static volatile SingularAttribute<Employeerole, EmployeerolePK> id;
	public static volatile SingularAttribute<Employeerole, Project> project;
	public static volatile SingularAttribute<Employeerole, Role> role;
	public static volatile SingularAttribute<Employeerole, Employee> employee;

}

