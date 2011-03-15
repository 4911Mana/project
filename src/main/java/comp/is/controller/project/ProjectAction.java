package comp.is.controller.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp.is.model.project.ChildWp;
import comp.is.model.project.CurrentWp;
import comp.is.model.project.ProjectPackage;
import comp.is.model.project.ProjectTree;
import comp.is.model.project.WorkPackage;
import comp.is.model.project.entity.Package;
import comp.is.model.project.entity.ProjectEntity;
import comp.is.model.project.entity.WorkpackageEntity;
import comp.is.view.project.ProjectManagerView;

@Stateful
@SessionScoped
@LocalBean
@Named("projectAction")
public class ProjectAction {

    private WorkPackage childWp;
    private Package currentP;
    @PersistenceContext(unitName = "ProjectManager")
    private EntityManager em;
    private ProjectEntity pr;
    // Lazy loading
    private ProjectTree project;
    @Inject
    private ProjectManagerView view;
    @Inject
    WorkPackageAction wpAction;

    public ProjectAction() {
    }

    public String addChild() {
        System.out.println("Saving: " + childWp.toString());
        // validate
        List<String> msgs = new ArrayList<String>();
        boolean err = false;
        // check for errors
        WorkPackage candidate = new WorkPackage(childWp);
        candidate.setParent(currentP);
        candidate.getParent().setProject(pr);
        candidate.setProject(pr);

        System.out.println("CANDIDATE " + candidate.getDetails());

        if (!uniqueNum(candidate.getId())) {
            msgs.add("Number is not unique.");
            err = true;
        }

        // }
        // if (wp.getActCost() != null) {
        // if (!wp.getActCost().isEmpty()) {
        // msgs.add("Parent has charges allocated. Parent is a leaf.");
        // err = true;
        // }
        // }
        if (currentP.isOpenForCharges()) {
            msgs.add("Parent is open for charges. Parent is a leaf.");
            err = true;

        }
        if (!candidate.validLengthNum() || !candidate.validPrefixNum()) {

            msgs.add("Invalid Work Package Number");
            err = true;

        }
        if (candidate.getStartDate() != null) {
            if (!validStartDate(candidate, candidate.getStartDate())) {

                msgs.add("Start Date must be later then parent Start Date");
                err = true;
            }
        }

        if (err) {
            msgs.add("New Work Package #" + candidate.getNumber()
                    + " was not saved.");
            view.displayMsgs(msgs);
            return null;
        }
        // persist
        WorkpackageEntity entity = new WorkpackageEntity(candidate);
        try {
            doPersist(entity);
        } catch (Exception ex) {
            msgs.add("DB connection error. New Work Package #"
                    + candidate.getNumber() + " was not saved.");
            view.displayMsgs(msgs);
            return null;
        }
        addWp(candidate);
        view.addChildToTree(candidate.getNumber(),
                (candidate.isRootChild()) ? pr.getId() : candidate.getParent()
                        .getNumber());
        setWp(candidate);
        setChildWp(new WorkPackage());
        return null;
    }

    public void addWp(WorkPackage wp) {
        // validate
        project.put(wp);

    }

    private void fillWpMap() {
        List<WorkpackageEntity> wps = pr.getWorkPackages();
        for (WorkpackageEntity wp : wps) {
            System.out.println(new WorkPackage(wp).getDetails());
            if (wp.getParent() != null & !wp.getId().equalsIgnoreCase(".")) {
                WorkPackage newWp = new WorkPackage(wp);
                project.put(newWp);
            }
        }
        System.out.print(project);
    }

    private void findAndSetRoot() {
        try {
            pr = em.find(ProjectEntity.class, "1000");
            ProjectPackage pp = new ProjectPackage(pr);
            project = new ProjectTree(pp);
        } catch (Exception e) {
            // ??
            init();
        }
    }

    @Produces
    @ChildWp
    @Named("childWp")
    @RequestScoped
    public WorkPackage getChildWp() {
        return childWp;
    }

    public ProjectTree getProject() {
        return project;
    }

    @Produces
    @CurrentWp
    @Named("wp")
    public Package getWp() {
        return currentP;
    }

    public WorkPackage getWpById(String wpNumber) {
        WorkPackage wp = project.get(wpNumber);
        return wp;
    }

    public void init() {
        // List<WorkpackageEntity> wps = new ArrayList<WorkpackageEntity>();
        // pr = new ProjectEntity();
        // pr.setId("1234567");
        // ProjectPackage pp = new ProjectPackage(pr);
        //
        // WorkPackage dot = new WorkPackage(".");
        // dot.setParent(dot);
        // WorkPackage a = new WorkPackage("a");
        // a.setTitle("Layer 1");
        // a.setParent(dot);
        // WorkPackage aa = new WorkPackage("aa");
        // aa.setTitle("Layer 2");
        // aa.setParent(a);
        // WorkPackage ab = new WorkPackage("ab");
        // ab.setTitle("Layer 3");
        // ab.setParent(a);
        // WorkPackage aaa = new WorkPackage("aaa");
        // aaa.setTitle("Layer 4");
        // aaa.setParent(aa);
        // WorkPackage aab = new WorkPackage("aab");
        // aab.setTitle("Layer 5");
        // aab.setParent(aa);
        // WorkPackage aabd = new WorkPackage("aabd");
        // aabd.setTitle("Layer 6");
        // aabd.setParent(aab);
        // WorkPackage aaaf = new WorkPackage("aaaf");
        // aaaf.setTitle("Layer 7");
        // aaaf.setParent(aaa);
        // WorkPackage aba = new WorkPackage("aba");
        // aba.setTitle("Layer 8");
        // aba.setParent(ab);
        // wps.add(dot);
        // wps.add(a);
        // wps.add(aa);
        // wps.add(ab);
        // wps.add(aaa);
        // wps.add(aab);
        // wps.add(aabd);
        // wps.add(aaaf);
        // wps.add(aba);
        // pr.setWorkPackages(wps);
        // WorkPackage root = new WorkPackage(pr);
        // for (WorkpackageEntity wp : wps) {
        // if (wp.getId().equalsIgnoreCase(".")) {
        // wp = root;
        // wp.setParent(dot);
        // }
        // }
        // project = new ProjectTree(root);

    }

    @PostConstruct
    public void initializeProject() {
        findAndSetRoot();
        fillWpMap();
        currentP = new WorkPackage(project.getRoot());
        childWp = new WorkPackage();
        //childWp.setCandidate(true);
        childWp.setParent(currentP);
    }

    public void setChildWp(WorkPackage childWp) {
        this.childWp = childWp;
    }

    public void setProject(ProjectTree tree) {
        this.project = tree;
    }

    public void setWp(Package wp) {
        currentP = wp;
    }

    public boolean uniqueNum(String number) {
        return !project.containsKey(number);

    }

    public boolean validStartDate(WorkPackage wp, Date newDate) {
        if (wp.isRootChild()) {
            return !pr.getStartDate().after(newDate);
        }
        if (wp.getParent().getStartDate() == null) {
            return validStartDate(getWpById(wp.getParent().getId()), newDate);
        } else
            return !wp.getParent().getStartDate().after(newDate);
    }

    private void doPersist(WorkpackageEntity entity) {
        em.persist(entity);
    }

    public void reinit() {
        setWp(new WorkPackage());
        setChildWp(new WorkPackage());

    }
    
    public String validParent(){
        if(currentP.isLowestLevel()){
            view.displayMsg("Work Package is a leaf");
        }
        return null;
    }
}
