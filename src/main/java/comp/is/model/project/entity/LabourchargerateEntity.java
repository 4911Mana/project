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
	private long labourchargerateid;
	private double lcrrate;
	private Date lcryear;
	private String rateclassid;

    public LabourchargerateEntity() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getLabourchargerateid() {
		return this.labourchargerateid;
	}

	@Column(precision=126)
	public double getLcrrate() {
		return this.lcrrate;
	}


	@Temporal( TemporalType.DATE)
	public Date getLcryear() {
		return this.lcryear;
	}

	@Column(length=16)
	public String getRateclassid() {
		return this.rateclassid;
	}


    public void setLabourchargerateid(long labourchargerateid) {
		this.labourchargerateid = labourchargerateid;
	}

	public void setLcrrate(double lcrrate) {
		this.lcrrate = lcrrate;
	}


	public void setLcryear(Date lcryear) {
		this.lcryear = lcryear;
	}

	public void setRateclassid(String rateclassid) {
		this.rateclassid = rateclassid;
	}

}