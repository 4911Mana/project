package comp.is.model.project;

import java.util.Set;

import comp.is.model.admin.Employee;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.EmployeeroleEntity;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.model.project.entity.ProjectbudgetEntity;
import comp.is.model.project.entity.WorkpackageEntity;
import comp.is.model.project.key.EmployeerolePK;

public class ProjectPackage extends ProjectEntity {
    final WorkPackage rootFlag;
    private EmployeeEntity manager;
    
    public ProjectPackage() {
        rootFlag = null;
        manager = new EmployeeEntity();
    }

    public ProjectPackage(ProjectEntity root) {
        init(root);
        if (findCurrentManager(root) != null) {
            manager = findCurrentManager(root).getEmployee();
            System.err.println("Project Manager constructor = "
                    + manager.getId());
        }

        customer = root.getCustomer();
        // labourratemarkup = root.getLabourratemarkup();
        workPackages = root.getWorkPackages();
        projectEmployees = root.getProjectEmployees();
        employeeRoles = root.getEmployeeRoles();
        initBudget = root.getProjectBudget();
        rootFlag = new WorkPackage();
        rootFlag.setId(".");
        rootFlag.setParent(rootFlag);
        rootFlag.setProjid(root.getId());
        if (projectEmployees != null) {
            for (EmployeeEntity ee : root.getProjectEmployees()) {
                employees.add(new Employee(ee));
            }
        }
        System.out.println((getEmployees() == null) ? "0" : getEmployees()
                .size() + " Emp added");
    }

    

    public String getChildMask() {
        String mask = "*";

        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING; // make configurable, ?
        }
        System.out.println("Mask " + mask);
        return mask;
    }

    public String getNumber() {
        return id;
    }

    public WorkPackage getRootFlag() {
        return rootFlag;
    }

    public EmployeeEntity getManager() {
        return manager;
    }

    public void setManager(EmployeeEntity manager) {
        this.manager = manager;
       
        System.out.println("Manager is set to " + manager.getLastname());
    }

    public Double getInBudget() {
        if (initBudget != null)
            return this.initBudget.getInitBudget();
        return null;

    }

    public void setInBudget(Double amount) {
        initBudget = new ProjectbudgetEntity();
        initBudget.setProjid(id);
        initBudget.setInitBudget(amount);
    }

    public EmployeeroleEntity findManager(EmployeeEntity currentProjManager) {
        for (EmployeeroleEntity role : getEmployeeRoles()) {
            if (role.getId().getEmpid()== (currentProjManager.getId())
                    & role.getId().getRoleid() == 1) {
                return role;
            }
        }
        return null;
    } 
    
    private EmployeeroleEntity findCurrentManager(ProjectEntity root) {
        for (EmployeeroleEntity role : root.getEmployeeRoles()) {
            if (role.getId().getProjid().equalsIgnoreCase(root.getId())
                    & role.getId().getRoleid() == 1) {
                return role;
            }
        }
        return null;
    }
}
