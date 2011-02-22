package comp.is.controller.project.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Workassignment.class)
public abstract class Workassignment_ {

	public static volatile SingularAttribute<Workassignment, Date> waprojmanagerelecsigndate;
	public static volatile SingularAttribute<Workassignment, BigDecimal> wareportstoid;
	public static volatile SingularAttribute<Workassignment, Long> workassignid;
	public static volatile SingularAttribute<Workassignment, Date> waapproxenddate;
	public static volatile SingularAttribute<Workassignment, String> wafundingsource;
	public static volatile SingularAttribute<Workassignment, String> waearlytermoption;
	public static volatile SingularAttribute<Workassignment, Date> waapproxstartdate;
	public static volatile SingularAttribute<Workassignment, String> watype;
	public static volatile SingularAttribute<Workassignment, String> projid;
	public static volatile SingularAttribute<Workassignment, Date> waengineerelecsigndate;
	public static volatile SingularAttribute<Workassignment, Date> watechressuperelecsigndate;
	public static volatile SingularAttribute<Workassignment, String> wamajordeliverables;
	public static volatile SingularAttribute<Workassignment, BigDecimal> empid;
	public static volatile SingularAttribute<Workassignment, BigDecimal> waestimatedmandays;
	public static volatile SingularAttribute<Workassignment, String> wajobdesc;
	public static volatile SingularAttribute<Workassignment, String> waauthority;
	public static volatile SingularAttribute<Workassignment, String> waresponsibilities;
	public static volatile SingularAttribute<Workassignment, String> wainteractswith;
	public static volatile SingularAttribute<Workassignment, String> wailldefinedassignment;
	public static volatile SingularAttribute<Workassignment, String> waevaluationcriteria;
	public static volatile SingularAttribute<Workassignment, String> waspecialsupervision;
	public static volatile SingularAttribute<Workassignment, BigDecimal> waengineerid;
	public static volatile SingularAttribute<Workassignment, String> waetcetera;

}

