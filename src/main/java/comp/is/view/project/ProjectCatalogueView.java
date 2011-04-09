package comp.is.view.project;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import comp.is.controller.project.ProjectAction;
import comp.is.controller.project.ProjectActionLocal;

@Named("catalogueView")
@RequestScoped
public class ProjectCatalogueView {
    @Inject
    ProjectAction projectAction;
    
    public void displayMsg(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                msg, "");
       context.addMessage(null, message);
    }

    public void displayMsgs(List<String> msgs) {
        for (String msg : msgs) {
            displayMsg(msg);
            System.out.println("Error !! " + msg);
        }
    }

}
