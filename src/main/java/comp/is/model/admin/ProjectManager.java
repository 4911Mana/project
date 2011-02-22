package comp.is.model.admin;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import comp.is.controller.project.entities.Employee;
import comp.is.controller.project.entities.Workpackage;

@Named
@SessionScoped
public class ProjectManager extends Employee implements Serializable{
    private List<Employee> assignedStaff;    

    public void setAssignedStaff(List<Employee> assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public List<Employee> getAssignedStaff() {
        return assignedStaff;
    }

}
