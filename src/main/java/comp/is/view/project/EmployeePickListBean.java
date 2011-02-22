package comp.is.view.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.model.DualListModel;

import comp.is.controller.project.ProjectAction;
import comp.is.controller.project.entities.Employee;

@Named
@RequestScoped
public class EmployeePickListBean {

	@PersistenceContext(name="ProjectManager")
	private EntityManager em;
    private DualListModel<Employee> employees;
    @Inject ProjectAction pra;
    
    public EmployeePickListBean() {
        //Employees
       

    }

    @PostActivate
    @PostConstruct
    private void init() {
    	 List<Employee> target = new ArrayList<Employee>();     
     	pra.initializeProject("PR002");
     	List<Employee>projEmps = pra.getProjectEmployees();
         employees = new DualListModel<Employee>(projEmps, target);
         System.err.println("size: " + projEmps.size());
    }
    public DualListModel<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(DualListModel<Employee> employees) {
        this.employees = employees;
    }

    
}
                    