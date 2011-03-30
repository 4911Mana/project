package comp.is.model.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import comp.is.model.admin.Employee;
import comp.is.model.admin.LabourGrade;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.TimesheetEntity;
import comp.is.model.project.entity.TimesheetentryEntity;
import comp.is.model.project.entity.WorkpackageEntity;

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
        
        
        if (p.getEmployeesAssigned() != null & (employees == null || employees.isEmpty())) {
            for (EmployeeEntity e : p.getEmployeesAssigned()) {
                employees.add(new Employee(e));
            }
        }
        System.out.println(id + " Emp: " + employees + " Timesheets: " + this.timeSheetEntries);
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

//    @CurrentWp
//    @ChildWp
//    @Override
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
    
    public ArrayList<Employee> getAvailableStaff(){
        WorkPackage p = new WorkPackage(getParent());
        System.out.println("Aval staff: parent: " + p.getEmployees());
        if(parent.getEmployeesAssigned() == null || parent.getEmployeesAssigned().isEmpty()){
            if(p.isRootChild()){ return null;}
            return p.getAvailableStaff();
        }
        else return new ArrayList<Employee>(p.getEmployees());
    }
    
    public void mereg(Package p){
        description = p.getDescription();
        id = p.getId();
        name = p.getName();
        startDate = p.getStartDate();
        status = p.getStatus();
        setEmployees(p.getEmployees());
    }
    
    public boolean isOpenForCharges(){
        if(getResponsibleEngineer() == null){
        return super.isOpenForCharges();}
        return true;
    }
    
    public void initBudget(){
        if(this.employees == null){return;}
        System.out.println("Budget init start--------------------------------");
        for(Employee e: employees){
            for(TimesheetEntity tsh : e.getTimeSheets()){
                for(TimesheetentryEntity te: tsh.getTimeSheetEntries()){
                    if(te.getWorkPackage().getId().equalsIgnoreCase(getId()) & 
                            te.getWorkPackage().getProjid().equalsIgnoreCase(getProjid())){
                        System.out.println("Time sheet entry " + te);
                        //System.out.println("Current grade" + e.getCurrentGrade());
                     budget.addToAccumulated(LabourGrade.getGrade(e.getCurrentGrade()),  getTotalForTimesheetEntry(te));}
                }
            }
        }
        System.out.println("Budget init end-----------------------------------");
    }
    
    public Double getTotalForTimesheetEntry(TimesheetentryEntity te){
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
    public List<Entry<LabourGrade, Map<String, Double>>> getWpBudget() {
        List<Entry<LabourGrade, Map<String, Double>>> budgetList = 
            new ArrayList<Entry<LabourGrade, Map<String, Double>>>(budget.entrySet());
        //Collections.sort(budgetList);
        return budgetList;
    }

    
    
}
