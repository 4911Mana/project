package comp.is.model.project;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WorkPackage.class)
public abstract class WorkPackage_ {

	public static volatile SingularAttribute<WorkPackage, Boolean> summary;
	public static volatile SingularAttribute<WorkPackage, Date> startDate;
	public static volatile SingularAttribute<WorkPackage, String> title;
	public static volatile SingularAttribute<WorkPackage, Boolean> openForCharges;
	public static volatile SingularAttribute<WorkPackage, String> description;
	public static volatile SingularAttribute<WorkPackage, String> number;

}

