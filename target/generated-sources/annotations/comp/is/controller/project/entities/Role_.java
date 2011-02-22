package comp.is.controller.project.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile ListAttribute<Role, Employeerole> employeeRoles;
	public static volatile SingularAttribute<Role, String> rolename;
	public static volatile SingularAttribute<Role, Long> roleid;

}

