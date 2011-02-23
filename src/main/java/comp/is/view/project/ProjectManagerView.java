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
import comp.is.model.project.ProjectIndexTree;
import comp.is.model.project.WorkPackage;

@Named("projectView")
@SessionScoped
public class ProjectManagerView implements Serializable {

    
    @Inject
    ProjectAction projectAction;
    @Inject
    WorkPackageAction wpAction;
    @Inject
    private ChildWpPanelBean childPanel;
    @Inject
    private ProjectTreeBean projectTree;
    
    
    @PostConstruct
    public void init() {
        projectTree.init(projectAction.getProject());
        displayRoot();
    }

    public ChildWpPanelBean getChildPanel() {
        return childPanel;
    }

    public void setChildPanel(ChildWpPanelBean childPanel) {
        this.childPanel = childPanel;
    }

    public ProjectTreeBean getProjectTree() {
        return projectTree;
    }

    public void setProjectTree(ProjectTreeBean projectTree) {
        this.projectTree = projectTree;
    }

    public void onNodeSelect() {
        String selectedNode = projectTree.getSelectedNode().toString();
        System.out.println("View: Selected: " + selectedNode);
        if (selectedNode.isEmpty()) {
            return;
        } else {
            // validate
            WorkPackage wp = projectAction.getWpById(selectedNode);
           
            if (wp != null)// should be exception
                System.out.println("REtrieved wp: " + wp.getNumber());
                ProjectAction.getWp().init(wp);
        }
    }

    public void addChildToTree(String child, String parent) {
        childPanel.setRendered(false);
        projectTree.addChild(child, parent);
    }

    public void displayRoot() {
        ProjectAction.setWp(projectAction.getRoot());
        //projectTree.setSelectedNode(projectTree.getRoot());
        //projectTree.getRoot().setSelected(true);
    }

    public void displayMsgs(List<String> msgs) {
        for (String msg : msgs) {
            displayMsg(msg);
            System.out.println("Error !! " + msg);
        }
    }

    public void displayMsg(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                msg, "");

        context.addMessage(null, message);
    }

    // public void numCheck(FacesContext context, UIComponent component, Object
    // value){
    // if(value == null) return;
    // if(value instanceof String){!projectAction.validWpNum(value)}
    // }

    public void nodeIsNotValid() {
        wpAction.reinit();

    }

   

    

}
