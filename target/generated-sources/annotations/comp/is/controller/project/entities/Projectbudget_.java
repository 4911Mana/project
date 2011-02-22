package comp.is.controller.project.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Projectbudget.class)
public abstract class Projectbudget_ {

	public static volatile SingularAttribute<Projectbudget, Project> project;
	public static volatile SingularAttribute<Projectbudget, String> projid;
	public static volatile SingularAttribute<Projectbudget, String> projbudgetdesc;

}

