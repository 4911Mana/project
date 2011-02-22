package comp.is.view.project;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp.is.controller.project.ProjectAction;
import comp.is.controller.project.entities.Employee;
import comp.is.controller.project.entities.Project;

@Named
@SessionScoped
public class EmployeeTableBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject ProjectAction prAction;
	
    private List<Employee> employees;
    
    private Employee selectedEmployee;    

    public EmployeeTableBean() {
        
    }
    @PostConstruct
    private void init() {
    	prAction.initializeProject("PR002");
    	employees = prAction.getProjectEmployees();
    }
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }
    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
    
