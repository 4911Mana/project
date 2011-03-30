package comp.is.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
		//initEmp(empId);
	}
	public void addEmpAccumFlexTime(double timeAdded) {
		double current = emp.getAccumflextime();
		double updated = current + timeAdded;
		emp.setAccumflextime(updated);
	}
	
	public void addEmpAccumVacation(double timeAdded) {
		double current = emp.getAccumvacation();
		double updated = current + timeAdded;
		emp.setAccumvacation(updated);
	}
	public void addEmpRole(EmployeeroleEntity role) {
		emp.getRoles().add(role);
	}
	public boolean addEmpSupervisor(EmployeeEntity supervisor) {
		int size = emp.getSupervisors().size();
		if (size >= 1) {
			return false;
		} else {
			emp.getSupervisors().add(supervisor);
			return true;
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
	public void addEmpWorkPackage(WorkpackageEntity workpackage) {
		emp.getWorkPackages().add(workpackage);
	}
	public double getAccumFlexTime() {
		return emp.getAccumflextime();
	}
	public double getEmpAccumVacation() {
		return emp.getAccumvacation();
	}
	public String getEmpFisrtName() {
		return emp.getFirstname();
	}
	/** note that empId is auto-generated so a setter is not provided */
	
	
	public int getEmpId() {
		return emp.getId();
	}
	public Set<EmployeelabourchargerateEntity> getEmpLabourChargeRates() {
		return emp.getLabourChargeRates();
	}
	public String getEmpLastName() {
		return emp.getLastname();
	}
	public double getEmpPercentFullTime() {
		return emp.getPercentfulltime();
	}
	public List<EmployeeroleEntity> getEmpRoles() {
		return new ArrayList<EmployeeroleEntity>(emp.getRoles());
	}
	public EmployeeEntity getEmpSupervisor() {
		List<EmployeeEntity> supervisors = new ArrayList(emp.getSupervisors());
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
	public Set<TimesheetEntity> getEmpTimeSheets() {
		return emp.getTimeSheets();
	}
	public EmployeeEntity getEmpTSApprover() {
		Set<EmployeeEntity> tsApprovers = emp.getTimeSheetApprovers();
		if (tsApprovers.size() == 1) {
			return (EmployeeEntity) tsApprovers.toArray()[0];
		} else if (tsApprovers.size() > 1) {
			// some logic required here if the business logic changed
			// to involve more than one tsApprovers, which is currently
			// not allowed.
			return (EmployeeEntity) tsApprovers.toArray()[0];
		} else {
			// none were found
			return null;
		}
	}
	public Set<WorkpackageEntity> getEmpWorkPackages() {
		return emp.getWorkPackages();
	}
	public void initEmp(int empId) {
		/* initialize the employee from the database */
		emp = em.find(EmployeeEntity.class, empId);
	}
	public void persistAll() {
		
	}
	public void removeEmpAccumFlexTime(double timeRemoved) {
		double current = emp.getAccumflextime();
		double updated = current - timeRemoved;
		emp.setAccumflextime(updated);
	}
	public void removeEmpAccumVacation(double timeRemoved) {
		double current = emp.getAccumvacation();
		double updated = current - timeRemoved;
		emp.setAccumvacation(updated);
	}
	public void removeEmpRole(EmployeeroleEntity role) {
		boolean indx = emp.getRoles().contains(role);
		if (indx) {
			emp.getRoles().remove(indx);
		}
	}
	public boolean removeEmpSupervisor(EmployeeEntity supervisor) {
		boolean indx = emp.getSupervisors().contains(supervisor);
		if (indx) {
			emp.getWorkPackages().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public boolean removeEmpTSApprover(EmployeeEntity tsApprover) {
		boolean indx = emp.getTimeSheetApprovers().contains(tsApprover);
		if (indx) {
			emp.getTimeSheetApprovers().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public boolean removeEmpWorkPackage(WorkpackageEntity workpackage) {
		boolean indx = emp.getWorkPackages().contains(workpackage);
		if (indx) {
			emp.getWorkPackages().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public void setFisrtName(String firstName) {
		emp.setFirstname(firstName);
	}
	public void setEmpLabourChargeRates(LabourchargerateEntity lcr, 
			TimesheetweekEntity tswStart) {
	// need to update the employeelabourchargerate table with the 
	// employees new labourcharge rate, and also put a closing date
	// on the previous employeelabourchargerate entry
	}
	public void setEmpLastName(String lastName) {
		emp.setLastname(lastName);
	}
	public void setEmpPercentFullTime(Double percentage) {
		emp.setPercentfulltime(percentage);
	}
	public void setEmpTimeSheets(TimesheetEntity ts) {
		emp.getTimeSheets().add(ts);
	}
}
