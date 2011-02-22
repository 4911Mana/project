package comp.is.controller.project;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp.is.controller.project.entities.Employee;
import comp.is.controller.project.entities.Employeelabourchargerate;
import comp.is.controller.project.entities.Employeerole;
import comp.is.controller.project.entities.Labourchargerate;
import comp.is.controller.project.entities.Timesheet;
import comp.is.controller.project.entities.Timesheetweek;
import comp.is.controller.project.entities.Workpackage;

@Stateful
@LocalBean
@Named("empAction")
public class EmployeeAction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="ProjectManager")
	private EntityManager em;
	
	private Employee emp;
	
	public EmployeeAction() {
		
	}
	public EmployeeAction(int empId) {
		initEmp(empId);
	}
	public void initEmp(int empId) {
		/* initialize the employee from the database */
		emp = em.find(Employee.class, empId);
	}
	
	public void persistAll() {
		
	}
	/** note that empId is auto-generated so a setter is not provided */
	
	
	public int getEmpId() {
		return emp.getEmpid();
	}
	public String getEmpFisrtName() {
		return emp.getEmpfirstname();
	}

	public void setEmpFisrtName(String firstName) {
		emp.setEmpfirstname(firstName);
	}
	public String getEmpLastName() {
		return emp.getEmplastname();
	}
	public void setEmpLastName(String lastName) {
		emp.setEmplastname(lastName);
	}
	public List<Employeelabourchargerate> getEmpLabourChargeRates() {
		return emp.getEmployeeLabourChargeRates();
	}
	public void setEmpLabourChargeRates(Labourchargerate lcr, 
			Timesheetweek tswStart) {
	// need to update the employeelabourchargerate table with the 
	// employees new labourcharge rate, and also put a closing date
	// on the previous employeelabourchargerate entry
	}
	public List<Employeerole> getEmpRoles() {
		return emp.getEmployeeRoles();
	}
	public void addEmpRole(Employeerole role) {
		emp.getEmployeeRoles().add(role);
	}
	public void removeEmpRole(Employeerole role) {
		int indx = emp.getEmployeeRoles().indexOf(role);
		if (indx >= 0) {
			emp.getEmployeeRoles().remove(indx);
		}
	}
	public List<Workpackage> getEmpWorkPackages() {
		return emp.getEmployeesWorkPackages();
	}
	public void addEmpWorkPackage(Workpackage workpackage) {
		emp.getEmployeesWorkPackages().add(workpackage);
	}
	public boolean removeEmpWorkPackage(Workpackage workpackage) {
		int indx = emp.getEmployeesWorkPackages().indexOf(workpackage);
		if (indx >= 0) {
			emp.getEmployeesWorkPackages().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public Employee getEmpSupervisor() {
		List<Employee> supervisors = emp.getEmployeeSupervisors();
		if (supervisors.size() == 1) {
			return supervisors.get(0);
		} else if (supervisors.size() > 1) {
			// some logic required here if the business logic changed
			// to involve more than one supervisor, which is currently
			// not allowed.
			return supervisors.get(0);
		} else {
			// none were found
			return null;
		}
	}
	public boolean addEmpSupervisor(Employee supervisor) {
		int size = emp.getEmployeeSupervisors().size();
		if (size >= 1) {
			return false;
		} else {
			emp.getEmployeeSupervisors().add(supervisor);
			return true;
		}
	}
	public boolean removeEmpSupervisor(Employee supervisor) {
		int indx = emp.getEmployeeSupervisors().indexOf(supervisor);
		if (indx >= 0) {
			emp.getEmployeesWorkPackages().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public Employee getEmpTSApprover() {
		List<Employee> tsApprovers = emp.getTimeSheetApprovers();
		if (tsApprovers.size() == 1) {
			return tsApprovers.get(0);
		} else if (tsApprovers.size() > 1) {
			// some logic required here if the business logic changed
			// to involve more than one tsApprovers, which is currently
			// not allowed.
			return tsApprovers.get(0);
		} else {
			// none were found
			return null;
		}
	}
	public boolean addEmpTSApprover(Employee tsApprover) {
		int size = emp.getTimeSheetApprovers().size();
		if (size >= 1) {
			return false;
		} else {
			emp.getTimeSheetApprovers().add(tsApprover);
			return true;
		}
	}
	public boolean removeEmpTSApprover(Employee tsApprover) {
		int indx = emp.getTimeSheetApprovers().indexOf(tsApprover);
		if (indx >= 0) {
			emp.getTimeSheetApprovers().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public List<Timesheet> getEmpTimeSheets() {
		return emp.getTimeSheets();
	}
	public void setEmpTimeSheets(Timesheet ts) {
		emp.getTimeSheets().add(ts);
	}
	public double getEmpPercentFullTime() {
		return emp.getEmppercentfulltime();
	}
	public void setEmpPercentFullTime(Double percentage) {
		emp.setEmppercentfulltime(percentage);
	}
	public double getEmpAccumFlexTime() {
		return emp.getEmpaccumflextime();
	}
	public void addEmpAccumFlexTime(double timeAdded) {
		double current = emp.getEmpaccumflextime();
		double updated = current + timeAdded;
		emp.setEmpaccumflextime(updated);
	}
	public void removeEmpAccumFlexTime(double timeRemoved) {
		double current = emp.getEmpaccumflextime();
		double updated = current - timeRemoved;
		emp.setEmpaccumflextime(updated);
	}
	public double getEmpAccumVacation() {
		return emp.getEmpaccumvacation();
	}
	public void addEmpAccumVacation(double timeAdded) {
		double current = emp.getEmpaccumvacation();
		double updated = current + timeAdded;
		emp.setEmpaccumvacation(updated);
	}
	public void removeEmpAccumVacation(double timeRemoved) {
		double current = emp.getEmpaccumvacation();
		double updated = current - timeRemoved;
		emp.setEmpaccumvacation(updated);
	}
}
