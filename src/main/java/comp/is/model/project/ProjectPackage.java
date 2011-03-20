package comp.is.model.project;

import java.util.Set;

import comp.is.model.admin.Employee;
import comp.is.model.project.entity.EmployeeEntity;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.model.project.entity.WorkpackageEntity;

public class ProjectPackage extends ProjectEntity {
    final WorkPackage rootFlag;

    public ProjectPackage(ProjectEntity root) {
        init(root);
        customer = root.getCustomer();
        labourratemarkup = root.getLabourratemarkup();
        workPackages = root.getWorkPackages();
        projectEmployees = root.getProjectEmployees();
        rootFlag = new WorkPackage();
        rootFlag.setId(".");
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

}
