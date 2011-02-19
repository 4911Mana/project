package comp.is.model.admin;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;


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
