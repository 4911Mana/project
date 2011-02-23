package comp.is.view.project;

/**

 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

@Named("tree")
@SessionScoped
public class ProjectTreeBean implements Serializable {

    private TreeNode root;
    private TreeNode rootWp;
    private TreeNode selectedNode;
    private ProjectIndexTree indexes;
    private ProjectTree project;

    @Inject
    ProjectManagerView view;
    

    public ProjectTreeBean() {
        root = new DefaultTreeNode("Roooooot", null);
    }

    public void init(ProjectIndexTree tree) {
        // validate
        indexes = tree;
        buildTree(indexes);

    }
    
    public void init(ProjectTree project){
        this.project = project;
        buildTree(project);
    }

    private void buildTree(ProjectIndexTree indexes) {
        rootWp = new DefaultTreeNode(indexes.getRoot(), root);
        addChildrenRecursively(rootWp);

    }
    
    private void buildTree(ProjectTree project) {
        rootWp = new DefaultTreeNode(project.getRoot().getNumber(), root);
        addObjChildrenRecursively(rootWp);

    }

    private void addChildrenRecursively(TreeNode parent) {
        
        List<String> children = indexes
                .getChildren(parent.getData().toString());
        if (children.isEmpty())
            return;
        for (String child : children) {
            TreeNode newChild = new DefaultTreeNode(child, parent);
            addChildrenRecursively(newChild);
        }
    }

    private void addObjChildrenRecursively(TreeNode parent) {
        List<String> children = new ArrayList<String>();
        Iterator<Entry<String, WorkPackage>> it = project.entrySet().iterator();
        String parentNum = parent.getData().toString();
        while (it.hasNext()) {
            Entry<String, WorkPackage> row = it.next();
            if (row.getValue().getParent().getNumber()
                    .equalsIgnoreCase(parentNum)) {
                children.add(row.getKey());
            }
        }
        if (children.isEmpty())
            return;
        for (String child : children) {
            TreeNode newChild = new DefaultTreeNode(child, parent);
            addObjChildrenRecursively(newChild);
        }
    }

    public void addChild(String child, String parent) {

        selectedNode.setExpanded(true);
        TreeNode newChild = new DefaultTreeNode(child, selectedNode);
        indexes.put(child, parent);
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