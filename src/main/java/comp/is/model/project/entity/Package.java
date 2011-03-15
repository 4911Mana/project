package comp.is.model.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class Package implements Serializable{
    protected static final int LENGTH = 6;
    protected static final char PADDING = '0';
    protected String description;
    protected String id;
    protected String name;
    protected Date startDate;
    protected String status;
    
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
        return null;
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
    
    //abstract public List<EmployeeEntity> getWpEmployeesAssigned();

    //abstract public void setWpEmployeesAssigned(List<EmployeeEntity> wpEmployeesAssigned);
    
    //abstract public Package getParent();
}
