package comp.is.controller.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp.is.model.admin.Employee;
import comp.is.model.project.ChildWp;
import comp.is.model.project.LoggedIn;
import comp.is.model.project.Project;
import comp.is.model.project.ProjectPackage;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.view.project.EmployeePickListBean;
import comp.is.view.project.ProjectCatalogueView;
import comp.is.view.project.ProjectView;

@Stateful
@SessionScoped
@LocalBean
@Named("catalogueAction")
@DeclareRoles({ "Troy", "2", "3", "4" , "5", "6" })
@PermitAll
public class ProjectCatalogueAction implements Serializable{

    @PersistenceContext(unitName = "ProjectManager")
    private EntityManager em;
    
    @Inject @LoggedIn
    Employee manager;
    
    
    
    ArrayList<ProjectEntity> allProj;
    
    ProjectPackage currentPP;
    
    Integer selectedProj;
    Employee[] selectedEmp;
    
    @Inject
    private ProjectCatalogueView view;

    public ProjectCatalogueAction() {
        currentPP = new ProjectPackage();
        //selectedEmp = new Employee[100];
        selectedEmp = new Employee[0];
    }

   @PostConstruct
   public void init(){
       allProj = manager.getAllProjects();
   }
 
    public Employee getManager() {
        return manager;
    }
    
    public void setManager(Employee imanager) {
        manager = imanager;
    }

    @Produces
    @Project
    @Named("project")
    public ProjectPackage getCurrentPP() {
        return currentPP;
    }

    public void setCurrentPP(ProjectPackage currentPP) {
        this.currentPP = currentPP;
    }
    
    public String displayProj(){
        ProjectEntity pr = getProjById(selectedProj.toString());
        ProjectPackage pp = new ProjectPackage(pr);
        setCurrentPP(pp);
        
        if(pp.getEmployees() != null){
        this.setSelectedEmp(pp.getEmployees().toArray(selectedEmp));
        }
        System.out.println("Project is set " + pp.getId());
        return null;
    }
    
    public ProjectEntity getProjById(String projNumber) {
        if(allProj != null & !allProj.isEmpty()){
            for(ProjectEntity entity : allProj){
                if(entity.getId().equalsIgnoreCase(projNumber)){
                    return entity;
                }
            }
        }
        return null;
    }

    public Integer getSelectedProj() {
        return selectedProj;
    }

    public void setSelectedProj(Integer selectedProj) {
        this.selectedProj = selectedProj;
    }

    public Employee[] getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Employee[] selectedEmp) {
        this.selectedEmp = new Employee[selectedEmp.length];
        this.selectedEmp = selectedEmp;
    }
    
    
    
}
