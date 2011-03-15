package comp.is.model.project.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.IdClass;

/**
 * The primary key class for the WORKPACKAGE database table.
 * 
 */
public class WorkpackagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String id;
	private String projid;

    public WorkpackagePK() {
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
			this.id.equals(castOther.id)
			&& this.projid.equals(castOther.projid);

    }
	@Column(unique=true, nullable=false, length=16)
	public String getId() {
		return id;
	}

	@Column(unique=true, nullable=false, length=16)
	public String getProjid() {
		return this.projid;
	}
	@Override
    public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.projid.hashCode();
		
		return hash;
    }

	public void setId(String wpid) {
		id = wpid;
	}
    
	public void setProjid(String projid) {
		this.projid = projid;
	}
}