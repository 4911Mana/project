package comp.is.model.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import comp.is.model.admin.Employee;
import comp.is.model.admin.LabourGrade;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.WorkpackageEntity;

public class WorkPackage extends WorkpackageEntity implements
        Comparable<WorkPackage> {

    //ArrayList<Employee> 
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

    private Hashtable<LabourGrade, Double> budget = new Hashtable<LabourGrade, Double>(
            6);

    // private boolean candidate = false;

    public WorkPackage() {
        super();
        budget.put(LabourGrade.P1, 4.6);
    }

    public WorkPackage(Package p) {
        init(p);
    }

//    public WorkPackage(WorkPackage p) {
//        id = p.id;
//        name = p.name;
//        description = p.description;
//        setOpenForCharges(p.isOpenForCharges());
//        this.parent = p.getParent();
//        this.projid = p.getProjid();
//        this.status = p.getStatus();
//        this.startDate = p.getStartDate();
//    }

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

    public List<LabourGrade> getBudgetKeys() {
        return new ArrayList<LabourGrade>(budget.keySet());
    }

    public ArrayList<Double> getBudgetVal() {
        return new ArrayList<Double>(budget.values());
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

    // public void init(WorkPackage p) {
    // super.init(p);
    // this.projid = p.getProjid();
    // // responsibleEngineer = p.responsibleEngineer;
    // //this.candidate = false;
    // this.parent = p.getParent();
    // // this.project = p.getProject();
    // }

    // public String getParentMask() {
    // String parentNum = getWpParent().getNumber();
    // if(parentNum.length() == LENGTH){return parentNum;} // should be
    // exception
    //
    // String mask = "*";
    // for (int i = 1; i < parentNum.length(); i++) {
    // mask += "*";
    // }
    // for (int i = mask.length(); i < LENGTH; i++) {
    // mask += PADDING;
    // }
    //
    // return mask;
    // }

    // public boolean isCandidate() {
    // return candidate;
    // }
    //
    // public void setCandidate(boolean candidate) {
    // this.candidate = candidate;
    // }

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
}
