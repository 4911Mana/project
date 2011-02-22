package comp.is.controller.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the LABOURCHARGERATE database table.
 * 
 */
@Entity
@Table(name="LABOURCHARGERATE")
public class Labourchargerate implements Serializable {
	private static final long serialVersionUID = 1L;
	private long labourchargerateid;
	private double lcrrate;
	private Date lcryear;
	private String rateclassid;

    public Labourchargerate() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getLabourchargerateid() {
		return this.labourchargerateid;
	}

	public void setLabourchargerateid(long labourchargerateid) {
		this.labourchargerateid = labourchargerateid;
	}


	@Column(precision=126)
	public double getLcrrate() {
		return this.lcrrate;
	}

	public void setLcrrate(double lcrrate) {
		this.lcrrate = lcrrate;
	}


    @Temporal( TemporalType.DATE)
	public Date getLcryear() {
		return this.lcryear;
	}

	public void setLcryear(Date lcryear) {
		this.lcryear = lcryear;
	}


	@Column(length=16)
	public String getRateclassid() {
		return this.rateclassid;
	}

	public void setRateclassid(String rateclassid) {
		this.rateclassid = rateclassid;
	}

}