package comp.is.view.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import comp.is.model.admin.Employee;

@Named
@SessionScoped
public class EmployeeTableBean implements Serializable {

    private final static String[] firstName;

    private final static String[] lastName;

    private List<Employee> employees;
    
    private Employee selectedEmployee;
    static {
        firstName = new String[10];
        firstName[0] = "Black";
        firstName[1] = "White";
        firstName[2] = "Green";
        firstName[3] = "Red";
        firstName[4] = "Blue";
        firstName[5] = "Orange";
        firstName[6] = "Silver";
        firstName[7] = "Yellow";
        firstName[8] = "Brown";
        firstName[9] = "Maroon";

        lastName = new String[10];
        lastName[0] = "Mercedes";
        lastName[1] = "BMW";
        lastName[2] = "Volvo";
        lastName[3] = "Audi";
        lastName[4] = "Renault";
        lastName[5] = "Opel";
        lastName[6] = "Volkswagen";
        lastName[7] = "Chrysler";
        lastName[8] = "Ferrari";
        lastName[9] = "Ford";
    }

    

    public EmployeeTableBean() {
        employees = new ArrayList<Employee>();
        populateRandomEmployees(employees, 50);
    }

    private void populateRandomEmployees(List<Employee> list, int size) {
        for(int i = 0 ; i < size ; i++)
            list.add(new Employee(getRandomModel(), getRandomManufacturer(), getRandomColor()));
    }
    
    private String getRandomColor() {
        return firstName[(int) (Math.random() * 10)];
    }
    
    private String getRandomManufacturer() {
        return lastName[(int) (Math.random() * 10)];
    }
    
    private String getRandomModel() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }
    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
    
