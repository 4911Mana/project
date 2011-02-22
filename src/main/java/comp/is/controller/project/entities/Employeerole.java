package comp.is.controller.project.entities;

import java.io.Serializable;
import javax.persistence.*;

import comp.is.controller.project.entities.keys.EmployeerolePK;


/**
 * The persistent class for the EMPLOYEEROLES database table.
 * 
 */
@Entity
@Table(name="EMPLOYEEROLES")
public class Employeerole implements Serializable {
	private static final long serialVersionUID = 1L;
	private EmployeerolePK id;
	private Employee employee;
	private Project project;
	private Role role;

    public Employeerole() {
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
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJID", nullable=false, insertable=false, updatable=false)
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

	//bi-directional many-to-one association to Role
    @ManyToOne
	@JoinColumn(name="ROLEID", nullable=false, insertable=false, updatable=false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}