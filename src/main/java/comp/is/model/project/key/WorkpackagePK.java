package comp.is.model.project.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the WORKPACKAGE database table.
 * 
 */
@Embeddable
public class WorkpackagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String wpid;
	private String projid;

    public WorkpackagePK() {
    }

	@Column(unique=true, nullable=false, length=16)
	public String getWpid() {
		return this.wpid;
	}
	public void setWpid(String wpid) {
		this.wpid = wpid;
	}

	@Column(unique=true, nullable=false, length=16)
	public String getProjid() {
		return this.projid;
	}
	public void setProjid(String projid) {
		this.projid = projid;
	}

	@Override
    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WorkpackagePK)) {
			return false;
		}
		WorkpackagePK castOther = (WorkpackagePK)other;
		return 
			this.wpid.equals(castOther.wpid)
			&& this.projid.equals(castOther.projid);

    }
    
	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.wpid.hashCode();
		hash = hash * prime + this.projid.hashCode();
		
		return hash;
    }
}