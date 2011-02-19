package comp.is.model.project;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class ProjectTree extends HashMap<String, String>{
    String root;
    
    public ProjectTree(String projectId){
        root = projectId;
    }
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void addChild(String id, String parentId){
       if(containsKey(parentId) ){
           put(id, parentId);
       }
    }
    
    public List<String> getChildren(String parent){
        List<String> children = new ArrayList<String>();
        Iterator<Entry<String, String>> it  = entrySet().iterator();
        while(it.hasNext()){
            Entry<String, String> row= it.next();
            if(row.getValue().equalsIgnoreCase(parent)){
                children.add(row.getKey());
            }
        }
        return children;
    }
    
    
}
