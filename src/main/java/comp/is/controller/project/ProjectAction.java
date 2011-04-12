package comp.is.controller.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
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
import comp.is.model.project.Budget;
import comp.is.model.project.Budget.RateAmountPair;
import comp.is.model.project.BudgetTypeMismatchException;
import comp.is.model.project.ChildWp;
import comp.is.model.project.CurrentWp;
import comp.is.model.project.PlannedBudgetEntry;
import comp.is.model.project.ProjectPackage;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.EmployeelabourchargerateEntity;
import comp.is.model.project.entity.LabourchargerateEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.model.project.entity.TimesheetentryEntity;
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
    private Double dolAmountAvailable;

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

    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#addChild()
     */

    public Double getDolAmountAvailable() {
        return dolAmountAvailable;
    }

    public void setDolAmountAvailable(Double dolAmountAvailable) {
        this.dolAmountAvailable = dolAmountAvailable;
    }

    public String addChild() {
        System.out.println("Saving: " + childWp.toString());
        // validate
        List<String> msgs = new ArrayList<String>();
        boolean err = false;
        // check for errors
        WorkPackage candidate = new WorkPackage(childWp);
        // if(currentP.is)
        candidate.setProjid(project.getRoot().getId());
        candidate.setParent(currentP);
        if (project.getRoot().getId().equalsIgnoreCase(currentP.getId())) {
            candidate.setParentId(".");
            candidate.setPlannedBudget(null);
            
        } else {
            candidate.setParentId(currentP.getId());
        }
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
        entity.setParent(null);
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
                try {
                    newWp.setRates(getEffectiveRatesForDate(newWp
                            .getStartDate()));
                } catch (RatesNotFoundException e) {
                    System.out.println(e.toString());
                }
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
        System.out
                .println("Before init &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7");
        try {
            for (WorkPackage wp : project.getLeafs()) {
                wp.getBudget().print();
                initAccumulatedBudget(wp);
                wp.getBudget().print();
                wp.initPlannedBudget();
                wp.getBudget().print();
                wp.initToCompleteBudget();
                wp.getBudget().print();

            }
            System.out
                    .println("After init Before update &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7");
            updateTreeBudget((WorkPackage) currentP);
            initProjectPlannedBudget();
            initSummaryPlannedBudget(currentP);
            updateTotal();

        } catch (Exception e) {
            System.err.println(e.toString());
        }

        view.init();
        return "success";
    }

    public void updateTotal() throws BudgetTypeMismatchException {
        if (pp.getInBudget() != null) {
            Double accumulated = this.getTotalForType("accumulated");
            Double tocomplete = this.getTotalForType("tocomplete");

            dolAmountAvailable = pp.getInBudget() - accumulated - tocomplete;
        }
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
        // entity.getP
        em.persist(entity);
        em.flush();
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
            for (Employee e : pp.getEmployees()) {
                System.out.println("projActin getsourceEmp " + e.getLastname());
            }
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
        // Set<Employee> emp = new HashSet<Employee>();
        Map<Integer, Employee> emp = new Hashtable<Integer, Employee>();
        for (Employee e : wp.getEmployees()) {
            if (!emp.containsKey(e.getId())) {
                emp.put(e.getId(), e);
            }
        }
        if (project.getChildren(wp.getId()).isEmpty()) {
            return new ArrayList<Employee>(wp.getEmployees());
        } else {
            for (WorkPackage cwp : project.getChildren(wp.getId())) {
                for (Employee e : getTargetEmp(cwp)) {
                    if (!emp.containsKey(e.getId())) {
                        emp.put(e.getId(), e);
                    }
                }
                // emp.addAll(getTargetEmp(cwp));
            }
            return new ArrayList<Employee>(emp.values());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see comp.is.controller.project.ProjectActionLocal#doMerge()
     */

    public String doMerge() throws BudgetTypeMismatchException {
        System.out.println("Saving " + currentP);
        List<String> msgs = new ArrayList<String>();
        boolean err = false;

        WorkPackage candidate = this.getWpById(currentP.getId());
        candidate.flushPlannedBudget();

        
        // if (!candidate.getId().equalsIgnoreCase(currentP.getId())) {
        // msgs.add("Number update is not allowed");
        // err = true;
        // }
        if (pickList.getRespEng() != null) {
            candidate.setResponsibleEngineer(new EmployeeEntity(pickList
                    .getRespEng()));
        }

        //candidate.mereg(currentP);
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
        WorkpackageEntity entity = new WorkpackageEntity(candidate);
        entity.setProjid(project.getRoot().getId());
        entity.setProject(project.getRoot());
        try {
            em.merge(entity);
        } catch (Exception ex) {
            view.displayMsg("Unable to update " + ex.toString());
            setWp(getWpById(currentP.getId()));
            System.out
            .println("Projid" + entity.getProjid());
            ex.printStackTrace();
            return null;

        }
        project.put(entity.getId(), new WorkPackage(entity));
        currentP.getBudget().reinitType("planned");
        view.displayMsg(entity.getId() + "Successfully updated");
        return null;
    }

    public Set<LabourchargerateEntity> getEffectiveRatesForDate(Date date)
            throws RatesNotFoundException {
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        Set<LabourchargerateEntity> rateSheet = new HashSet<LabourchargerateEntity>();
        for (LabourGrade grade : LabourGrade.values()) {
            String queryStr = RATE_SQL + "'" + grade.toString() + "'";
            Query query = em.createQuery(queryStr);
            final List<LabourchargerateEntity> p1result = query.getResultList();
            System.err.println("All rates: " + p1result);
            if (p1result == null || p1result.isEmpty()) {
                throw new RatesNotFoundException(
                        "Effective LabourChargerates not found for "
                                + grade.toString());
            }
            LabourchargerateEntity rate = p1result.get(0);
            for (LabourchargerateEntity e : p1result) {
                if (!e.getEffectiveDate().before(date)
                        & e.getEffectiveDate().after(rate.getEffectiveDate())) {
                    rate = e;
                }
            }
            rateSheet.add(rate);
        }
        return rateSheet;
    }

    public LabourchargerateEntity getEffectiveRatesForLabourGradeForDate(
            LabourGrade lg, Date date) {
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        String queryStr = RATE_SQL + "'" + lg.toString() + "'";
        Query query = em.createQuery(queryStr);
        final List<LabourchargerateEntity> p1result = query.getResultList();
        System.err.println("All rates: " + p1result);
        if (p1result == null || p1result.isEmpty()) {
            return null;
        }
        LabourchargerateEntity rate = p1result.get(0);
        for (LabourchargerateEntity e : p1result) {
            if (!e.getEffectiveDate().before(date)
                    & e.getEffectiveDate().after(rate.getEffectiveDate())) {
                rate = e;
            }
        }
        return rate;
    }

    public boolean isLeaf() {
        if (currentP == null) {
            return false;
        }
        return (project.getChildren(currentP.getId()).isEmpty());
    }

    public void initSummaryPlannedBudget(Package p) {

        if (!isLeaf()) {
            System.out.println("Current P is not a leaf " + p.getId());
            p.getPlannedBudgetList().init();
            for (WorkPackage child : project.getChildren(p.getId())) {
                p.getPlannedBudgetList().addAllToPlanned(
                        child.getPlannedBudgetList());
            }
            System.out.println("Current P is not a leaf "
                    + p.getPlannedBudgetList());
        }

    }

    public void initProjectPlannedBudget() {

        project.getRoot().getPlannedBudgetList().init();
        for (WorkPackage child : project.getChildren(getProject().getRoot()
                .getId())) {
            System.out.println("All children of "
                    + getProject().getRoot().getId() + ": " + child);
            project.getRoot().getPlannedBudgetList()
                    .addAllToPlanned(child.getPlannedBudgetList());
        }
    }

    public void initSummaryAccumulated(Package p)
            throws BudgetTypeMismatchException {
        if (!isLeaf()) {
            p.getBudget().reinitType("accumulated");
            // currentP.getBudget().
            for (WorkPackage child : project.getChildren(p.getId())) {
                p.getPlannedBudgetList().addAllToPlanned(
                        child.getPlannedBudgetList());
            }
            System.out.println("Current P is not a leaf "
                    + p.getPlannedBudgetList());
        }
    }

    public void updateParentBudget(WorkPackage p)
            throws BudgetTypeMismatchException {
        p.getParent()
                .getBudget()
                .addAllToSumType("initplanned",
                        p.getBudget().getBudgetForType("initplanned"));
        p.getParent()
                .getBudget()
                .addAllToSumType("accumulated",
                        p.getBudget().getBudgetForType("accumulated"));
        p.getParent()
                .getBudget()
                .addAllToSumType("tocomplete",
                        p.getBudget().getBudgetForType("tocomplete"));
    }

    public void updateTreeBudget(WorkPackage root)
            throws DataInconsistencyException, BudgetTypeMismatchException {
        System.out.println("update tree budget " + root.getId()
                + root.getBudget());
        if (project.getChildren(root.getId()).isEmpty()) {
            root.initPlannedBudgetEntries();
            updateParentBudget(root);
            System.out.println("Returning from update parent budget: "
                    + root.getId());
            return;
        }
        for (WorkPackage wp : project.getChildren(root.getId())) {
            updateTreeBudget(wp);
            root.initPlannedBudgetEntries();
            updateParentBudget(wp);
        }

    }

    public void initAccumulatedBudget(WorkpackageEntity wp)
            throws BudgetTypeMismatchException {
        if (wp.getBudget().getBudgetForType("accumulated") == null) {
            wp.getBudget().reinitType("accumulated");
        }
        if (wp.getEmployeesAssigned() == null) {
            return;
        }
        System.out
                .println("Accumulated Budget init start--------------------------------");

        for (TimesheetentryEntity te : wp.getTimeSheetEntries()) {
            Date timesheetDate = te.getTimeSheet().getTimeSheetWeek()
                    .getWeekend();
            if (te.getWorkPackage().getId().equalsIgnoreCase(wp.getId())
                    & te.getWorkPackage().getProjid()
                            .equalsIgnoreCase(wp.getProjid())) {
                System.out.println("Time sheet entry for "
                        + te.getTimeSheet().getId());

                LabourchargerateEntity rateEntity = null;
                ;
                try {
                    rateEntity = this.getRateForDate(te.getTimeSheet()
                            .getEmployee().getLabourChargeRates(),
                            timesheetDate);

                    Double hrsAmount = getTotalForTimesheetEntry(te);
                    Double dolAmount = new Double(0);
                    if (rateEntity != null) {
                        dolAmount = hrsAmount * rateEntity.getRate();

                        System.out.println("Adding to accum: " + rateEntity
                                + " /" + hrsAmount + "/" + dolAmount);
                        wp.getBudget().addToSumType(
                                "accumulated",
                                LabourGrade.getGrade(rateEntity
                                        .getRateclassid()), hrsAmount,
                                dolAmount);
                    }
                } catch (DataInconsistencyException e) {
                    System.out.println(e.toString());
                }
            }
        }
        System.out
                .println("Accumulated Budget init end-----------------------------------");
    }

    public Double getTotalForTimesheetEntry(TimesheetentryEntity te) {
        Double amount = 0D;
        System.out.println("Fri" + te.getFrihours());
        amount += te.getFrihours();
        System.out.println("Mon" + te.getMonhours());
        amount += te.getMonhours();
        System.out.println("Sat" + te.getSathours());
        amount += te.getSathours();
        System.out.println("Sun" + te.getSunhours());
        amount += te.getSunhours();
        System.out.println("Thu" + te.getThuhours());
        amount += te.getThuhours();
        System.out.println("Tue" + te.getTuehours());
        amount += te.getTuehours();
        System.out.println("Wed" + te.getWedhours());
        amount += te.getWedhours();
        return amount;
    }

    public LabourchargerateEntity getRateForDate(
            Set<EmployeelabourchargerateEntity> set, Date date)
            throws DataInconsistencyException {
        if (set.isEmpty()) {
            throw new DataInconsistencyException("No rates found");
        }
        Iterator<EmployeelabourchargerateEntity> it = set.iterator();
        LabourchargerateEntity fRate = it.next().getLabourChargerRate();
        while (it.hasNext()) {
            LabourchargerateEntity rate = it.next().getLabourChargerRate();
            if (!rate.getEffectiveDate().before(date)
                    & rate.getEffectiveDate().after(fRate.getEffectiveDate())) {
                fRate = rate;
            }
        }
        return fRate;
    }

    public Double getTotalForType(String type)
            throws BudgetTypeMismatchException {
        Budget budget = project.getRoot().getBudget();
        Hashtable<LabourGrade, RateAmountPair> accumulatedBudget = budget
                .getBudgetForType(type);
        Double total = 0.0;
        for (LabourGrade lg : LabourGrade.values()) {
            if (accumulatedBudget.get(lg) != null) {
                total += accumulatedBudget.get(lg).getDolVal();
            }

        }
        return total;
    }

}
