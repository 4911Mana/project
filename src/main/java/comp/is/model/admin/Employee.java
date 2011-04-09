package comp.is.model.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import comp.is.model.project.LoggedIn;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.EmployeelabourchargerateEntity;
import comp.is.model.project.entity.EmployeeroleEntity;
import comp.is.model.project.entity.ProjectEntity;

@SessionScoped
public class Employee extends EmployeeEntity implements Comparable<Employee>,
        Serializable {
    private LabourGrade currentGrade;

    public Employee() {
        firstname = "boooooooooooo";
    }

    public Employee(EmployeeEntity ee) {
//        accumflextime = ee.getAccumflextime();
//        accumvacation = ee.getAccumvacation();
        firstname = ee.getFirstname();
        id = ee.getId();
        lastname = ee.getLastname();
        labourChargeRates = ee.getLabourChargeRates();
        roles = ee.getRoles();
        supervisor = ee.getSupervisor();
        workPackages = ee.getWorkPackages();
        //percentfulltime = ee.getPercentfulltime();
        projects = ee.getProjects();
        projectSummaries = ee.getProjectSummaries();
       // rateSheets = ee.getRateSheets();
        //timeSheetApprovers = ee.getTimeSheetApprovers();
        timeSheets = ee.getTimeSheets();
        peons = ee.getPeons();
        System.out.println("Peons : " + peons);
    }

    public String getCurrentGrade() {
        for (EmployeelabourchargerateEntity charge : this
                .getLabourChargeRates()) {
            if (charge.getEndTimeSheetWeek() == null)
                return charge.getLabourChargerRate().getRateclassid();
        }
        return null;// throw exception
    }

    public String getStrId() {
        return String.valueOf(getId());
    }

    public void setCurrentGrade(String currentGrade) {
        this.currentGrade = LabourGrade.getGrade(currentGrade);
    }

    public String toString() {
        return getStrId() + " : " + getLastname() + ", " + getFirstname();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        Employee castOther = (Employee) other;
        return this.id == castOther.id;
    }

    
    

    public ArrayList<ProjectEntity> getSupervisedProjects() {
        if (projects == null) {
            return null;
        }
        Set<ProjectEntity> list = new HashSet<ProjectEntity>();
        for (EmployeeroleEntity p : getRoles()) {
            list.add(p.getProject());
        }
        return new ArrayList<ProjectEntity>(list);
    }

    public ArrayList<ProjectEntity> getAllProjects() {
        if (projects == null) {
            return null;
        }
        return new ArrayList<ProjectEntity>(this.projects);
    }

    public ArrayList<EmployeeEntity> getAllPeons() {
        if (peons == null) {
            return null;
        }
        return new ArrayList<EmployeeEntity>(peons);
    }

    private ArrayList<Employee> convertToEmp(Set<EmployeeEntity> empSet) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        for (EmployeeEntity e : empSet) {
            employees.add(new Employee(e));
        }
        return employees;
    }
}
