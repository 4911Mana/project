package comp.is.view.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import comp.is.model.admin.Employee;

@Named
@RequestScoped
public class EmployeePickListBean implements Serializable{

    private DualListModel<Employee> employees;
    public EmployeePickListBean(){
        List<Employee> source = new ArrayList<Employee>();
        List<Employee> target = new ArrayList<Employee>();
        Employee a = new Employee();
        a.setEmpid(1234);
        a.setEmplastname("Rerer");
        a.setEmpfirstname("Tyu");
        source.add(a);
        employees = new DualListModel<Employee>(source, target);
    }

    public EmployeePickListBean(List<Employee> source, List<Employee> target) {
        employees = new DualListModel<Employee>(source, target);
    }

    public DualListModel<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(DualListModel<Employee> employees) {
        this.employees = employees;
    }

}
