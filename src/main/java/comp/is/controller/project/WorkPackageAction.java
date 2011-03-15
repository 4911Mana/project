package comp.is.controller.project;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp.is.model.project.ChildWp;
import comp.is.model.project.CurrentWp;
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
    @ChildWp
    private static WorkPackage childWp;
    @Inject
    @CurrentWp
    private static Package wp;
    @Inject
    private ProjectAction projectAction;
    @Inject
    private ProjectManagerView view;

    // @PersistenceContext(unitName="primary")
    // private EntityManager em;

    public WorkPackageAction() {

    } 

//    public void persist() {
//    }
//
//    public void delete() {
//    }
//
//    private boolean validWpDate(WorkPackage wp) {
//        return !(wp.getStartDate().before(wp.getParent().getStartDate()));
//    }
}
