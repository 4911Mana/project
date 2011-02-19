package comp.is.controller.project;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;

@Stateful
@SessionScoped
@LocalBean
@Named("projectAction")
public class ProjectAction {

    
    @Inject 
    WorkPackageAction wpAction;
 // Lazy loading
    Map<String, WorkPackage> wpMap;
    WorkPackage root;
    private ProjectTree wpIndexes;
    
    public ProjectAction(){
      //for now we assume that wpMap is available
        root = new WorkPackage("root wp");
        wpMap = new Hashtable<String, WorkPackage>();
        
        init(); 
    }
    
    public void init() {
        WorkPackage wp1 = new WorkPackage("a");
        wp1.setTitle("Layer 1");
        wp1.setParent(root);
        WorkPackage wp2 = new WorkPackage("wp2");
        wp2.setTitle("Layer 2");
        wp2.setParent(wp1);
        WorkPackage wp3 = new WorkPackage("wp3");
        wp3.setTitle("Layer 3");
        wp3.setParent(wp2);
        WorkPackage wp4 = new WorkPackage("wp4");
        wp4.setTitle("Layer 4");
        wp4.setParent(wp1);
        WorkPackage wp5 = new WorkPackage("wp5");
        wp5.setTitle("Layer 5");
        wp5.setParent(wp2);
        WorkPackage wp6 = new WorkPackage("wp6");
        wp6.setTitle("Layer 6");
        wp6.setParent(wp1);
        WorkPackage wp7 = new WorkPackage("wp7");
        wp7.setTitle("Layer 7");
        wp7.setParent(wp6);
        WorkPackage wp8 = new WorkPackage("wp8");
        wp8.setTitle("Layer 8");
        wp8.setParent(wp1);
       
        wpMap.put(wp1.getNumber(), wp1);
        wpMap.put(wp2.getNumber(), wp2);
        wpMap.put(wp3.getNumber(), wp3);
        wpMap.put(wp4.getNumber(), wp4);
        wpMap.put(wp5.getNumber(), wp5);
        wpMap.put(wp6.getNumber(), wp6);
        wpMap.put(wp7.getNumber(), wp7);
        wpMap.put(wp8.getNumber(), wp8);
        generateWpIndexes();
        
    }
    
    public WorkPackage getRoot() {
        return root;
    }

    public void setRoot(WorkPackage root) {
        this.root = root;
    }

    public Map<String, WorkPackage> getProject() {
        return new HashMap<String, WorkPackage>();
    }

    public ProjectTree generateWpIndexes() {
        ProjectTree project = new ProjectTree(root.getNumber());

        for (WorkPackage wp : wpMap.values()) {
            project.put(wp.getNumber(), wp.getParent().getNumber());
        }

        wpIndexes = project;
        return wpIndexes;
    }

    public WorkPackage getWpById(String wpNumber) {
        System.out.println("Get by Id: " + wpNumber+ " / " + (wpMap.containsKey(wpNumber) ? "founds ok" : "not found"));
        WorkPackage wp = wpMap.get(wpNumber);
        System.out.println("Getting from wpmap: " +  ((wp == null)? "null" : wp.toString()));
        return wpMap.get(wpNumber);

    }
 
    public void addWp(WorkPackage wp) {
        // validate
        System.out.println(wp.toString());
        wpMap.put(wp.getNumber(), wp);
        System.out.println("Put and now Getting from wpmap: " + wpMap.get(wp.getNumber()).toString());
        System.out.println((wpMap.get(wp.getNumber())== null)? "not found" : "finds ok");
        
     }
    public void printAll(){
        System.out.print(wpMap.toString());
    }

    public boolean validWpNum(String number) {
        return !wpMap.containsKey(number);
        
    }
    
}
