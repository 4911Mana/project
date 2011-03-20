package comp.is.view.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import comp.is.model.admin.Employee;

@Named
@SessionScoped
public class EmployeeTableBean implements Serializable {

    private List<Employee> employees;
    
    private Employee selectedEmployee;
   
    
    public EmployeeTableBean(){
        employees = new ArrayList<Employee>();
        System.out.println("Table bean init " + this);
    }
    public EmployeeTableBean(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}
    
