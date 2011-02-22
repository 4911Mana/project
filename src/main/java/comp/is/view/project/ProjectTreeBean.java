package comp.is.view.project;

/**

 */

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import comp.is.controller.project.entities.Workpackage;
import comp.is.model.project.ProjectTree;

@Named("tree")
@SessionScoped
public class ProjectTreeBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeNode root;
    private TreeNode rootWp;
    private TreeNode selectedNode;
    private ProjectTree projectTree;

    @Inject
    ProjectManagerView view;
    @Inject
    Workpackage currentWp;

    public ProjectTreeBean() {
        root = new DefaultTreeNode("Root", null);
    }

    public void init(ProjectTree tree) {
        // validate
    	projectTree = tree;
        buildTree();

    }

    private void buildTree() {
        rootWp = new DefaultTreeNode(projectTree.getRoot(), root);
        addChildrenRecursively(rootWp);

    }

    private void addChildrenRecursively(TreeNode parent) {
        List<String> children = projectTree
                .getChildren(parent.getData().toString());
        if (children.isEmpty())
            return;
        for (String child : children) {
            TreeNode newChild = new DefaultTreeNode(child, parent);
            addChildrenRecursively(newChild);
        }
    }

    public void addChild(String child, String parent) {
        selectedNode.setExpanded(true);
        TreeNode newChild = new DefaultTreeNode(child, selectedNode);
        projectTree.addChild(child, parent);
        newChild.setSelected(true);
        setSelectedNode(newChild);
        
        System.out.println(child + " for " + parent + " added");
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        if (event == null || selectedNode == null
                || selectedNode.getData().toString().isEmpty()
                || event.getTreeNode().getData() == null) {
            view.nodeIsNotValid();
            return;
        } else if (rootWp.isSelected()) {
            view.displayRoot();
        } else if (selectedNode.getData().toString()
                .equalsIgnoreCase(event.getTreeNode().getData().toString())) {
            System.out.println("Selected" + selectedNode.getData().toString()
                    + "Event source : "
                    + event.getTreeNode().getData().toString());
            view.onNodeSelect();

        }
    }

    public void displaySelectedSingle(ActionEvent e) {
        if (selectedNode == null) {
            System.out.println("No node selected");
        }
    }

}