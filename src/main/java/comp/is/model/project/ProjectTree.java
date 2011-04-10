package comp.is.model.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;


public class ProjectTree extends Hashtable<String, WorkPackage> {
    private ProjectPackage root;

    public ProjectTree(ProjectPackage projectRoot) {
        root = projectRoot;
    }

    public List<WorkPackage> getChildren(String parent) {
        if (parent.equalsIgnoreCase(root.getId())) {
            parent = ".";
        } else {
            parent = WorkPackage.unpad(parent);
        }
        List<WorkPackage> children = new ArrayList<WorkPackage>();
        Iterator<Entry<String, WorkPackage>> it = entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, WorkPackage> row = it.next();
            if (row.getValue().getParent() != null
                    & !row.getValue().getId().equalsIgnoreCase(".")) {
                
                if (row.getValue().getParent().getId().equalsIgnoreCase(parent)) {
                    children.add(row.getValue());
                }
            }
        }
        try {
            Collections.sort(children);
        } catch (ClassCastException e) {
            System.out.println("Sorting : Class Cast Exception");///???
        }
        return children;
    }

    public ProjectPackage getRoot() {
        return root;
    }

    public Set<WorkPackage> getLeafs(){
        Set<WorkPackage> set = new HashSet<WorkPackage>();
        for(WorkPackage wp : this.values()){
            if(wp.isLeaf()){set.add(wp);}
        }
        return set;
    }

    public void put(WorkPackage wp) {
        if (!containsKey(wp.getId())) {
            put(wp.getId(), wp);
        }
    }

    public void setRoot(ProjectPackage root) {
        this.root = root;
    }

}
