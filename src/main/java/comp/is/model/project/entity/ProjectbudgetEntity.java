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
	private String projbudgetdesc;
	private ProjectEntity project;
	private String projid;
	private Double amount;

    public ProjectbudgetEntity() {
    }


//	@Column(name="desc",length=2048)
//	public String getDescr() {
//		return this.projbudgetdesc;
//	}

	//bi-directional one-to-one association to Project
	@OneToOne
	@JoinColumn(name="PROJID")
	public ProjectEntity getProject() {
		return this.project;
	}


	@Id
	@Column(unique=true, nullable=false, length=16)
	public String getProjid() {
		return this.projid;
	}
	
	@Column
	public Double getInitBudget() {
        return amount;
    }


    public void setInitBudget(Double budget) {
        this.amount = budget;
    }

//
//    public void setDescr(String projbudgetdesc) {
//		this.projbudgetdesc = projbudgetdesc;
//	}


	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public void setProjid(String projid) {
		this.projid = projid;
	}
	
}