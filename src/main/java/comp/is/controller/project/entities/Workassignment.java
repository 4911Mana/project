package comp.is.controller.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the WORKASSIGNMENT database table.
 * 
 */
@Entity
@Table(name="WORKASSIGNMENT")
public class Workassignment implements Serializable {
	private static final long serialVersionUID = 1L;
	private long workassignid;
	private BigDecimal empid;
	private String projid;
	private Date waapproxenddate;
	private Date waapproxstartdate;
	private String waauthority;
	private String waearlytermoption;
	private Date waengineerelecsigndate;
	private BigDecimal waengineerid;
	private BigDecimal waestimatedmandays;
	private String waetcetera;
	private String waevaluationcriteria;
	private String wafundingsource;
	private String wailldefinedassignment;
	private String wainteractswith;
	private String wajobdesc;
	private String wamajordeliverables;
	private Date waprojmanagerelecsigndate;
	private BigDecimal wareportstoid;
	private String waresponsibilities;
	private String waspecialsupervision;
	private Date watechressuperelecsigndate;
	private String watype;

    public Workassignment() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getWorkassignid() {
		return this.workassignid;
	}

	public void setWorkassignid(long workassignid) {
		this.workassignid = workassignid;
	}


	@Column(precision=16)
	public BigDecimal getEmpid() {
		return this.empid;
	}

	public void setEmpid(BigDecimal empid) {
		this.empid = empid;
	}


	@Column(length=16)
	public String getProjid() {
		return this.projid;
	}

	public void setProjid(String projid) {
		this.projid = projid;
	}


    @Temporal( TemporalType.DATE)
	public Date getWaapproxenddate() {
		return this.waapproxenddate;
	}

	public void setWaapproxenddate(Date waapproxenddate) {
		this.waapproxenddate = waapproxenddate;
	}


    @Temporal( TemporalType.DATE)
	public Date getWaapproxstartdate() {
		return this.waapproxstartdate;
	}

	public void setWaapproxstartdate(Date waapproxstartdate) {
		this.waapproxstartdate = waapproxstartdate;
	}


	@Column(length=2048)
	public String getWaauthority() {
		return this.waauthority;
	}

	public void setWaauthority(String waauthority) {
		this.waauthority = waauthority;
	}


	@Column(length=64)
	public String getWaearlytermoption() {
		return this.waearlytermoption;
	}

	public void setWaearlytermoption(String waearlytermoption) {
		this.waearlytermoption = waearlytermoption;
	}


    @Temporal( TemporalType.DATE)
	public Date getWaengineerelecsigndate() {
		return this.waengineerelecsigndate;
	}

	public void setWaengineerelecsigndate(Date waengineerelecsigndate) {
		this.waengineerelecsigndate = waengineerelecsigndate;
	}


	@Column(precision=16)
	public BigDecimal getWaengineerid() {
		return this.waengineerid;
	}

	public void setWaengineerid(BigDecimal waengineerid) {
		this.waengineerid = waengineerid;
	}


	@Column(precision=10, scale=1)
	public BigDecimal getWaestimatedmandays() {
		return this.waestimatedmandays;
	}

	public void setWaestimatedmandays(BigDecimal waestimatedmandays) {
		this.waestimatedmandays = waestimatedmandays;
	}


	@Column(length=1024)
	public String getWaetcetera() {
		return this.waetcetera;
	}

	public void setWaetcetera(String waetcetera) {
		this.waetcetera = waetcetera;
	}


	@Column(length=1024)
	public String getWaevaluationcriteria() {
		return this.waevaluationcriteria;
	}

	public void setWaevaluationcriteria(String waevaluationcriteria) {
		this.waevaluationcriteria = waevaluationcriteria;
	}


	@Column(length=64)
	public String getWafundingsource() {
		return this.wafundingsource;
	}

	public void setWafundingsource(String wafundingsource) {
		this.wafundingsource = wafundingsource;
	}


	@Column(length=1024)
	public String getWailldefinedassignment() {
		return this.wailldefinedassignment;
	}

	public void setWailldefinedassignment(String wailldefinedassignment) {
		this.wailldefinedassignment = wailldefinedassignment;
	}


	@Column(length=1024)
	public String getWainteractswith() {
		return this.wainteractswith;
	}

	public void setWainteractswith(String wainteractswith) {
		this.wainteractswith = wainteractswith;
	}


	@Column(length=2048)
	public String getWajobdesc() {
		return this.wajobdesc;
	}

	public void setWajobdesc(String wajobdesc) {
		this.wajobdesc = wajobdesc;
	}


	@Column(length=2048)
	public String getWamajordeliverables() {
		return this.wamajordeliverables;
	}

	public void setWamajordeliverables(String wamajordeliverables) {
		this.wamajordeliverables = wamajordeliverables;
	}


    @Temporal( TemporalType.DATE)
	public Date getWaprojmanagerelecsigndate() {
		return this.waprojmanagerelecsigndate;
	}

	public void setWaprojmanagerelecsigndate(Date waprojmanagerelecsigndate) {
		this.waprojmanagerelecsigndate = waprojmanagerelecsigndate;
	}


	@Column(precision=16)
	public BigDecimal getWareportstoid() {
		return this.wareportstoid;
	}

	public void setWareportstoid(BigDecimal wareportstoid) {
		this.wareportstoid = wareportstoid;
	}


	@Column(length=2048)
	public String getWaresponsibilities() {
		return this.waresponsibilities;
	}

	public void setWaresponsibilities(String waresponsibilities) {
		this.waresponsibilities = waresponsibilities;
	}


	@Column(length=1024)
	public String getWaspecialsupervision() {
		return this.waspecialsupervision;
	}

	public void setWaspecialsupervision(String waspecialsupervision) {
		this.waspecialsupervision = waspecialsupervision;
	}


    @Temporal( TemporalType.DATE)
	public Date getWatechressuperelecsigndate() {
		return this.watechressuperelecsigndate;
	}

	public void setWatechressuperelecsigndate(Date watechressuperelecsigndate) {
		this.watechressuperelecsigndate = watechressuperelecsigndate;
	}


	@Column(length=64)
	public String getWatype() {
		return this.watype;
	}

	public void setWatype(String watype) {
		this.watype = watype;
	}

}