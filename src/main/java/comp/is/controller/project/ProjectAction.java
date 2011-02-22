package comp.is.controller.project;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp.is.controller.project.entities.Employee;
import comp.is.controller.project.entities.Employeerole;
import comp.is.controller.project.entities.Project;
import comp.is.controller.project.entities.Projectbudget;
import comp.is.controller.project.entities.Projectsummary;
import comp.is.controller.project.entities.Ratesheet;
import comp.is.controller.project.entities.Timesheetentry;
import comp.is.controller.project.entities.Workpackage;
import comp.is.model.project.ProjectTree;

@LocalBean
@Stateful
@SessionScoped
@Named("projectAction")
public class ProjectAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="ProjectManager")
	private EntityManager em;
	
	private Project pr;
	private Map<String, Workpackage> wpMap;
	private Workpackage root;
	
	public ProjectAction() {
	}
	
    public void initializeProject(String projId) {
    	pr = em.find(Project.class, "PR002");
    	findAndSetRoot();
    	wpMap = new Hashtable<String, Workpackage>();
    	fillWpMap();
    }
    private void findAndSetRoot() {
    	/** note that only one workpackage has a null parent */
    	//Project p = em.find(Project.class, pr.getProjid());
    	List<Workpackage> wps = pr.getWorkPackages();
    	for (Workpackage wp : wps) {
    		if (wp.getWpParent() == null) {
    			setRoot(wp);
    			return;
    		}
    	}
    }
    private void fillWpMap() {
    	//Project p = em.find(Project.class, pr.getProjid());
    	List<Workpackage> wps = pr.getWorkPackages();
    	for (Workpackage wp : wps) {
    			wpMap.put(wp.getId().getWpid(), wp);
    	}
    }
    public ProjectTree generateWpIndexes() {
        ProjectTree projTree = new ProjectTree(root.getId().getWpid());
        for (Workpackage wp : wpMap.values()) {
        	if (wp.getWpParent() == null) {
        		projTree.setRoot(wp.getId().getWpid());
        		projTree.put(wp.getId().getWpid(), "root");
        	} else {
        		projTree.put(wp.getId().getWpid(), wp.getWpParent().getId().getWpid());
        	}
        }
        for (Object s : projTree.entrySet().toArray()) {
        	System.err.println("tree entry: " + s.toString());
        }
        projTree.getChildren("root");
        return projTree;
    }
    
    
	public String getProjId() {
		return pr.getProjid();
	}
	public void setProjId(String projId) {
		pr.setProjid(projId);
	}
	public String getProjName() {
		return pr.getProjname();
	}
	public void setProjName(String name) {
		pr.setProjname(name);
	}
	public List<Employee> getProjectEmployees() {
		return pr.getProjectEmployees();
	}
	public void addProjectEmployees(Employee emp) {
		pr.getProjectEmployees().add(emp);
	}
	public boolean removeProjectEmployees(Employee emp) {
		int indx = pr.getProjectEmployees().indexOf(emp);
		if (indx >= 0) {
			pr.getProjectEmployees().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public List<Employeerole> getEmployeeRoles() {
		return pr.getEmployeeRoles();
	}
	public void addEmployeeRoles(Employeerole role) {
		pr.getEmployeeRoles().add(role);
	}
	public boolean removeEmployeeRoles(Employeerole role) {
		int indx = pr.getEmployeeRoles().indexOf(role);
		if (indx >= 0) {
			pr.getEmployeeRoles().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public List<Ratesheet> getProjRateSheets() {
		return pr.getRateSheets();
	}
	public void addProjRateSheet(Ratesheet rs) {
		pr.getRateSheets().add(rs);
	}
	public boolean removeProjRateSheet(Ratesheet rs) {
		int indx = pr.getRateSheets().indexOf(rs);
		if (indx >= 0) {
			pr.getRateSheets().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public List<Timesheetentry> getTimeSheetEntries() {
		return pr.getTimeSheetEntries();
	}
	public List<Workpackage> getProjWorkPackages() {
		return pr.getWorkPackages();
	}
	
	public String getProjCustomer() {
		return pr.getProjcustomer();
	}
	public void setProjCustomer(String customer) {
		pr.setProjcustomer(customer);
	}
	public Projectbudget getProjectBudget() {
		return pr.getProjectBudget();
	}
	public void setProjectBudget(Projectbudget budget) {
		pr.setProjectBudget(budget);
	}
	public List<Projectsummary> getProjectSummaries() {
		return pr.getProjectSummaries();
	}
	public void addProjectSummary(Projectsummary summary) {
		pr.getProjectSummaries().add(summary);
	}
	public boolean removeProjectSummary(Projectsummary summary) {
		int indx = pr.getProjectSummaries().indexOf(summary);
		if (indx >= 0) {
			pr.getProjectSummaries().remove(indx);
			return true;
		} else {
			return false;
		}
	}
	public double getProjLabourRateMarkup() {
		return pr.getProjlabourratemarkup();
	}
	public void setProjLabourRateMarkup(double labrate) {
		pr.setProjlabourratemarkup(labrate);
	}
    public Workpackage getRoot() {
        return root;
    }

    public void setRoot(Workpackage newroot) {
        root = newroot;
    }
    public Workpackage getWpById(String wpNumber) {
        System.out.println("Get by Id: " + wpNumber+ " / " + (wpMap.containsKey(wpNumber) ? "founds ok" : "not found"));
        Workpackage wp = wpMap.get(wpNumber);
        System.out.println("Getting from wpmap: " +  ((wp == null)? "null" : wp.toString()));
        return wpMap.get(wpNumber);

    }
 
    public void addWp(Workpackage wp) {
        // validate
        System.out.println(wp.toString());
        wpMap.put(wp.getId().getWpid(), wp);
        System.out.println("Put and now Getting from wpmap: " + wpMap.get(wp.getId().getWpid()).toString());
        System.out.println((wpMap.get(wp.getId().getWpid())== null)? "not found" : "finds ok");
        
     }
}
