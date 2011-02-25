package comp.is.model.admin;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import comp.is.model.project.WorkPackage;



@Named
@SessionScoped
public class ProjectManager extends Employee implements Serializable{
    private List<Employee> assignedStaff;
    private static List<WorkPackage> projects;
    
//    static {
//        projects.add(new WorkPackage("advn"));
//        projects.add(new WorkPackage("a89n"));
//        projects.add(new WorkPackage("9dvn"));
//        projects.add(new WorkPackage("4dvn"));
//        projects.add(new WorkPackage("ad99"));
//    }

    public void setAssignedStaff(List<Employee> assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public List<Employee> getAssignedStaff() {
        return assignedStaff;
    }

    public void setProjects(List<WorkPackage> projects) {
        ProjectManager.projects = projects;
    }

    public List<WorkPackage> getProjects() {
        return projects;
    }
}
