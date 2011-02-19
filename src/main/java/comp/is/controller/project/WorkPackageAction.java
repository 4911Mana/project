package comp.is.controller.project;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import comp.is.model.project.WorkPackage;
import comp.is.view.project.ProjectManagerView;

/**
 * Session Bean implementation class WorkPackageAction
 */
@Stateful
@SessionScoped
@LocalBean
@Named("wpAction")
public class WorkPackageAction implements WorkPackageLocal {

    @Inject
    private WorkPackage wp;
    @Inject
    private ProjectManagerView view;
    @Inject
    private ProjectAction projectAction;
    private WorkPackage childWp;
    private List<WorkPackage> wpList;
//    @PersistenceContext(unitName="primary")
//    private EntityManager em;

    public WorkPackageAction() {
        childWp = new WorkPackage("child");

    }

    @Override
    public String reinit() {
        wp.setWp(new WorkPackage());

        return null;
    }

    public List<WorkPackage> getWpList() {
        return wpList;
    }

    public void setWpList(List<WorkPackage> wpList) {
        this.wpList = wpList;
    }

    public WorkPackage getWp() {
        return wp;
    }

    public void setWp(WorkPackage iWp) {
        wp.setWp(iWp);
        System.out.println("Wp is set :" + wp.getNumber());
    }

    public void displayWP(String wpId) {
        wp = new WorkPackage(wpId);

    }

    public String addChild() {
        // validate
        List<String> msgs = new ArrayList<String>();
        boolean err = false;
        WorkPackage candidate = childWp.getCopy();
        System.out.println(candidate.toString());
        if (!projectAction.validWpNum(candidate.getNumber())) {
            msgs.add("Work Package Number is not unique.");
            err = true;
        }

        candidate.setParent(wp);
        // check grandparents
        if (wp.getStartDate() != null) {

            msgs.add("Parent Work Package #" + wp.getNumber() + " start date is set. Parent is a leaf.");
            err = true;

        }
        if (wp.getActCost() != null) {
            if(!wp.getActCost().isEmpty()) {
                msgs.add(wp.getNumber() + " has charges allocated. Parent is a leaf.");
                err = true;
        }
     }
        if (wp.isOpenForCharges()) {
            msgs.add(wp.getNumber() + " is open for charges. Parent is a leaf.");
                err = true;
        
     }
        if (candidate.getNumber().matches("\\w")) {
            msgs.add("Work Package number is invalid.");
                err = true;
        
     }
        //persist
        //em.persist(wp);
        if (err) {
            msgs.add("New Work Package #" + candidate.getNumber() + " was not saved.");
            view.displayMsgs(msgs);
            return null;
        }
        projectAction.addWp(candidate);
        view.addChildToTree(candidate.getNumber(), wp.getNumber());
        wp.setWp(candidate);
        childWp.setWp(new WorkPackage());
        System.out.println(candidate.toString());

        return null;

    }

    public void persist() {
    }

    public void delete() {
    }

    public void setChildWp(WorkPackage childWp) {
        this.childWp = childWp;
    }

    public WorkPackage getChildWp() {
        return childWp;
    }

    private boolean validWpDate(WorkPackage wp) {
        return !(wp.getStartDate().before(wp.getParent().getStartDate()));
    }
}
