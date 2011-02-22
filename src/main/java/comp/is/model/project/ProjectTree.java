package comp.is.model.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


public class ProjectTree extends HashMap<String, String>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String root;
    
    public ProjectTree(String rootwp){
        root = rootwp;
    }
    public String getRoot() {
        return root;
    }

    public void setRoot(String rootwp) {
        root = rootwp;
    }

    public void addChild(String childwp, String parentwp){
       if(containsKey(parentwp) ){
           put(childwp, parentwp);
       }
    }
    
    public List<String> getChildren(String parentId){
        List<String> children = new ArrayList<String>();
        Iterator<Entry<String, String>> it  = entrySet().iterator();
        while(it.hasNext()){
            Entry<String, String> row = it.next();
            if(row.getValue().equalsIgnoreCase(parentId)){
                children.add(row.getKey());
            }
        }
        return children;
    }
    
    
}
