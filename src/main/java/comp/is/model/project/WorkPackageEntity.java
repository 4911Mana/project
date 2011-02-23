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
public class WorkPackageEntity implements Serializable{
    @Id
    protected String number;
    private String title;
    private String description;
    private Date startDate;
    @Transient
    protected String parentNum;
    //?? get project from parent ??
    @Transient
    private String project;
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
    
    
    public String getParentNum() {
        return parentNum;
    }
    public void setParentNUm(String parent) {
        this.parentNum = parent;
    }
    public String getProject() {
        return project;
    }
    public WorkPackageEntity(){
        number = "";
    }
    public WorkPackageEntity(String number){
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
    public void init(WorkPackageEntity wp) {
        number = wp.number;
        title = wp.title;
        description = wp.description;
        startDate = wp.startDate;
        parentNum = wp.parentNum;
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
    
    
    public Hashtable<LabourGrade.Type, Integer> getActCost() {
        return actCost;
    }
    public void setActCost(Hashtable<LabourGrade.Type, Integer> actCost) {
        this.actCost = actCost;
    }
    public String toString(){
        return "Wp number: " + number + " Charges: " + openForCharges; 
    }
    public WorkPackageEntity getCopy() {
        WorkPackageEntity temp = new WorkPackageEntity();
        temp.number = number;
        temp.title = title;
        temp.description = description;
        temp.startDate = startDate;
        temp.parentNum = parentNum;
        temp.openForCharges = openForCharges;
        //add more
        return temp;
    }
    
    public boolean isLeaf(){
        if(actCost.isEmpty() & startDate == null){return true;}
        return false;
    }
    
}
