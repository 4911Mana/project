package comp.is.model.admin;
import java.io.Serializable;

import javax.inject.Inject;


public class Employee implements Serializable{
    private String firstName;
    private String lastName;
    private String id;
    @Inject
    public Employee(){}
    
    public Employee(String firstName, String lastName, String id) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getId() {
        return id;
    }
}
