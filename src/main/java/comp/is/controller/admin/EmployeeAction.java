package comp.is.controller.admin;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.EmployeelabourchargerateEntity;
import comp.is.model.project.entity.EmployeeroleEntity;
import comp.is.model.project.entity.LabourchargerateEntity;
import comp.is.model.project.entity.TimesheetEntity;
import comp.is.model.project.entity.TimesheetweekEntity;
import comp.is.model.project.entity.WorkpackageEntity;

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
	
	private EmployeeEntity emp;
	
	public EmployeeAction() {
		
	}
	public EmployeeAction(int empId) {
		initEmp(empId);
	}
	public void initEmp(int empId) {
		/* initialize the employee from the database */
		emp = em.find(EmployeeEntity.class, empId);
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
	public List<EmployeelabourchargerateEntity> getEmpLabourChargeRates() {
		return emp.getEmployeeLabourChargeRates();
	}
	public void setEmpLabourChargeRates(LabourchargerateEntity lcr, 
			TimesheetweekEntity tswStart) {
	// need to update the employeelabourchargerate table with the 
	// employees new labourcharge rate, and also put a closing date
	// on the previous employeelabourchargerate entry
	}
	public List<EmployeeroleEntity> getEmpRoles() {
		return emp.getEmployeeRoles();
	}
	public void addEmpRole(EmployeeroleEntity role) {
		emp.getEmployeeRoles().add(role);
	}
	public void removeEmpRole(EmployeeroleEntity role) {
		int indx = emp.getEmployeeRoles().indexOf(role);
		if (indx >= 0) {
			emp.getEmployeeRoles().remove(indx);
		}
	}
	public List<WorkpackageEntity> getEmpWorkPackages() {
		return emp.getEmployeesWorkPackages();
	}
	public void addEmpWorkPackage(WorkpackageEntity workpackage) {
		emp.getEmployeesWorkPackages().add(workpackage);
	}
	public boolean removeEmpWorkPackage(WorkpackageEntity workpackage) {
		int indx = emp.getEmployeesWorkPackages().indexOf(workpackage);
		if (indx >= 0) {
			emp.getEmployeesWorkPackages().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public EmployeeEntity getEmpSupervisor() {
		List<EmployeeEntity> supervisors = emp.getEmployeeSupervisors();
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
	public boolean addEmpSupervisor(EmployeeEntity supervisor) {
		int size = emp.getEmployeeSupervisors().size();
		if (size >= 1) {
			return false;
		} else {
			emp.getEmployeeSupervisors().add(supervisor);
			return true;
		}
	}
	public boolean removeEmpSupervisor(EmployeeEntity supervisor) {
		int indx = emp.getEmployeeSupervisors().indexOf(supervisor);
		if (indx >= 0) {
			emp.getEmployeesWorkPackages().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public EmployeeEntity getEmpTSApprover() {
		List<EmployeeEntity> tsApprovers = emp.getTimeSheetApprovers();
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
	public boolean addEmpTSApprover(EmployeeEntity tsApprover) {
		int size = emp.getTimeSheetApprovers().size();
		if (size >= 1) {
			return false;
		} else {
			emp.getTimeSheetApprovers().add(tsApprover);
			return true;
		}
	}
	public boolean removeEmpTSApprover(EmployeeEntity tsApprover) {
		int indx = emp.getTimeSheetApprovers().indexOf(tsApprover);
		if (indx >= 0) {
			emp.getTimeSheetApprovers().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public List<TimesheetEntity> getEmpTimeSheets() {
		return emp.getTimeSheets();
	}
	public void setEmpTimeSheets(TimesheetEntity ts) {
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
