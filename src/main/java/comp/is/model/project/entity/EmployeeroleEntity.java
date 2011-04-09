package comp.is.model.project.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import comp.is.model.admin.Employee;
import comp.is.model.project.key.EmployeerolePK;


/**
 * The persistent class for the EMPLOYEEROLES database table.
 * 
 */
@Entity
@Table(name="EMPLOYEEROLES")
public class EmployeeroleEntity implements Serializable {
	private EmployeeEntity employee;
	private EmployeerolePK id;
	private ProjectEntity project;
	private RoleEntity role;

    public EmployeeroleEntity() {
    }


	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="EMPID", nullable=false, insertable=false, updatable=false)
	public EmployeeEntity getEmployee() {
		return this.employee;
	}

	@EmbeddedId
	public EmployeerolePK getId() {
		return this.id;
	}
	

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID", nullable=false, insertable=false, updatable=false)
	public ProjectEntity getProject() {
		return this.project;
	}

	//bi-directional many-to-one association to Role
    @ManyToOne
	@JoinColumn(name="ROLEID", nullable=false, insertable=false, updatable=false)
	public RoleEntity getRole() {
		return this.role;
	}
	

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public void setId(EmployeerolePK id) {
		this.id = id;
	}
	

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object emp) {
	        if (!(emp instanceof EmployeeroleEntity))
	            throw new ClassCastException("A Employee object expected.");
	        EmployeerolePK empId = ((EmployeeroleEntity) emp).getId();
	        return empId.equals(getId());
	    }
	
	public String toString(){
	    return "Proj " + getId().getProjid() + "/Emp " + getId().getEmpid() + "/Role " + getId().getRoleid();
	}
}