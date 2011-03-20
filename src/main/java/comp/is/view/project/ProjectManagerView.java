package comp.is.view.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import comp.is.controller.project.ProjectActionLocal;
import comp.is.model.admin.Employee;
import comp.is.model.project.WorkPackage;

@Named("projectView")
@RequestScoped
public class ProjectManagerView implements Serializable {

    @Inject
    private ChildWpPanelBean childPanel;
    @Inject
    private EmployeePickListBean empPickList;
    @Inject
    ProjectActionLocal projectAction;
    @Inject
    private ProjectTreeBean projectTree;

    // @Inject
    // WorkPackageAction wpAction;

    public void addChildToTree(String child, String parent) {
        childPanel.setRendered(false);
        projectTree.addChild(child, parent);
    }

    public void displayMsg(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                msg, "");

        context.addMessage(null, message);
    }

    public void displayMsgs(List<String> msgs) {
        for (String msg : msgs) {
            displayMsg(msg);
            System.out.println("Error !! " + msg);
        }
    }

    public void displayRoot() {

        projectAction.setWp(projectAction.getProject().getRoot());
        System.out.println("Root is selected "
                + projectAction.getProject().getRoot().getEmployees());
        empPickList.setRendered(false);
    }

    public ChildWpPanelBean getChildPanel() {
        return childPanel;
    }

    public ProjectTreeBean getProjectTree() {
        return projectTree;
    }

    public String getStart() {
        return null;
    }

    public void init() {
        System.out.println("View init start " + projectTree);
        projectTree.init(projectAction.getProject());
        displayRoot();
        System.out.println("View init end");
    }

    public void nodeIsNotValid() {
        projectAction.reinit();
    }

    public void onNodeSelect() {
        String selectedNode = projectTree.getSelectedNode().toString();
        

        System.out.println("View: Selected: " + selectedNode);
        if (selectedNode.isEmpty()) {
            nodeIsNotValid();
        } else {
            // validate
            WorkPackage wp = projectAction.getWpById(WorkPackage
                    .unpad(selectedNode));

            if (wp != null)// should be exception
            {    
            empPickList.setRendered(true);
            projectAction.setWp(wp);
            ArrayList<Employee> assignedEmp = projectAction.getTargetEmp(wp);
            Collections.sort(assignedEmp);
            ArrayList<Employee> availEmp = new ArrayList<Employee>(projectAction.getSourceEmp(wp));
            Collections.sort(availEmp);
            availEmp.removeAll(assignedEmp);
            empPickList.arrangeEmployees(availEmp, assignedEmp, 
                    (wp.getResponsibleEngineer()==null)? null : new Employee(wp.getResponsibleEngineer()));
            System.out.println("Not null, setting to : "
                    + WorkPackage.unpad(selectedNode) + "\navail: " + availEmp + "\nass: " + assignedEmp);
            }
        }
    }

    // public void setChildPanel(ChildWpPanelBean childPanel) {
    // this.childPanel = childPanel;
    // }
    //
    // public void setProjectTree(ProjectTreeBean projectTree) {
    // this.projectTree = projectTree;
    // }

//    public void initProjectEmp(ArrayList<Employee> arrayList) {
//        System.out.println("Num of empl " + arrayList.size());
//        for (Employee e : arrayList) {
//            empTable.getEmployees().add(e);
//        }
//    }

    
}
