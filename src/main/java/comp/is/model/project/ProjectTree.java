package comp.is.model.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ProjectTree extends Hashtable<String, WorkPackage> {
    private final WorkPackage root;

    public ProjectTree(WorkPackage projectRoot) {
        root = projectRoot;
    }

    public WorkPackage getRoot() {
        return root;
    }

//    public void setRoot(WorkPackage root) {
//        this.root = root;
//    }

    public WorkPackage add(String id, WorkPackage wp) {
        if (!containsKey(id)){
            wp.setNumber(id);
            WorkPackage old = put(id, wp);
            return old;
        }
        //exception
        return null;
    }
    public void put(WorkPackage wp) {
        if (!containsKey(wp.getNumber())) {
            put(wp.getNumber(), wp);
        }
    }

    public List<WorkPackage> getChildren(String parent) {
        List<WorkPackage> children = new ArrayList<WorkPackage>();
        Iterator<Entry<String, WorkPackage>> it = entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, WorkPackage> row = it.next();
            if (row.getValue().getParent().getNumber().equalsIgnoreCase(parent)) {
                children.add(row.getValue());
            }
        }
        try {
            Collections.sort(children);
        } catch (ClassCastException e) {
            System.out.println("Exception");// ??
        }
        return children;
    }

}
