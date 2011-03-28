package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import comp.is.model.admin.Employee;
import comp.is.model.admin.LabourGrade;
import comp.is.model.project.Budget;

@MappedSuperclass
public abstract class Package implements Serializable{
    protected static final int LENGTH = 6;
    protected static final char PADDING = '0';
    protected String description;
    protected String id;
    protected String name;
    protected Date startDate;
    protected String status;
    protected ArrayList<Employee> employees;
    protected Budget budget;
    
    
    public Package(){
        employees = new ArrayList<Employee>();
        budget = new Budget();
    }
 
    @Transient
    public String getChildMask(){return null;}

    @Column(length = 256)
    public String getDescription() {
        return description;
    }

    @Id
    @Column(unique=true, nullable=false, length=16)
    public String getId() {
        return id;
    }

    @Column(length = 256)
    public String getName() {
        return name;
    }
    @Transient
    public String getNumber(){
        return id;
    }

    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }
    @Column(length = 64)    
    public String getStatus() {
        return status;
    }
    
    public void init(Package candidate){
        id = candidate.id.toLowerCase();
        name = candidate.name;
        description = candidate.description;
        startDate = candidate.startDate;
        status = candidate.status;
        budget = candidate.budget;
        employees = candidate.employees;
    }
    @Transient
    public boolean isOpenForCharges(){
        if(status == null) return false;
        return (status.equalsIgnoreCase("Costing"));
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id.toLowerCase();
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setOpenForCharges(boolean val){
        if(val) status = "Costing";
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String toString(){
        return getNumber();
    }
    @Transient
    public boolean isLowestLevel() {
        if(id == null ) return true;
        return id.length() == LENGTH;
    }
    
    public void setLowestLevel(boolean b){
        
    }
    @Transient
    public ArrayList<Employee> getEmployees(){ return employees;}

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    
    @Transient
    public List<Entry<LabourGrade, Map<String, Double>>> getWpBudget() {
        return null;
    }
    
    @Transient
    public Budget getBudget() {
        return budget;
    }
   
    
    //abstract public void setWpEmployeesAssigned(List<EmployeeEntity> wpEmployeesAssigned);
    
    //need this one it to be able to use it in projectpackage 
    @Transient
    public Package getParent() { return null;}
    
}
