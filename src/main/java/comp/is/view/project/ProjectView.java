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

import org.primefaces.event.SelectEvent;


import comp.is.controller.project.ProjectAction;
import comp.is.controller.project.ProjectActionLocal;
import comp.is.model.admin.Employee;
import comp.is.model.admin.LabourGrade;
import comp.is.model.project.BudgetTypeMismatchException;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.WorkPackageBudgetEntity;

@Named("projectView")
@RequestScoped
public class ProjectView implements Serializable {

    @Inject
    private ChildWpPanelBean childPanel;
    @Inject
    private EmployeePickListBean empPickList;
    @Inject
    ProjectAction projectAction;
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
        empPickList.setRendered(false);
        projectAction.initProjectPlannedBudget();
        try {
            projectAction.updateTotal();
        } catch (BudgetTypeMismatchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            wp.getBudget().print();
            projectAction.setWp(wp);
            projectAction.initSummaryPlannedBudget(projectAction.getWp());
            try {
                projectAction.updateTotal();
            } catch (BudgetTypeMismatchException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ArrayList<Employee> assignedEmp = projectAction.getTargetEmp(wp);
            for(Employee e : assignedEmp){System.out.println(e.getLastname());}
            Collections.sort(assignedEmp);
            ArrayList<Employee> availEmp = new ArrayList<Employee>(projectAction.getSourceEmp(wp));
            for(Employee e : availEmp){System.out.println(e.getLastname());}
            Collections.sort(availEmp);
            if(assignedEmp != null || !assignedEmp.isEmpty()){availEmp.removeAll(assignedEmp);}
            
            empPickList.arrangeEmployees(availEmp, assignedEmp, 
                    (wp.getResponsibleEngineer()==null)? null : new Employee(wp.getResponsibleEngineer()));
            }
            
        }
    }
    
    public void resetTabs(){
        empPickList.setActiveTabIndex(0);
    }
   
    
}
