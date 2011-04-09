package comp.is.controller.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import comp.is.model.admin.Employee;
import comp.is.model.project.LoggedIn;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.view.project.ProjectView;

@Stateful
@SessionScoped
@LocalBean
@Named("addProjectStaffAction")
@DeclareRoles({ "Troy", "2", "3", "4", "5", "6" })
@PermitAll
public class AddProjectStaffAction {
    @PersistenceContext(unitName = "ProjectManager")
    private EntityManager em;

    @Inject
    @LoggedIn
    Employee supervisor;

    @Inject
    ProjectNumberAction projNumManager;
    @Inject
    ProjectView view;

    private List<String> projectNumList;

    private EmployeeEntity[] selectedEmployees;
    private List<ProjectEntity> projList;
    private String targetProj;

    @PostConstruct
    public void getProjects() {

        selectedEmployees = new EmployeeEntity[0];
        System.out.println("Em=" + em);

    }

    public void assignSelectedEmployees() {
        // projList = projNumManager.getProjList();
        System.out.println("target to employee assign" + targetProj);
        // projectNumList = projNumManager.getProjNumList();

        if (targetProj == null) {
            view.displayMsg("Please select a project");
            return;
        }
        if (selectedEmployees.length < 1) {
            view.displayMsg("No employee selected");
            return;
        }
        ProjectEntity proj = em.find(ProjectEntity.class, targetProj);
        if (proj == null) {
            view.displayMsg("Project does not exist");
        }

        proj.getProjectEmployees().addAll(Arrays.asList(selectedEmployees));

        try {
            em.merge(proj);
            view.displayMsg(selectedEmployees.length
                    + ((selectedEmployees.length == 1) ? " Employee "
                            : " Employees added to project " + targetProj));
        } catch (Exception e) {
            view.displayMsg("Cannot persist: " + e.toString());
        }

    }

 
    public EmployeeEntity[] getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(EmployeeEntity[] selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }

    public List<String> getProjList(String query) {
        System.err.println("Number manager " + projNumManager);
        List<String> result = new ArrayList<String>();
        List<String> allProj = projNumManager.getProjNumList();
        if (allProj == null || allProj.isEmpty()) {
            return result;
        }
        for (String id : allProj) {
            if (id.startsWith(query)) {
                result.add(id);
            }
        }
        return result;
    }

    public void setProjList(List<ProjectEntity> projList) {
        this.projList = projList;
    }

    public String getTargetProj() {
        return targetProj;
    }

    public void setTargetProj(String targetProj) {
        this.targetProj = targetProj;
    }

}
