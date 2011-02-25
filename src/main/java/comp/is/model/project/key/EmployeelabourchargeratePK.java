package comp.is.model.project.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the EMPLOYEELABOURCHARGERATE database table.
 * 
 */
@Embeddable
public class EmployeelabourchargeratePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private long empid;
	private long labourchargerateid;

    public EmployeelabourchargeratePK() {
    }

	@Column(unique=true, nullable=false, precision=16)
	public long getEmpid() {
		return this.empid;
	}
	public void setEmpid(long empid) {
		this.empid = empid;
	}

	@Column(unique=true, nullable=false, precision=16)
	public long getLabourchargerateid() {
		return this.labourchargerateid;
	}
	public void setLabourchargerateid(long labourchargerateid) {
		this.labourchargerateid = labourchargerateid;
	}

	@Override
    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmployeelabourchargeratePK)) {
			return false;
		}
		EmployeelabourchargeratePK castOther = (EmployeelabourchargeratePK)other;
		return 
			(this.empid == castOther.empid)
			&& (this.labourchargerateid == castOther.labourchargerateid);

    }
    
	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.empid ^ (this.empid >>> 32)));
		hash = hash * prime + ((int) (this.labourchargerateid ^ (this.labourchargerateid >>> 32)));
		
		return hash;
    }
}