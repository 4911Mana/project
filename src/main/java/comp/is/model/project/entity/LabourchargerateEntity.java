package comp.is.model.project.entity;

import java.io.Serializable;
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
 * The persistent class for the LABOURCHARGERATE database table.
 * 
 */
@Entity
@Table(name="LABOURCHARGERATE")
public class LabourchargerateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private double rate;
	private Date effectiveDate;
	private String rateclassid;

    public LabourchargerateEntity() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getId() {
		return id;
	}

	@Column(precision=126)
	public double getRate() {
		return rate;
	}


	@Temporal( TemporalType.DATE)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	@Column(length=16)
	public String getRateclassid() {
		return this.rateclassid;
	}


    public void setId(long labourchargerateid) {
		id = labourchargerateid;
	}

	public void setRate(double lcrrate) {
		rate = lcrrate;
	}


	public void setEffectiveDate(Date lcryear) {
		effectiveDate = lcryear;
	}

	public void setRateclassid(String rateclassid) {
		this.rateclassid = rateclassid;
	}

}