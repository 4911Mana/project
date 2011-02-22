package comp.is.controller.project.entities.keys;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EMPLOYEEROLES database table.
 * 
 */
@Embeddable
public class EmployeerolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private long empid;
	private String projid;
	private long roleid;

    public EmployeerolePK() {
    }

	@Column(unique=true, nullable=false, precision=16)
	public long getEmpid() {
		return this.empid;
	}
	public void setEmpid(long empid) {
		this.empid = empid;
	}

	@Column(unique=true, nullable=false, length=16)
	public String getProjid() {
		return this.projid;
	}
	public void setProjid(String projid) {
		this.projid = projid;
	}

	@Column(unique=true, nullable=false, precision=16)
	public long getRoleid() {
		return this.roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

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
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.empid ^ (this.empid >>> 32)));
		hash = hash * prime + this.projid.hashCode();
		hash = hash * prime + ((int) (this.roleid ^ (this.roleid >>> 32)));
		
		return hash;
    }
}