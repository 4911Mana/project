package comp.is.model.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.WorkpackageEntity;

public class ProjectTree extends Hashtable<String, WorkPackage> {
    private ProjectPackage root;

    public ProjectTree(ProjectPackage projectRoot) {
        root = projectRoot;
    }

    public List<WorkPackage> getChildren(String parent) {
        if (parent.equalsIgnoreCase(root.getId())) {
            parent = ".";
            System.out.println("Root is a parent " + parent);
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

    public Package getRoot() {
        return root;
    }

    // public WorkPackage add(String id, WorkPackage wp) {
    // if (!containsKey(id)) {
    // wp.setNumber(id);
    // WorkPackage old = put(id, wp);
    // return old;
    // }
    // // exception
    // return null;
    // }

    public void put(WorkPackage wp) {
        if (!containsKey(wp.getId())) {
            put(wp.getId(), wp);
        }
    }

    public void setRoot(ProjectPackage root) {
        this.root = root;
    }

}
