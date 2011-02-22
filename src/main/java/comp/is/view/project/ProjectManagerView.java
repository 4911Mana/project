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
import comp.is.controller.project.entities.Workpackage;
import comp.is.controller.project.entities.keys.WorkpackagePK;
import comp.is.model.project.ProjectTree;

@SessionScoped
@Named("projectView")
public class ProjectManagerView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    ProjectAction projectAction;
    @Inject
    WorkPackageAction wpAction;

    @Inject
    private ChildWpPanelBean childPanel;
    @Inject
    private ProjectTreeBean projectTree;
    
    public ProjectManagerView() {
    }
    @PostConstruct
    public void init() {
    	projectAction.initializeProject("PR002");
        ProjectTree tree = projectAction.generateWpIndexes();
        projectTree.init(tree);
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
        System.out.println("Selected: " + selectedNode);
        WorkpackagePK wpPK = new WorkpackagePK();
        if (selectedNode.isEmpty()) {
            return;
        } else {
            // validate

            Workpackage wp = projectAction.getWpById(selectedNode);
            System.out.println("REtrieved wp: " + wp.getId().getWpid());
            if (wp != null)// should be exception
            	wpPK.setWpid(selectedNode);
            	wpPK.setProjid(projectAction.getProjId());
                wpAction.initialize(wpPK);
        }
    }

    public void addChildToTree(String child, String parent) {
        childPanel.setRendered(false);
        projectTree.addChild(child, parent);
    }

    public void displayRoot() {
    	WorkpackagePK wpPK = new WorkpackagePK();
    	wpPK.setWpid(projectAction.getRoot().getId().getWpid());
    	wpPK.setProjid(projectAction.getRoot().getId().getProjid());
        wpAction.initialize(wpPK);
        projectTree.setSelectedNode(projectTree.getRoot());
        projectTree.getRoot().setSelected(true);
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
