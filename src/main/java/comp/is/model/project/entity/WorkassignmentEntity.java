package comp.is.model.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the WORKASSIGNMENT database table.
 * 
 */
@Entity
@Table(name="WORKASSIGNMENT")
public class WorkassignmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
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
	private long workassignid;

    public WorkassignmentEntity() {
    }


	@Column(precision=16)
	public BigDecimal getEmpid() {
		return this.empid;
	}

	@Column(length=16)
	public String getProjid() {
		return this.projid;
	}


	@Temporal( TemporalType.DATE)
	public Date getWaapproxenddate() {
		return this.waapproxenddate;
	}

	@Temporal( TemporalType.DATE)
	public Date getWaapproxstartdate() {
		return this.waapproxstartdate;
	}


	@Column(length=2048)
	public String getWaauthority() {
		return this.waauthority;
	}

	@Column(length=64)
	public String getWaearlytermoption() {
		return this.waearlytermoption;
	}


    @Temporal( TemporalType.DATE)
	public Date getWaengineerelecsigndate() {
		return this.waengineerelecsigndate;
	}

	@Column(precision=16)
	public BigDecimal getWaengineerid() {
		return this.waengineerid;
	}


    @Column(precision=10, scale=1)
	public BigDecimal getWaestimatedmandays() {
		return this.waestimatedmandays;
	}

	@Column(length=1024)
	public String getWaetcetera() {
		return this.waetcetera;
	}


	@Column(length=1024)
	public String getWaevaluationcriteria() {
		return this.waevaluationcriteria;
	}

	@Column(length=64)
	public String getWafundingsource() {
		return this.wafundingsource;
	}


	@Column(length=1024)
	public String getWailldefinedassignment() {
		return this.wailldefinedassignment;
	}

	@Column(length=1024)
	public String getWainteractswith() {
		return this.wainteractswith;
	}


    @Column(length=2048)
	public String getWajobdesc() {
		return this.wajobdesc;
	}

	@Column(length=2048)
	public String getWamajordeliverables() {
		return this.wamajordeliverables;
	}


	@Temporal( TemporalType.DATE)
	public Date getWaprojmanagerelecsigndate() {
		return this.waprojmanagerelecsigndate;
	}

	@Column(precision=16)
	public BigDecimal getWareportstoid() {
		return this.wareportstoid;
	}


	@Column(length=2048)
	public String getWaresponsibilities() {
		return this.waresponsibilities;
	}

	@Column(length=1024)
	public String getWaspecialsupervision() {
		return this.waspecialsupervision;
	}


	@Temporal( TemporalType.DATE)
	public Date getWatechressuperelecsigndate() {
		return this.watechressuperelecsigndate;
	}

	@Column(length=64)
	public String getWatype() {
		return this.watype;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getWorkassignid() {
		return this.workassignid;
	}

	public void setEmpid(BigDecimal empid) {
		this.empid = empid;
	}


	public void setProjid(String projid) {
		this.projid = projid;
	}

	public void setWaapproxenddate(Date waapproxenddate) {
		this.waapproxenddate = waapproxenddate;
	}


	public void setWaapproxstartdate(Date waapproxstartdate) {
		this.waapproxstartdate = waapproxstartdate;
	}

	public void setWaauthority(String waauthority) {
		this.waauthority = waauthority;
	}


	public void setWaearlytermoption(String waearlytermoption) {
		this.waearlytermoption = waearlytermoption;
	}

	public void setWaengineerelecsigndate(Date waengineerelecsigndate) {
		this.waengineerelecsigndate = waengineerelecsigndate;
	}


	public void setWaengineerid(BigDecimal waengineerid) {
		this.waengineerid = waengineerid;
	}

	public void setWaestimatedmandays(BigDecimal waestimatedmandays) {
		this.waestimatedmandays = waestimatedmandays;
	}


	public void setWaetcetera(String waetcetera) {
		this.waetcetera = waetcetera;
	}

	public void setWaevaluationcriteria(String waevaluationcriteria) {
		this.waevaluationcriteria = waevaluationcriteria;
	}


    public void setWafundingsource(String wafundingsource) {
		this.wafundingsource = wafundingsource;
	}

	public void setWailldefinedassignment(String wailldefinedassignment) {
		this.wailldefinedassignment = wailldefinedassignment;
	}


	public void setWainteractswith(String wainteractswith) {
		this.wainteractswith = wainteractswith;
	}

	public void setWajobdesc(String wajobdesc) {
		this.wajobdesc = wajobdesc;
	}


	public void setWamajordeliverables(String wamajordeliverables) {
		this.wamajordeliverables = wamajordeliverables;
	}

	public void setWaprojmanagerelecsigndate(Date waprojmanagerelecsigndate) {
		this.waprojmanagerelecsigndate = waprojmanagerelecsigndate;
	}


	public void setWareportstoid(BigDecimal wareportstoid) {
		this.wareportstoid = wareportstoid;
	}

	public void setWaresponsibilities(String waresponsibilities) {
		this.waresponsibilities = waresponsibilities;
	}


    public void setWaspecialsupervision(String waspecialsupervision) {
		this.waspecialsupervision = waspecialsupervision;
	}

	public void setWatechressuperelecsigndate(Date watechressuperelecsigndate) {
		this.watechressuperelecsigndate = watechressuperelecsigndate;
	}


	public void setWatype(String watype) {
		this.watype = watype;
	}

	public void setWorkassignid(long workassignid) {
		this.workassignid = workassignid;
	}

}