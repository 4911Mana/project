package comp.is.view.project;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import comp.is.model.admin.Employee;

@Named
@RequestScoped
public class EmployeePickListBean {

    private DualListModel<Employee> employees;

    
    public EmployeePickListBean() {
        //Employees
        List<Employee> source = new ArrayList<Employee>();
        List<Employee> target = new ArrayList<Employee>();

        Employee one = new Employee();
        one.setFirstName("Joe");
        one.setLastName("Doe");
        Employee two = new Employee();
        two.setFirstName("Joe");
        two.setLastName("Doe");
        source.add(one);
        source.add(two);
        

        employees = new DualListModel<Employee>(source, target);

    }

    public DualListModel<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(DualListModel<Employee> employees) {
        this.employees = employees;
    }

    
}
                    