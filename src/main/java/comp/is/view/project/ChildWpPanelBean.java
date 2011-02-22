package comp.is.view.project;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;

@SessionScoped
public class ChildWpPanelBean implements Serializable{
    
    private boolean rendered;
    
    public void handleClose(CloseEvent e) {
    
        e.getComponent().setRendered(true);
        e.getComponent().setTransient(true);
        FacesContext context = FacesContext.getCurrentInstance();
        e.getComponent().processUpdates(context);
        
        rendered = false;
     }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
    
    public String render(){
        rendered = true;
        return null;
    }
}
