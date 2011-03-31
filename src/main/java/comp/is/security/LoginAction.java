package comp.is.security;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import comp.is.model.admin.Employee;
import comp.is.model.project.LoggedIn;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.ProjectEntity;

@Stateful
@SessionScoped
@LocalBean
@DeclareRoles({ "Troy", "2", "3", "4" , "5", "6" })
//@RolesAllowed({ Roles.ADMIN, Roles.HR, Roles.PROJECT_MANAGER,
 //       Roles.RESPONSIBLE_ENGINEER, Roles.SUPERVISOR, Roles.TIMESHEET_APPROVER })
//@RolesAllowed({ "1", "2", "3", "4" , "5", "6"  })
@PermitAll
public class LoginAction {
    @PersistenceContext(unitName = "ProjectManager")
    private EntityManager em;
    @Resource
    SessionContext ctx;
    Employee loggedInEmployee;
    EmployeeEntity entity;

    @Produces
    @LoggedIn
    @SessionScoped
    @Named("employee")
    @PermitAll
    public Employee getLoggedInEmployee() {
        String name = ctx.getCallerPrincipal().getName();
        System.out.println(name);
        int id = Integer.parseInt(name);
        
      
            entity = em.find(EmployeeEntity.class, id);
            loggedInEmployee = new Employee(entity);
        
        return loggedInEmployee;
        }

    public void setLoggedInEmployee(Employee loggedInEmployee) {
        this.loggedInEmployee = loggedInEmployee;
    }


}
