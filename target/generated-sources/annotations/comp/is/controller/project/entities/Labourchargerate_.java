package comp.is.controller.project.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Labourchargerate.class)
public abstract class Labourchargerate_ {

	public static volatile SingularAttribute<Labourchargerate, Date> lcryear;
	public static volatile SingularAttribute<Labourchargerate, Long> labourchargerateid;
	public static volatile SingularAttribute<Labourchargerate, Double> lcrrate;
	public static volatile SingularAttribute<Labourchargerate, String> rateclassid;

}

