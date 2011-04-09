package comp.is.controller.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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
import javax.persistence.Query;

import comp.is.model.admin.Employee;
import comp.is.model.admin.LabourGrade;
import comp.is.model.project.ChildWp;
import comp.is.model.project.CurrentWp;
import comp.is.model.project.ProjectPackage;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.LabourchargerateEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.model.project.entity.WorkPackageBudgetEntity;
import comp.is.model.project.entity.WorkpackageEntity;
import comp.is.view.project.EmployeePickListBean;
import comp.is.view.project.ProjectView;

@Stateful
@SessionScoped
@LocalBean
@Named("projectAction")
@DeclareRoles({ "Troy", "2", "3", "4", "5", "6" })
@PermitAll
public class ProjectAction {

    final String RATE_SQL = "SELECT r FROM LabourchargerateEntity r WHERE r.rateclassid = ";

    private Package currentP;
    private WorkPackage childWp;
    private ProjectPackage pp;
    private ProjectTree project;
    private Hashtable<String, LabourchargerateEntity> rates;
    private final List<String> sql;

    @PersistenceContext(unitName = "ProjectManager")
    private EntityManager em;
    @Inject
    private ProjectView view;
    @Inject
    private ProjectCatalogueAction catalogue;
    @Inject
    private EmployeePickListBean pickList;

    public ProjectAction() {
        currentP = new WorkPackage();
        childWp = new WorkPackage();
        rates = new Hashtable<String, LabourchargerateEntity>();
        sql = new ArrayList<String>();

    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#addChild()
     */

    public String addChild() {
        System.out.println("Saving: " + childWp.toString());
        // validate
        List<String> msgs = new ArrayList<String>();
        boolean err = false;
        // check for errors
        WorkPackage candidate = new WorkPackage(childWp);
        // if(currentP.is)
        candidate.setParent(currentP);
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
            msgs.add("Chils cannot be added: Parent is closed.");
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
                    + candidate.getNumber() + " was not saved." + ex.toString());
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

    public void addWp(WorkPackage wp) {
        // validate
        project.put(wp);

    }

    private void fillWpMap() {
        Set<WorkpackageEntity> wps = pp.getWorkPackages();
        for (WorkpackageEntity wp : wps) {
            if (wp.getParent() != null & !wp.getId().equalsIgnoreCase(".")) {
                WorkPackage newWp = new WorkPackage(wp);
                newWp.setRates(getEffectiveRatesForDate(newWp.getStartDate()));
                newWp.initAccumulatedBudget();
                newWp.initPlannedBudget();
                newWp.PlannedBudgetInit();
                project.put(newWp);
            }
        }
    }

    private boolean findAndSetRoot(String id) {
        ProjectEntity pr = null;
        try {
            pr = em.find(ProjectEntity.class, id);

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

    public ProjectTree getProject() {
        return project;
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#getWp()
     */

    @Produces
    @CurrentWp
    @Named("wp")
    @RequestScoped
    public Package getWp() {
        return currentP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#getWpById(java.lang.String)
     */

    public WorkPackage getWpById(String wpNumber) {
        WorkPackage wp = project.get(wpNumber);
        return wp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#initializeProject()
     */

    public String init(String id) {
        if (id == null) {
            System.err.println(" Project Id is null");
            return "failure";
        }
        view.resetTabs();
        if (!findAndSetRoot(id)) {
            view.displayMsg("Project not found");
            return "failure";
        }
        fillWpMap();
        currentP = new WorkPackage(project.getRoot());
        childWp = new WorkPackage();
        childWp.setParent(currentP);
        view.init();
        return "success";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#setChildWp(comp.is.model
     * .project.WorkPackage)
     */

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

    public void setWp(Package wp) {
        currentP = wp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * comp.is.controller.project.ProjectActionLocal#uniqueNum(java.lang.String)
     */

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

    public boolean validStartDate(WorkPackage wp, Date newDate) {

        if (wp.isRootChild()) {
            if (pp.getStartDate() == null)
                return true;
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

    public void reinit() {
        setWp(new WorkPackage());
        setChildWp(new WorkPackage());

    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#validParent()
     */

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
        Set<Employee> emp = new HashSet<Employee>();
//        Map<Integer, Employee> emp = new Hashtable<Integer, Employee>();
//        for (Employee e : wp.getEmployees()) {
//            if (!emp.containsKey(e.getId())) {
//                emp.put(e.getId(), e);
//            }
//        }
        if (project.getChildren(wp.getId()) == null) {
            return new ArrayList<Employee>(wp.getEmployees());
        } else {
            for (WorkPackage cwp : project.getChildren(wp.getId())) {
//                for (Employee e : getTargetEmp(cwp)) {
//                    if (!emp.containsKey(e.getId())) {
//                        emp.put(e.getId(), e);
//                    }
//                }
                 emp.addAll(getTargetEmp(cwp));
            }
            System.err
                    .println("P1 "
                            + getEffectiveRatesForDate(Calendar.getInstance()
                                    .getTime()));
            return new ArrayList<Employee>(emp);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#doMerge()
     */

    public String doMerge() {
        System.out.println("Saving " + currentP);
        List<String> msgs = new ArrayList<String>();
        boolean err = false;

        WorkPackage candidate = this.getWpById(currentP.getId());
        candidate.flushPlannedBudget();
 
        WorkpackageEntity entity = null;
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
            entity = new WorkpackageEntity(candidate);
            em.merge(entity);
            System.out.println("Saving " + candidate + " "
                    + candidate.getStartDate() + "/ "
                    + candidate.getEmployeesAssigned());
            // em.refresh(entity) ;

        } catch (Exception ex) {
            view.displayMsg("Unable to update " + ex.toString());
            setWp(getWpById(currentP.getId()));
            System.out.println("Unable to update " + ex.toString());
            return null;
            
        }
        project.put(entity.getId(), new WorkPackage(entity));
        currentP.getBudget().reinitType("planned");
        view.displayMsg(entity.getId() + "Successfully updated");
        return null;
    }

    public Set<LabourchargerateEntity> getEffectiveRatesForDate(Date date) {
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        Set<LabourchargerateEntity> rateSheet = new HashSet<LabourchargerateEntity>();
        for (LabourGrade grade : LabourGrade.values()) {
            String queryStr = RATE_SQL + "'" + grade.toString() + "'";
            System.err.println(queryStr);
            Query query = em.createQuery(queryStr);
            final List<LabourchargerateEntity> p1result = query.getResultList();
            System.err.println("All rates: " + p1result);
            if (p1result == null || p1result.isEmpty()) {
                break;
            }
            LabourchargerateEntity rate = p1result.get(0);
            for (LabourchargerateEntity e : p1result) {
                if (!e.getEffectiveDate().before(date)
                        & e.getEffectiveDate().after(rate.getEffectiveDate())) {
                    rate = e;
                    System.err
                            .println("Rates: "
                                    + e
                                    + "******************************************************************");
                }
            }
            rateSheet.add(rate);
        }
        return rateSheet;
    }
    
    public boolean isLeaf(){
        if(currentP == null){return false;}
        return (project.getChildren(currentP.getId()).isEmpty());
    }
    
    public void initSummaryPlannedBudget(){
        
//        if(project.getChildren(currentP.getId()) == null)
//            return;
//        currentP.getBudget().reinitPlanned();
//        for(WorkPackage child : project.getChildren(currentP.getId())){
//            currentP.getBudget().addAllToPlanned(child.getBudget().getPlanned());
//        }
    }
   
    
}
