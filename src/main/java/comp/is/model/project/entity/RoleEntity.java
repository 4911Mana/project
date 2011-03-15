package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the ROLE database table.
 * 
 */
@Entity
@Table(name="ROLE")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<EmployeeroleEntity> employeeRoles;
	private long roleid;
	private String rolename;

    public RoleEntity() {
    }


	//bi-directional many-to-one association to Employeerole
	@OneToMany(mappedBy="role")
	public List<EmployeeroleEntity> getEmployeeRoles() {
		return this.employeeRoles;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=16)
	public long getRoleid() {
		return this.roleid;
	}


	@Column(length=64)
	public String getRolename() {
		return this.rolename;
	}

	public void setEmployeeRoles(List<EmployeeroleEntity> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}


	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
}