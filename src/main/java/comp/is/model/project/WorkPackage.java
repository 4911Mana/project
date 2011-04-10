package comp.is.model.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import comp.is.model.admin.Employee;
import comp.is.model.admin.LabourGrade;
import comp.is.model.project.Budget.RateAmountPair;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.LabourchargerateEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.RespEngReportBudgetEntryEntity;
import comp.is.model.project.entity.TimesheetentryEntity;
import comp.is.model.project.entity.WorkPackageBudgetEntity;
import comp.is.model.project.entity.WorkpackageEntity;
import comp.is.model.project.entity.WorkpackagestatusreportEntity;

public class WorkPackage extends WorkpackageEntity implements
        Comparable<WorkPackage> {

    public static String pad(String num) {
        String padNum = num;
        for (int i = padNum.length(); i < LENGTH; i++) {
            padNum += PADDING;
        }
        return padNum;
    }

    public static String unpad(String num) {
        int numEnd = num.indexOf(PADDING);
        if (numEnd == -1) {
            return num;
        }
        String padNum = num.substring(0, num.indexOf(PADDING));

        return padNum;
    }

    public WorkPackage() {
    }

    public WorkPackage(Package p) {
        init(p);
    }

    public WorkPackage(WorkpackageEntity p) {
        init(p);
        setParent(p.getParent());
        responsibleEngineerId = p.getResponsibleEngineer();
        timeSheetEntries = p.getTimeSheetEntries();
        employeesAssigned = p.getEmployeesAssigned();
        statusReports = p.getStatusReports();
        project = p.getProject();
        plannedBudget = p.getPlannedBudget();
        

        if (p.getEmployeesAssigned() != null
                & (employees == null || employees.isEmpty())) {
            for (EmployeeEntity e : p.getEmployeesAssigned()) {
                employees.add(new Employee(e));
            }
        }
        for (WorkPackageBudgetEntity entry : getPlannedBudget()) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$"
                    + "getBudgetEntryForLaborGrade "
                    + entry.getLabourChargeRate().getRateclassid());

        }
        System.out.println(id + " Emp: " + employees + " Timesheets: "
                + this.timeSheetEntries + " plannedBgt " + plannedBudget);
    }

    public WorkPackage getParentWp() {
        return new WorkPackage(super.getParent());
    }
    

    @Override
    public int compareTo(WorkPackage iWp) throws ClassCastException {
        if (!(iWp instanceof WorkPackage))
            throw new ClassCastException("A WorkPackage object expected.");
        String iWpNum = (iWp).getNumber();
        return getId().compareTo(iWpNum);
    }

    public boolean isRootChild() {
        return getParent().getId().equalsIgnoreCase(".");
    }

    public String getChildMask() {
        String mask = "*";
        // null is dif
        if (parent == null || getParent().getId().equalsIgnoreCase(".")) {
            mask += '*';
            System.out.println("First child mask");
        } else {
            for (int i = 0; i < getId().length(); i++) {
                mask += '*';
            }
        }
        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING; // make configurable, ?
        }
        return mask;
    }

    public String getDetails() {
        return getNumber() + " Parent: "
                + ((getParent() == null) ? "null" : getParent().getId());
    }

    public String getNumber() {
        String id = getId();
        if (id == null) {
            System.out.println("Id is null");
            return id;
        }
        return pad(id);
    }

    public void setNumber(String num) {
        System.out.println("setting view wp to" + num);
        id = (unpad(num));
    }

    public void setParent(Package p) {
        if (p instanceof ProjectPackage) {
            super.setParent(((ProjectPackage) p).getRootFlag());
        } else {
            super.setParent(new WorkPackage(p));
        }

    }

    public String toString() {
        return getNumber();
    }

    public boolean validLengthNum() {
        if (getParent().getId().equalsIgnoreCase(".")) {
            return (getId().length() == 1);
        }
        return (getId().length() == getParent().getId().length() + 1);
    }

    public boolean validPrefixNum() {
        if (getParent().getId().equalsIgnoreCase(".")) {
            return true;
        }
        return getId().matches(getParent().getId() + "\\w");
    }

    public ArrayList<Employee> getAvailableStaff() {
        WorkPackage p = new WorkPackage(getParent());
        System.out.println("Aval staff: parent: " + p.getEmployees());
        if (parent.getEmployeesAssigned() == null
                || parent.getEmployeesAssigned().isEmpty()) {
            if (p.isRootChild()) {
                return null;
            }
            return p.getAvailableStaff();
        } else
            return new ArrayList<Employee>(p.getEmployees());
    }

    public void mereg(Package p) {
        description = p.getDescription();
        id = p.getId();
        name = p.getName();
        startDate = p.getStartDate();
        status = p.getStatus();
        setEmployees(p.getEmployees());
    }

    public boolean isOpenForCharges() {
        if (getResponsibleEngineer() == null) {
            return super.isOpenForCharges();
        }
        return true;
    }

    public void initAccumulatedBudget() {
        if(budget.getBudgetForType("accumulated") == null){
            budget.reinitType("accumulated");
        }
        if (this.employees == null) {
            return;
        }
        System.out.println("Budget init start--------------------------------");

        for (TimesheetentryEntity te : getTimeSheetEntries()) {
            Date timesheetDate = te.getTimeSheet().getTimeSheetWeek().getWeekend();
            if (te.getWorkPackage().getId().equalsIgnoreCase(getId())
                    & te.getWorkPackage().getProjid()
                            .equalsIgnoreCase(getProjid())) {
                System.out.println("Time sheet entry " + te);
                // System.out.println("Current grade" + e.getCurrentGrade());
                LabourGrade grade = LabourGrade.getGrade(new Employee((te
                        .getTimeSheet().getEmployee())).getCurrentGrade());
                LabourchargerateEntity rateEntity = getRateForGrade(grade);
                budget.addToSumType("accumulated", grade,
                        getTotalForTimesheetEntry(te), new Double(0));
            }
        }
        System.out
                .println("Budget init end-----------------------------------");
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

    public List<Entry<LabourGrade, Hashtable<String, Budget.RateAmountPair>>> getWpBudget() {
//        List<Entry<LabourGrade, Hashtable<String, Budget.RateAmountPair>>> budgetList = new ArrayList<Entry<LabourGrade, Hashtable<String, Budget.RateAmountPair>>>(
//                budget.entrySet());

        return new ArrayList<Entry<LabourGrade, Hashtable<String, Budget.RateAmountPair>>>(budget.entrySet());
    }
    public List<Entry<LabourGrade, PlannedBudgetEntry>> getWpPlannedBudgetList() {
        System.out.println("Getting planned bgt lst" + plannedBudgetList);
        return new ArrayList<Entry<LabourGrade, PlannedBudgetEntry>>(plannedBudgetList.entrySet());
    }

    public void initPlannedBudget() {
        System.out
                .println("Planned Budget init start--------------------------------=============================");
        if(budget.getBudgetForType("initplanned") == null){
            budget.reinitType("initplanned");
        }
        if (getPlannedBudget() == null) {
            return;
        }
        for (WorkPackageBudgetEntity entry : getPlannedBudget()) {
            Hashtable<String, Budget.RateAmountPair> bugEntry = new Hashtable<String, Budget.RateAmountPair>();
            LabourGrade lg = LabourGrade.getGrade(entry.getLabourChargeRate()
                    .getRateclassid());
            Double hrsAmount = new Double(entry.getWpAmount());
            Double dolAmount = hrsAmount * entry.getLabourChargeRate().getRate();
            bugEntry.put("initplanned", this.budget.new RateAmountPair(hrsAmount, dolAmount));
            budget.put(lg, bugEntry);
            System.out.println("Planned budget : "
                    + LabourGrade.getGrade(entry.getLabourChargeRate()
                            .getRateclassid()) + ":" + entry.getWpAmount()
                    + "$");
        }
        System.out
                .println("Planned Budget init end--------------------------------=================================");
    }

    public void initToCompleteBudget() {
        if(budget.getBudgetForType("tocomplete") == null){
            budget.reinitType("tocomplete");
        }
        for (WorkpackagestatusreportEntity report : getStatusReports()) {
            for (RespEngReportBudgetEntryEntity entry : report.getEntries()) {
                Hashtable<String, Budget.RateAmountPair> bugEntry = new Hashtable<String, Budget.RateAmountPair>();
                Budget.RateAmountPair pair = this.budget.new RateAmountPair(
                        entry.getAmount(),  entry.getAmount() * entry.getLabourChargeRate().getRate());
                bugEntry.put("tocomplete", pair);
                budget.put(LabourGrade.getGrade(entry.getLabourChargeRate()
                        .getRateclassid()), bugEntry);
            }
        }
    }

    public void flushPlannedBudget() {
        try {
            for (LabourGrade grade : LabourGrade.values()) {
                Double amount = getPlannedBudgetList().get(grade)
                        .getAdjustmentAmount();
                if (amount > 0) {
                    WorkPackageBudgetEntity entity = getBudgetEntryForLaborGrade(grade);
                    if (entity == null) {
                        entity = new WorkPackageBudgetEntity();
                        entity.setProjId(getProjid());
                        entity.setWpId(id);
                        entity.setWp(this);

                        entity.setLabourChargeRateId(getRateForGrade(grade)
                                .getId());
                        entity.setLabourChargeRate(getRateForGrade(grade));
                    } else if (entity.getLabourChargeRate().equals(
                            getRateForGrade(grade))) {
                        amount += budget.get(grade).get("initplanned")
                                .getHrsVal();
                    }
                    entity.setWpAmount(amount);
                    plannedBudget.add(entity);
                    budget.addToSumType("initplanned", grade, amount, amount * getRateForGrade(grade).getRate());

                    // System.out.print("Flushing planned, grade -" + grade);
                    // Double amount =
                    // budget.get(grade).get("planned").getHrsVal();
                    // if (amount != null & amount > 0) {
                    // WorkPackageBudgetEntity entity =
                    // getBudgetEntryForLaborGrade(grade);
                    // if (entity == null) {
                    // entity = new WorkPackageBudgetEntity();
                    // entity.setProjId(getProjid());
                    // entity.setWpId(id);
                    // entity.setWp(this);
                    //
                    // entity.setLabourChargeRateId(getRateForGrade(grade)
                    // .getId());
                    // entity.setLabourChargeRate(getRateForGrade(grade));
                    // } else if (entity.getLabourChargeRate().equals(
                    // getRateForGrade(grade))) {
                    // amount += budget.get(grade).get("initplanned")
                    // .getHrsVal();
                    // }
                    // entity.setWpAmount(amount);
                    // plannedBudget.add(entity);
                    // budget.addToPlanned(grade, amount);
                    // System.out.println("Amount==" + amount);

                    // }
                    //
                }
                
            }
            budget.reinitType("planned");
            initPlannedBudgetEntries();
        } catch (Exception e) {
            System.out.println("Flshing " + e.toString());
            e.printStackTrace();
        }
    }

    public LabourchargerateEntity getRateForGrade(LabourGrade grade) {
        if (rates == null || grade == null) {
            return null;
        }
        System.out.println("Total raltes: " + rates.size());
        for (LabourchargerateEntity lbchr : rates) {
            if (lbchr.getRateclassid().equalsIgnoreCase(grade.name())) {
                return lbchr;
            }
        }
        return null;
    }

    public String print() {
        flushPlannedBudget();
        System.out.println(getBudget());
        return null;
    }

    WorkPackageBudgetEntity getBudgetEntryForLaborGrade(LabourGrade grade) {
        if (getPlannedBudget() == null)
            return null;
        System.out.println("getBudgetEntryForLaborGrade " + getPlannedBudget());
        for (WorkPackageBudgetEntity entry : getPlannedBudget()) {
            System.out.println("getBudgetEntryForLaborGrade " + grade
                    + entry.getLabourChargeRate().getRateclassid());
            if (entry.getLabourChargeRate().getRateclassid()
                    .equalsIgnoreCase(grade.toString()))
                return entry;
        }
        return null;
    }


    public void initPlannedBudgetEntries() {
        if (getPlannedBudgetList() == null) {
            setPlannedBudgetList(new PlannedBudgetList());
        }
        System.out.println("init Planned Bgt List" + budget
                .getBudgetForType("initplanned"));
        for (Iterator<Entry<LabourGrade, RateAmountPair>> it = budget
                .getBudgetForType("initplanned").entrySet().iterator(); it
                .hasNext();) {
            Entry<LabourGrade, RateAmountPair> entry = it.next();
            PlannedBudgetEntry newEntry = getPlannedBudgetList().get(entry.getKey());
            newEntry.setCurrentAmount(entry.getValue().getHrsVal());
            newEntry.setAdjustmentAmount(0D);
            System.out.println("adding  Planned Bgt List" + newEntry.getLg());
            getPlannedBudgetList().put(newEntry.getLg(), newEntry);
        }
        System.out.println("init Planned Bgt List End " + getPlannedBudgetList());
    }
    
    
}
