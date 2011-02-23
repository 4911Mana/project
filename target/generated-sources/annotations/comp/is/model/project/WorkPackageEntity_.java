package comp.is.model.project;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WorkPackageEntity.class)
public abstract class WorkPackageEntity_ {

	public static volatile SingularAttribute<WorkPackageEntity, Boolean> summary;
	public static volatile SingularAttribute<WorkPackageEntity, Date> startDate;
	public static volatile SingularAttribute<WorkPackageEntity, String> title;
	public static volatile SingularAttribute<WorkPackageEntity, Boolean> openForCharges;
	public static volatile SingularAttribute<WorkPackageEntity, String> description;
	public static volatile SingularAttribute<WorkPackageEntity, String> number;

}

