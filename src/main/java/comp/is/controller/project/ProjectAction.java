package comp.is.controller.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
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
import comp.is.model.project.CurrentWp;
import comp.is.model.project.ProjectPackage;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.model.project.entity.WorkpackageEntity;
import comp.is.view.project.EmployeePickListBean;
import comp.is.view.project.ProjectManagerView;

@Stateful
@SessionScoped
@Local
@Named("projectAction")
public class ProjectAction implements ProjectActionLocal {

    private Package currentP;
    private WorkPackage childWp;
    private ProjectPackage pp;
    private ProjectTree project;
    @PersistenceContext(unitName = "ProjectManager")
    private EntityManager em;
    @Inject
    private ProjectManagerView view;
    @Inject
    EmployeePickListBean pickList;

    public ProjectAction() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#addChild()
     */
    @Override
    public String addChild() {
        System.out.println("Saving: " + childWp.toString());
        // validate
        List<String> msgs = new ArrayList<String>();
        boolean err = false;
        // check for errors
        WorkPackage candidate = new WorkPackage(childWp);
        candidate.setParent(getWpById(currentP.getId()));
        candidate.setProject(pp);

        System.out.println("CANDIDATE " + candidate.getDetails());

        if (!uniqueNum(candidate.getId())) {
            msgs.add("Number is not unique.");
            err = true;
        }

        // }
        // if (wp.getActCost() != null) {
        // if (!wp.getActCost().isEmpty()) {
        // msgs.add("Parent has charges allocated. Parent is a leaf.");
        // err = true;
        // }
        // }
        if (currentP.isOpenForCharges()) {
            msgs.add("Parent is open for charges. Parent is a leaf.");
            err = true;

        }
        if (!candidate.validLengthNum() || !candidate.validPrefixNum()) {

            msgs.add("Invalid Work Package Number");
            err = true;

        }
        if (candidate.getStartDate() != null) {
            if (!validStartDate(candidate, candidate.getStartDate())) {

                msgs.add("Start Date must be later then parent Start Date");
                err = true;
            }
        }

        if (err) {
            msgs.add("New Work Package #" + candidate.getNumber()
                    + " was not saved.");
            view.displayMsgs(msgs);
            return null;
        }
        // persist
        WorkpackageEntity entity = new WorkpackageEntity(candidate);
        try {
            doPersist(entity);
        } catch (Exception ex) {
            msgs.add("DB connection error. New Work Package #"
                    + candidate.getNumber() + " was not saved.");
            view.displayMsgs(msgs);
            return null;
        }
        addWp(candidate);
        view.addChildToTree(candidate.getNumber(),
                (candidate.isRootChild()) ? pp.getId() : candidate.getParent()
                        .getNumber());
        setWp(candidate);
        setChildWp(new WorkPackage());
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#addWp(comp.is.model.project
     * .WorkPackage)
     */
    @Override
    public void addWp(WorkPackage wp) {
        // validate
        project.put(wp);

    }

    private void fillWpMap() {
        Set<WorkpackageEntity> wps = pp.getWorkPackages();
        for (WorkpackageEntity wp : wps) {
            if (wp.getParent() != null & !wp.getId().equalsIgnoreCase(".")) {
                WorkPackage newWp = new WorkPackage(wp);
                project.put(newWp);
            }
        }
    }

    private boolean findAndSetRoot() {
        ProjectEntity pr = null;
        try {
            pr = em.find(ProjectEntity.class, "1000");

        } catch (Exception e) {
            System.out.println("Project not found. " + e.toString());
            return false;
        }
        System.out.println("Project found " + pr.getId());
        pp = new ProjectPackage(pr);

        project = new ProjectTree(pp);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#getChildWp()
     */
    @Override
    @Produces
    @ChildWp
    @Named("childWp")
    @RequestScoped
    public WorkPackage getChildWp() {
        return childWp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#getProject()
     */
    @Override
    public ProjectTree getProject() {
        return project;
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#getWp()
     */
    @Override
    @Produces
    @CurrentWp
    @Named("wp")
    public Package getWp() {
        return currentP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#getWpById(java.lang.String)
     */
    @Override
    public WorkPackage getWpById(String wpNumber) {
        WorkPackage wp = project.get(wpNumber);
        return wp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#initializeProject()
     */
    @Override
    public void initializeProject() {
        System.out
                .println("Post construct start =======================================================");
        if (!findAndSetRoot()) {
            view.displayMsg("Project not found");
            return;
        }
        fillWpMap();
        currentP = new WorkPackage(project.getRoot());
        childWp = new WorkPackage();
        childWp.setParent(currentP);
        view.init();
        System.out
                .println("Post construct end =======================================================");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#setChildWp(comp.is.model
     * .project.WorkPackage)
     */
    @Override
    public void setChildWp(WorkPackage childWp) {
        this.childWp = childWp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#setProject(comp.is.model
     * .project.ProjectTree)
     */
    @Override
    public void setProject(ProjectTree tree) {
        this.project = tree;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#setWp(comp.is.model.project
     * .entity.Package)
     */
    @Override
    public void setWp(Package wp) {
        currentP = wp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#uniqueNum(java.lang.String)
     */
    @Override
    public boolean uniqueNum(String number) {
        return !project.containsKey(number);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#validStartDate(comp.is.
     * model.project.WorkPackage, java.util.Date)
     */
    @Override
    public boolean validStartDate(WorkPackage wp, Date newDate) {
        if (wp.isRootChild()) {
            return !pp.getStartDate().after(newDate);
        }
        if (wp.getParent().getStartDate() == null) {
            return validStartDate(getWpById(wp.getParent().getId()), newDate);
        } else
            return !wp.getParent().getStartDate().after(newDate);
    }

    private void doPersist(WorkpackageEntity entity) {
        em.persist(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#reinit()
     */
    @Override
    public void reinit() {
        setWp(new WorkPackage());
        setChildWp(new WorkPackage());

    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#validParent()
     */
    @Override
    public String validParent() {
        if (currentP.isLowestLevel()) {
            view.displayMsg("Work Package is a leaf");
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#getSourceEmp(comp.is.model
     * .project.WorkPackage)
     */
    @Override
    public ArrayList<Employee> getSourceEmp(WorkPackage wp) {

        ArrayList<Employee> emp = wp.getAvailableStaff();
        if (emp != null) {
            return emp;
        } else {
            return new ArrayList<Employee>(pp.getEmployees());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#getTargetEmp(comp.is.model
     * .project.WorkPackage)
     */
    public ArrayList<Employee> getTargetEmp(WorkPackage wp) {
        Map<Integer, Employee> emp = new Hashtable<Integer, Employee>();
        for (Employee e : wp.getEmployees()) {
            if (!emp.containsKey(e.getId())) {
                emp.put(e.getId(), e);
            }
        }
        if (project.getChildren(wp.getId()) == null) {
            return new ArrayList<Employee>(wp.getEmployees());
        } else {
            for (WorkPackage cwp : project.getChildren(wp.getId())) {
                for (Employee e : getTargetEmp(cwp)) {
                    if (!emp.containsKey(e.getId())) {
                        emp.put(e.getId(), e);
                    }
                }
                // emp.addAll(getTargetEmp(cwp));
                System.out.println("getting targ emp after " + emp);
            }

            return new ArrayList<Employee>(emp.values());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#doMerge()
     */
    @Override
    public String doMerge() {
        System.out.println("Saving " + currentP);
        List<String> msgs = new ArrayList<String>();
        boolean err = false;

        WorkPackage candidate = this.getWpById(currentP.getId());
        // if (!candidate.getId().equalsIgnoreCase(currentP.getId())) {
        // msgs.add("Number update is not allowed");
        // err = true;
        // }
        if (pickList.getRespEng() != null) {
            candidate.setResponsibleEngineer(new EmployeeEntity(pickList
                    .getRespEng()));
        }
        candidate.mereg(currentP);
        Set<Employee> employeesAssigned = new HashSet<Employee>();
        Set<EmployeeEntity> empSet = new HashSet<EmployeeEntity>();
        for (Employee e : pickList.getEmployees().getTarget()) {
            if (!candidate.getParent().getEmployees().contains(e)) {
                employeesAssigned.add(e);
                empSet.add(new EmployeeEntity(e));
            }
        }
        // candidate.setEmployees(employeesAssigned);
        candidate.setEmployeesAssigned(empSet);
        if (candidate.getStartDate() != null) {
            if (!validStartDate(candidate, candidate.getStartDate())) {

                msgs.add("Start Date must be later then parent Start Date");
                err = true;
            }
        }
        if (err) {
            msgs.add("Work Package #" + candidate.getNumber()
                    + " was not updated.");
            view.displayMsgs(msgs);
            setWp(getWpById(currentP.getId()));
            return null;
        }
        try {
            WorkpackageEntity entity = new WorkpackageEntity(candidate);
            em.merge(entity);
            System.out.println("Saving " + candidate + " "
                    + candidate.getStartDate() + "/ "
                    + candidate.getEmployeesAssigned());
            // em.refresh(entity) ;
            project.put(entity.getId(), new WorkPackage(entity));
        } catch (Exception ex) {
            view.displayMsg("Unable to update " + ex.toString());
            setWp(getWpById(currentP.getId()));
            System.out.println("Unable to update " + ex.toString());
        }
        return null;
    }
}
