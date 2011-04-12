package comp.is.controller.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.primefaces.event.SelectEvent;

import comp.is.model.admin.Employee;
import comp.is.model.project.ChildWp;
import comp.is.model.project.LoggedIn;
import comp.is.model.project.Project;
import comp.is.model.project.ProjectPackage;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.EmployeeroleEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.model.project.entity.ProjectbudgetEntity;
import comp.is.model.project.entity.WorkpackageEntity;
import comp.is.model.project.key.EmployeerolePK;
import comp.is.view.project.EmployeePickListBean;
import comp.is.view.project.ProjectCatalogueView;
import comp.is.view.project.ProjectView;

@Stateful
@ConversationScoped
@LocalBean
@Named("catalogueAction")
@DeclareRoles({ "Troy", "Homer", "3", "4", "5", "6" })
@PermitAll
public class ProjectCatalogueAction {

    @Inject
    Conversation conversation;

    @PersistenceContext(unitName = "ProjectManager", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Inject
    ProjectNumberAction numValidator;
    @Inject
    ProjectAction projectAction;

    @Inject
    @LoggedIn
    Employee manager;

    Hashtable<String, ProjectEntity> allProj;

    ProjectPackage currentPP;

    Integer selectedProjId;

    EmployeeEntity currentProjManager;
    EmployeeEntity newProjManager;

    @Inject
    private ProjectCatalogueView view;

    public ProjectCatalogueAction() {
        currentPP = new ProjectPackage();
        currentProjManager = currentPP.getManager();
        newProjManager = currentPP.getManager();
        allProj = new Hashtable<String, ProjectEntity>();
    }

    @PostConstruct
    public void init() {
        conversation.begin();
        for (ProjectEntity p : manager.getSupervisedProjects()) {
            allProj.put(p.getId(), p);
        }
        System.out.println(allProj);
    }

    private boolean currentPPisNew = true;

    public Integer getSelectedProjId() {
        return selectedProjId;
    }

    public void setSelectedProjId(Integer selectedProjId) {
        this.selectedProjId = selectedProjId;
    }

    public EmployeeEntity getNewProjManager() {
        return newProjManager;
    }

    public void setNewProjManager(EmployeeEntity newProjManager) {
        this.newProjManager = newProjManager;
    }

    @Produces
    @Project
    @RequestScoped
    @Named("project")
    public ProjectPackage getCurrentPP() {
        return currentPP;
    }

    public void setCurrentPP(ProjectPackage currentPP) {
        this.currentPP = currentPP;
    }

    public String displayProj() {
        ProjectEntity pr = getProjById(selectedProjId.toString());
        if (pr == null) {
            System.out.println("Null is selected");
            return null;
        }
        ProjectPackage pp = new ProjectPackage(pr);
        System.out.println("Manager " + pp.getManager() + " /"
                + pp.getDescription());
        setCurrentPP(pp);
        currentPPisNew = false;
        if (currentPP.getManager() != null) {
            currentProjManager = currentPP.getManager();
            newProjManager = currentPP.getManager();
        }
        return null;
    }

    public ProjectEntity getProjById(String projNum) {
        if (allProj != null & !allProj.isEmpty()) {
            return allProj.get(projNum);
        }
        return null;
    }

    public String doPersist() {
        System.out.println("Adding " + currentPP);
        ProjectEntity entity = new ProjectEntity(currentPP);

        try {

            ProjectEntity proj = getProjById(entity.getId());

            if (entity.getProjectBudget() != null) {
                entity.getProjectBudget().setProject(entity);
                entity.getProjectBudget().setProjid(entity.getId());
                entity.getProjectBudget()
                        .setInitBudget(currentPP.getInBudget());

            }
            if (proj != null & !currentPPisNew) {
                // if (newProjManager != null) {
                // if (currentProjManager.getId() != newProjManager.getId()) {
                // ask for confirmation
                // entity.setProjectBudget(null);
                addProjManager(entity);
                EmployeeroleEntity role = currentPP.findManager(currentProjManager);
                // System.out.println("Role to remove: " +
                // role.getEmployee().getId());
                // for(EmployeeroleEntity r : entity.getEmployeeRoles()){
                // System.out.println("All roles: " +
                // entity.getEmployeeRoles());
                // }

                // role = em.find(EmployeeroleEntity.class, role.getId());
                // System.out.println("Removing previouse manager "
                // + role.getId().getEmpid()
                // + "/"+ role.getId().getProjid()
                // + "/"+ role.getId().getRoleid());
                // em.remove(role);
                System.out.println("Prev manager " + role);

                entity.getEmployeeRoles().remove(role);
                for (EmployeeroleEntity r : entity.getEmployeeRoles()) {
                    System.out.println("One role removed: "
                            + r.getId());
                }
                //
                // }
                // }

                em.merge(entity);
                em.flush();
                System.out.println("Here we are  ");
                ProjectbudgetEntity budget = entity.getProjectBudget();
                if (budget != null) {
                    if (em.find(ProjectbudgetEntity.class, budget.getProjid()) != null) {
                        em.merge(budget);
                    } else {
                        em.persist(budget);
                    }
                }
                allProj.put(entity.getId(), entity);
                view.displayMsg(entity.getId() + " Successfully updated");
                return null;
            } else {

                if (numValidator.projectIsUnique(entity.getId())) {
                    // ProjectbudgetEntity budget = entity.getProjectBudget();
                    entity.setProjectBudget(null);
                    em.persist(entity);
                    em.flush();

                    // if (budget != null) {
                    //
                    // budget.setProjid(entity.getId());
                    // budget.setInitBudget(currentPP.getInBudget());
                    // em.persist(budget);
                    //
                    // }
                    addSupervisorToNewProj(entity);
                    addProjManager(entity);

                    System.out.println("Adding " + currentPP.getId());
                    WorkpackageEntity wp = new WorkpackageEntity();
                    wp.setId(".");
                    wp.setParentId(".");
                    wp.setProjid(entity.getId());
                    System.out.println("Adding " + wp.getId());
                    entity.getWorkPackages().add(wp);
                    em.persist(wp);
                    this.allProj.put(entity.getId(), entity);
                    view.displayMsg(entity.getId() + " Successfully created");
                    return null;
                } else {
                    view.displayMsg(entity.getId()
                            + " project number is not unique");
                    return null;
                }
            }

        } catch (Exception ex) {
            view.displayMsg("Unable to create " + ex.toString());
            System.out.println("Unable to create " + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    public void initNew() {
        currentPP = new ProjectPackage();
        currentPPisNew = true;
        newProjManager = null;
    }

    private void addSupervisorToNewProj(ProjectEntity pp) {
        if (manager.getId() != 0) {
            System.out.println("Supervisor " + manager.getId());
            EmployeeroleEntity role = new EmployeeroleEntity();
            EmployeerolePK id = new EmployeerolePK();
            id.setEmpid(manager.getId());
            id.setProjid(pp.getId());
            id.setRoleid(7);
            role.setId(id);
            pp.getEmployeeRoles().add(role);
        }
    }

    private void addProjManager(ProjectEntity pp) {
        if (newProjManager == null || newProjManager.getId() == 0) {
            return;
        }

        EmployeeroleEntity role = new EmployeeroleEntity();
        EmployeerolePK id = new EmployeerolePK();
        role.setEmployee(newProjManager);
        id.setEmpid(newProjManager.getId());
        id.setProjid(pp.getId());
        id.setRoleid(1);
        role.setId(id);
        pp.getEmployeeRoles().add(role);
        System.out.println("New manager  " + role);
    }

    public ArrayList<ProjectEntity> getAllProj() {
        return new ArrayList<ProjectEntity>(allProj.values());
    }

    public void onProjManagerRawSelect(SelectEvent event) {
        newProjManager = (EmployeeEntity) event.getObject();
        System.out.println("New Proj Manager "
                + ((newProjManager == null) ? "null" : newProjManager.getId()));
    }

    public String openTree() {
        conversation.end();
        return projectAction.init(currentPP.getId());
    }
}
