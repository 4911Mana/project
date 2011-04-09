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

import comp.is.model.project.ProjectIndexTree;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.WorkpackageEntity;

@Named("tree")
@SessionScoped
public class ProjectTreeBean implements Serializable {

    private ProjectTree project;
    private TreeNode root;
    private TreeNode rootWp;
    private TreeNode selectedNode;

    @Inject
    ProjectView view;

    public ProjectTreeBean() {
        root = new DefaultTreeNode("Roooooot", null);
    }

    public void addChild(String child, String parent) {

        if (selectedNode == null
                || !selectedNode.getData().toString().equalsIgnoreCase(parent)) {
            view.nodeIsNotValid();
            return;
        }
        selectedNode.setExpanded(true);
        TreeNode newChild = new DefaultTreeNode(child, selectedNode);
        newChild.setSelected(true);
        setSelectedNode(newChild);

        System.out.println(child + " for " + parent + " added");
    }

    private void addObjChildrenRecursively(TreeNode parent) {
        // getId()?
        List<WorkPackage> children = project.getChildren(parent.getData().toString());
        System.out.println("Adding Child to Tree for " + parent);
        if (children.isEmpty())
            return;
        for (WorkPackage child : children) {

            TreeNode newChild = new DefaultTreeNode(child, parent);
            addObjChildrenRecursively(newChild);
        }
    }

    private void buildTree(ProjectTree project) {
        root = new DefaultTreeNode("Roooooot", null);
        rootWp = new DefaultTreeNode(project.getRoot(), root);
        addObjChildrenRecursively(rootWp);
    }

    public void displaySelectedSingle(ActionEvent e) {
        if (selectedNode == null) {
            System.out.println("No node selected");
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void init(ProjectTree project) {
        this.project = project;
        buildTree(project);
        rootWp.setExpanded(true);
    }

    public void onNodeSelect(NodeSelectEvent event) {
        if (event == null || selectedNode == null
                || selectedNode.getData().toString().isEmpty()
                || event.getTreeNode().getData() == null) {
            view.nodeIsNotValid();
            System.out.println("Selection is not valid");
            return;
        }
        if (event.getTreeNode() == rootWp) {
            view.displayRoot();
        } else if (selectedNode.getData().toString()
                .equalsIgnoreCase(event.getTreeNode().getData().toString())) {
            System.out.println("Selected" + selectedNode.getData().toString()
                    + "Event source : "
                    + event.getTreeNode().getData().toString());
            view.onNodeSelect();

        }
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    
    public String toString(){
        return (rootWp == null)? "root wp not set" : rootWp.getData().toString();
    }
}