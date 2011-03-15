package comp.is.model.admin;
import java.io.Serializable;

import javax.inject.Inject;

import comp.is.model.project.entity.EmployeeEntity;


public class Employee extends EmployeeEntity{
    private LabourGrade currentGrade; 
    public Employee(){}
    
    public Employee(EmployeeEntity ee) {
        super();
    }
    
    public LabourGrade getCurrentGrade() {
        return currentGrade;
    }
    public String getId(){
        return String.valueOf(getEmpid());
    }

    public void setCurrentGrade(LabourGrade currentGrade) {
        this.currentGrade = currentGrade;
    }
    public String toString(){
        return getEmpid() 
        + " : " + getEmplastname() 
        + ", " + getEmpfirstname();
    }
}
