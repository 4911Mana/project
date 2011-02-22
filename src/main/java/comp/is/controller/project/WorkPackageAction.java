package comp.is.controller.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp.is.controller.project.entities.Employee;
import comp.is.controller.project.entities.Timesheetentry;
import comp.is.controller.project.entities.Workpackage;
import comp.is.controller.project.entities.Workpackagestatusreport;
import comp.is.controller.project.entities.keys.WorkpackagePK;

/**
 * Session Bean implementation class WorkPackageAction
 */
@Stateful
@LocalBean
@SessionScoped
@Named("wpAction")
public class WorkPackageAction implements WorkPackageLocal, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="ProjectManager")
	private EntityManager em;
	
	private Workpackage wp;

	public WorkPackageAction() {
		
	}
	public WorkPackageAction(WorkpackagePK wpPK) {
		init(wpPK);
	}
	
	public void init(WorkpackagePK wpPK) {
		wp = em.find(Workpackage.class, wpPK);
	}	
	@Override
	@PostConstruct
	public void reinit() {
    	WorkpackagePK wppk = new WorkpackagePK();
    	wppk.setProjid("PR002");
    	wppk.setWpid("aa");
    	init(wppk);
		//em.refresh(wp);
	}
	public Workpackage getCurrentWorkPackage() {
		return wp;
	}
	public void setCurrentWorkPackage(Workpackage wpkg) {
		if (wpkg != null) {
			wp = wpkg;
		}
	}
	public void initialize(WorkpackagePK wpPK) {
		wp = em.find(Workpackage.class, wpPK);
	}
	/** returns the primary key, which holds the wpid an the projid */
	public WorkpackagePK getWpPrimaryKey() {
		return wp.getId();
	}
	public void setWpPrimaryKey(String wpId, String projId) {
		WorkpackagePK wpPK = new WorkpackagePK();
		wpPK.setWpid(wpId);
		wpPK.setProjid(projId);
		wp.setId(wpPK);
	}
	public String getWpNumber() {
		return getWpPrimaryKey().getWpid();
	}
	public void setWpNumber(String wpId) {
		wp.getId().setWpid(wpId);
	}
	public String getWpProjNumber() {
		return getWpPrimaryKey().getProjid();
	}
	public void setWpProjNumber(String projId) {
		wp.getId().setProjid(projId);
	}
	public Workpackage getWpParent() {
		return wp.getWpParent();
	}
	public void setWpParent(Workpackage parent) {
		wp.setWpParent(parent);
	}
	public List<Timesheetentry> getTimesheetEntries() {
		return wp.getTimeSheetEntries();
	}
	public List<Workpackage> getWpChildren() {
		return wp.getWpChildren();
	}
	public void addWpChild(Workpackage wpChild) {
		wp.getWpChildren().add(wpChild);
	}
	public boolean removeWpChild(Workpackage wpChild) {
		int indx = wp.getWpChildren().indexOf(wpChild);
		if (indx >= 0) {
			wp.getWpChildren().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public List<Employee> getWpEmployeesAssigned() {
		return wp.getWpEmployeesAssigned();
	}
	public void addWpEmployeeAssigned(Employee emp) {
		wp.getWpEmployeesAssigned().add(emp);
	}
	public boolean removeWpEmployeeAssigned(Employee emp) {
		int indx = wp.getWpEmployeesAssigned().indexOf(emp);
		if (indx >= 0) {
			wp.getWpEmployeesAssigned().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public Employee getWpResponsibleEngineer() {
		return wp.getWpResponsibleEngineer();
	}
	public void setWpResponsibleEngineer(Employee emp) {
		wp.setWpResponsibleEngineer(emp);
	}
	public String getWpInputs() {
		return wp.getWpinputs();
	}
	public void setWpInputs(String inputs) {
		wp.setWpinputs(inputs);
	}
	public Date getWpIssueDate() {
		return wp.getWpissuedate();
	}
	public void setWpIssueDate(Date date) {
		wp.setWpissuedate(date);
	}
	public String getWpOutputs() {
		return wp.getWpoutputs();
	}
	public String getWpActivities() {
		return wp.getWpactivities();
	}
	public void setWpActivities(String activities) {
		wp.setWpactivities(activities);
	}
	public void setWpOutputs(String outputs) {
		wp.setWpoutputs(outputs);
	}
	public String getWpPurpose() {
		return wp.getWppurpose();
	}
	public void setWpPurpose(String purpose) {
		wp.setWppurpose(purpose);
	}
	public boolean getWpStatus() {
		return (wp.getWpstatus().equalsIgnoreCase("Ongoing")) ? true : false;
	}
	public void setWpStatus(String status) {
		wp.setWpstatus(status);
	}
	public List<Workpackagestatusreport> getWpStatusReports() {
		return wp.getWpStatusReports();
	}
	public void addWpStatusReport(Workpackagestatusreport wpsr) {
		wp.getWpStatusReports().add(wpsr);
	}
	public boolean removeWpStatusReport(Workpackagestatusreport wpsr) {
		int indx = wp.getWpStatusReports().indexOf(wpsr);
		if (indx >= 0) {
			wp.getWpStatusReports().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public String getWpTitle() {
		return wp.getWptitle();
	}
	public void setWpTitle(String title) {
		wp.setWptitle(title);
	}	
	public boolean persist() {
		return true;
	}
	public boolean delete() {
		return true;
	}
	public boolean refresh() {
		return true;
	}
}