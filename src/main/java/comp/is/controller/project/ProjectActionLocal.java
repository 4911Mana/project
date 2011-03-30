package comp.is.controller.project;

import java.util.ArrayList;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import comp.is.model.admin.Employee;
import comp.is.model.project.ChildWp;
import comp.is.model.project.CurrentWp;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.Package;

public interface ProjectActionLocal {

    public String addChild();

    public void addWp(WorkPackage wp);

    @Produces
    @ChildWp
    @Named("childWp")
    @RequestScoped
    public WorkPackage getChildWp();

    public ProjectTree getProject();

    @Produces
    @CurrentWp
    @Named("wp")
    public Package getWp();

    public WorkPackage getWpById(String wpNumber);

    //public String init();

    public void setChildWp(WorkPackage childWp);

    public void setProject(ProjectTree tree);

    public void setWp(Package wp);

    public boolean uniqueNum(String number);

    public boolean validStartDate(WorkPackage wp, Date newDate);

    public void reinit();

    public String validParent();

    public ArrayList<Employee> getSourceEmp(WorkPackage wp);

    public ArrayList<Employee> getTargetEmp(WorkPackage wp);

    public String doMerge();

    String init(String id);

}