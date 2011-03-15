package comp.is.view.project;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import comp.is.controller.project.ProjectAction;
import comp.is.controller.project.WorkPackageAction;
import comp.is.model.project.WorkPackage;

@Named("projectView")
@SessionScoped
public class ProjectManagerView implements Serializable {

    @Inject
    private ChildWpPanelBean childPanel;
    @Inject
    private EmployeePickListBean empPickList;
    @Inject
    ProjectAction projectAction;
    @Inject
    private ProjectTreeBean projectTree;
//    @Inject
//    WorkPackageAction wpAction;

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
                + projectAction.getProject().getRoot());
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

    @PostConstruct
    public void init() {
        projectTree.init(projectAction.getProject());
        displayRoot();
        // empPickList = new
        // EmployeePickListBean(projectAction.getWp().getParent().getWpEmployeesAssigned(),
        // projectAction.getWp().getWpEmployeesAssigned());
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
                System.out.println("Not null, setting to : "
                        + WorkPackage.unpad(selectedNode));
            projectAction.setWp(wp);
        }
    }


//    public void setChildPanel(ChildWpPanelBean childPanel) {
//        this.childPanel = childPanel;
//    }
//
//    public void setProjectTree(ProjectTreeBean projectTree) {
//        this.projectTree = projectTree;
//    }

}
