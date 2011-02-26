package comp.is.model.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the PROJECTBUDGET database table.
 * 
 */
@Entity
@Table(name="PROJECTBUDGET")
public class ProjectbudgetEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String projid;
	private String projbudgetdesc;
	private ProjectEntity project;

    public ProjectbudgetEntity() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=16)
	public String getProjid() {
		return this.projid;
	}

	public void setProjid(String projid) {
		this.projid = projid;
	}


	@Column(length=2048)
	public String getProjbudgetdesc() {
		return this.projbudgetdesc;
	}

	public void setProjbudgetdesc(String projbudgetdesc) {
		this.projbudgetdesc = projbudgetdesc;
	}


	//bi-directional one-to-one association to Project
	@OneToOne
	@JoinColumn(name="PROJID", nullable=false, insertable=false, updatable=false)
	public ProjectEntity getProject() {
		return this.project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	
}