package comp.is.model.project;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import comp.is.model.admin.Employee;
import comp.is.model.admin.LabourGrade;

@Entity
@Named("wp")
@SessionScoped
public class WorkPackage implements Serializable{
    @Id
    private String number;
    private String title;
    private String description;
    private Date startDate;
    @Transient
    private WorkPackage parent;
    //?? get project from parent ??
    @Transient
    private WorkPackage project;
    @Transient
    private List<Employee> staff;
    @Transient
    private Employee respEngineer;
    private boolean summary;
    private boolean openForCharges;
    @Transient
    private Hashtable<LabourGrade.Type, Integer> estCost;
    @Transient
    private Hashtable<LabourGrade.Type, Integer> actCost;
    @Transient
    private Hashtable<LabourGrade.Type, Integer> remCost;
    
    
    public WorkPackage getParent() {
        return parent;
    }
    public void setParent(WorkPackage parent) {
        this.parent = parent;
    }
    public WorkPackage getProject() {
        return project;
    }
    public WorkPackage(){
        number = "";
    }
    public WorkPackage(String number){
        this.number = number;
        //description = wp.description;
    }
    
    
    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }
   public void setTitle(String name) {
        this.title = name;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setWp(WorkPackage wp) {
        number = wp.number;
        title = wp.title;
        description = wp.description;
        startDate = wp.startDate;
        parent = wp.parent;
        openForCharges = wp.openForCharges;
        
    }
    
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public boolean isOpenForCharges() {
        return openForCharges;
    }
    public void setOpenForCharges(boolean openForCharges) {
        this.openForCharges = openForCharges;
    }
    public void reinit() {
        // TODO Auto-generated method stub
        
    }
    
    public Hashtable<LabourGrade.Type, Integer> getActCost() {
        return actCost;
    }
    public void setActCost(Hashtable<LabourGrade.Type, Integer> actCost) {
        this.actCost = actCost;
    }
    public String toString(){
        return "Wp number: " + number + " Charges: " + openForCharges + " Parent: " + ((parent == null)? "null" : parent.getNumber()); 
    }
    public WorkPackage getCopy() {
        WorkPackage temp = new WorkPackage();
        temp.number = number;
        temp.title = title;
        temp.description = description;
        temp.startDate = startDate;
        temp.parent = parent;
        temp.openForCharges = openForCharges;
        //add more
        return temp;
    }
    
    public boolean isLeaf(){
        if(actCost.isEmpty() & startDate == null){return true;}
        return false;
    }
    
}
