package comp.is.model.admin;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.EmployeelabourchargerateEntity;
import comp.is.model.project.entity.EmployeeroleEntity;
import comp.is.model.project.entity.ProjectsummaryEntity;
import comp.is.model.project.entity.RatesheetEntity;
import comp.is.model.project.entity.TimesheetEntity;
import comp.is.model.project.entity.WorkpackageEntity;
import comp.is.model.project.key.WorkpackagePK;


public class Employee extends EmployeeEntity implements
Comparable<Employee> {
    private LabourGrade currentGrade; 
    public Employee(){}
    
    public Employee(EmployeeEntity ee) {
        accumflextime = ee.getAccumflextime();
        accumvacation = ee.getAccumvacation();
        firstname = ee.getFirstname();
        id = ee.getId();
        lastname = ee.getLastname();
        labourChargeRates = ee.getLabourChargeRates();
        roles = ee.getRoles();
        supervisors = ee.getSupervisors();
        workPackages = ee.getWorkPackages();
        percentfulltime = ee.getPercentfulltime();
        //private List<ProjectEntity> projects;
        projectSummaries = ee.getProjectSummaries();
        rateSheets = ee.getRateSheets();
        timeSheetApprovers = ee.getTimeSheetApprovers();
        timeSheets = ee.getTimeSheets();
    }
    
    public LabourGrade getCurrentGrade() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        Integer weekNum = cal.get(Calendar.WEEK_OF_YEAR);
        for(EmployeelabourchargerateEntity charge: this.getLabourChargeRates()){
            System.out.println("All labour grades: " + charge.getLabourChargerRate().getRateclassid());
            if (charge.getEndTimeSheetWeek() == null) return LabourGrade.getGrage(charge.getLabourChargerRate().getRateclassid());
        }
        return currentGrade;
    }
    public String getStrId(){
        return String.valueOf(getId());
    }

    public void setCurrentGrade(LabourGrade currentGrade) {
        this.currentGrade = currentGrade;
    }
    public String toString(){
        return getStrId() 
        + " : " + getLastname() 
        + ", " + getFirstname();
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        Employee castOther = (Employee)other;
        return 
            this.id == castOther.id;
        }

    @Override
    public int compareTo(Employee emp) {
        if (!(emp instanceof Employee))
            throw new ClassCastException("A Employee object expected.");
        int empId = (emp).getId();
        return id - empId;
    }
}
