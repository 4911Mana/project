package comp.is.view.project;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;

@Named("childPanel")
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

    public String render(){
        rendered = true;
        return null;
    }
    
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
}
