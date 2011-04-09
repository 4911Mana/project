package comp.is.model.project.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the EMPLOYEEROLES database table.
 * 
 */
@Embeddable
public class EmployeerolePK implements Serializable {
	//default serial version id, required for serializable classes.
	
	private long empid;
	private String projid;
	private long roleid;

    public EmployeerolePK() {
    }

	@Override
    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmployeerolePK)) {
			return false;
		}
		EmployeerolePK castOther = (EmployeerolePK)other;
		return 
			(this.empid == castOther.empid)
			&& this.projid.equals(castOther.projid)
			&& (this.roleid == castOther.roleid);

    }
	@Column(unique=true, nullable=false, precision=16)
	public long getEmpid() {
		return this.empid;
	}

	@Column(unique=true, nullable=false, length=16)
	public String getProjid() {
		return this.projid;
	}
	@Column(unique=true, nullable=false, precision=16)
	public long getRoleid() {
		return this.roleid;
	}

	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.empid ^ (this.empid >>> 32)));
		hash = hash * prime + this.projid.hashCode();
		hash = hash * prime + ((int) (this.roleid ^ (this.roleid >>> 32)));
		
		return hash;
    }
	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public void setProjid(String projid) {
		this.projid = projid;
	}
    
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
}