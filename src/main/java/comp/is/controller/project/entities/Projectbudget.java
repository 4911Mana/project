package comp.is.controller.project.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PROJECTBUDGET database table.
 * 
 */
@Entity
@Table(name="PROJECTBUDGET")
public class Projectbudget implements Serializable {
	private static final long serialVersionUID = 1L;
	private String projid;
	private String projbudgetdesc;
	private Project project;

    public Projectbudget() {
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
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}