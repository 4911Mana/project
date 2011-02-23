package comp.is.controller.project;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import comp.is.model.project.ChildWp;
import comp.is.model.project.CurrentWp;
import comp.is.model.project.ProjectIndexTree;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.view.project.ProjectManagerView;

@Stateful
@SessionScoped
@LocalBean
@Named("projectAction")
public class ProjectAction {

    @Produces
    @CurrentWp
    @Named("wp")
    @SessionScoped
    private static WorkPackage wp;
    @Produces
    @ChildWp
    @Named("childWp")
    @SessionScoped
    private static WorkPackage childWp;
    @Inject
    WorkPackageAction wpAction;
    @Inject
    private ProjectManagerView view;
    // Lazy loading
    private ProjectTree project;

    public ProjectAction() {
        wp = new WorkPackage();
        wp.setNumber("rootwp");
        project = new ProjectTree(wp);
        childWp = new WorkPackage();
        childWp.setCandidate(true);
        childWp.setParent(wp);
        init();
    }

    public void init() {
        WorkPackage a = new WorkPackage("a");
        a.setTitle("Layer 1");
        a.setParent(project.getRoot());
        WorkPackage aa = new WorkPackage("a");
        aa.setTitle("Layer 2");
        aa.setParent(a);
        WorkPackage ab = new WorkPackage("ab");
        ab.setTitle("Layer 3");
        ab.setParent(a);
        WorkPackage aaa = new WorkPackage("aaa");
        aaa.setTitle("Layer 4");
        aaa.setParent(aa);
        WorkPackage aab = new WorkPackage("aab");
        aab.setTitle("Layer 5");
        aab.setParent(aa);
        WorkPackage aabd = new WorkPackage("aabd");
        aabd.setTitle("Layer 6");
        aabd.setParent(aab);
        WorkPackage aaaf = new WorkPackage("aaaf");
        aaaf.setTitle("Layer 7");
        aaaf.setParent(aaa);
        WorkPackage aba = new WorkPackage("aba");
        aba.setTitle("Layer 8");
        aba.setParent(ab);

        project.put(a);
        project.put(aa);
        project.put(ab);
        project.put(aaa);
        project.put(aab);
        project.put(aabd);
        project.put(aaaf);
        project.put(aba);
        // generateWpIndexes();

    }

    public WorkPackage getRoot() {
        return project.getRoot();
    }

    public ProjectTree getProject() {
        return project;
    }

    public void setProject(ProjectTree tree) {
        this.project = tree;
    }

    public static WorkPackage getWp() {
        return wp;
    }
    

    
//    public ProjectIndexTree generateWpIndexes() {
//        ProjectIndexTree indexes = new ProjectIndexTree(getRoot().toString());
//
//        for (WorkPackage wp : project.values()) {
//            indexes.put(wp.getNumber(), wp.getParent().getNumber());
//        }
//        return indexes;
//    }

   

    public static WorkPackage getChildWp() {
        return childWp;
    }

    public WorkPackage getWpById(String wpNumber) {
        WorkPackage wp = project.get(wpNumber);
        System.out.println("Getting from wpmap: "
                + ((wp == null) ? "null" : wp.toString()));
        return wp;

    }

    public void addWp(WorkPackage wp) {
        // validate
        System.out.println(wp.toString());
        project.put(wp);
        System.out.println("Put and now Getting from wpmap: "
                + project.get(wp.getNumber()));
        System.out.println((project.get(wp.getNumber()) == null) ? "not found"
                : "finds ok");

    }

    public void printAll() {
        System.out.print(project.toString());
    }

    public boolean uniqueNum(String number) {
        return !project.containsKey(number);

    }
    
    public void reinit(){
        wp.init(new WorkPackage());
        childWp.init(new WorkPackage());
        childWp.setParent(wp);
    }
    

    public String addChild() {
        System.out.println("Saving: " + childWp.toString());
        // validate
        List<String> msgs = new ArrayList<String>();
        boolean err = false;
        // check for errors
        WorkPackage candidate = new WorkPackage(childWp);
        candidate.setNumber(candidate.getNumber().toLowerCase());
        System.out.println(childWp.getDetails());
        

        if (!uniqueNum(candidate.getNumber())) {
            msgs.add("Number is not unique.");
            err = true;
        }

        // check grandparents
        if (wp.getStartDate() != null) {

            msgs.add("Parent start date is set. Parent is a leaf.");
            err = true;

        }
        if (wp.getActCost() != null) {
            if (!wp.getActCost().isEmpty()) {
                msgs.add("Parent has charges allocated. Parent is a leaf.");
                err = true;
            }
        }
        if (wp.isOpenForCharges()) {
            msgs.add("Parent is open for charges. Parent is a leaf.");
            err = true;

        }
        if (!candidate.validLengthNum() || !candidate.validPrefixNum()) {

            msgs.add("Invalid Number");
            err = true;

        }

        // persist
        // em.persist(wp);
        if (err) {
            msgs.add("New Work Package #" + candidate.getNumber()
                    + " was not saved.");
            view.displayMsgs(msgs);
            return null;
        }
        addWp(candidate);
        view.addChildToTree(candidate.getNumber(), wp.getNumber());
        wp.init(candidate);
        childWp.init(new WorkPackage());
        childWp.setParent(wp);
        return null;
    }
}
