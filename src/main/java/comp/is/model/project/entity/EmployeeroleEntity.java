package comp.is.model.project.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import comp.is.model.project.key.EmployeerolePK;


/**
 * The persistent class for the EMPLOYEEROLES database table.
 * 
 */
@Entity
@Table(name="EMPLOYEEROLES")
public class EmployeeroleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private EmployeerolePK id;
	private EmployeeEntity employee;
	private ProjectEntity project;
	private RoleEntity role;

    public EmployeeroleEntity() {
    }


	@EmbeddedId
	public EmployeerolePK getId() {
		return this.id;
	}

	public void setId(EmployeerolePK id) {
		this.id = id;
	}
	

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="EMPID", nullable=false, insertable=false, updatable=false)
	public EmployeeEntity getEmployee() {
		return this.employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}
	

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID", nullable=false, insertable=false, updatable=false)
	public ProjectEntity getProject() {
		return this.project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	

	//bi-directional many-to-one association to Role
    @ManyToOne
	@JoinColumn(name="ROLEID", nullable=false, insertable=false, updatable=false)
	public RoleEntity getRole() {
		return this.role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
}