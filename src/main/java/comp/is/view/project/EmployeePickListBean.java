package comp.is.view.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

import comp.is.model.admin.Employee;

@Named
@SessionScoped
public class EmployeePickListBean implements Serializable{
    private DualListModel<Employee> employees;
    private Employee respEng;
    private boolean rendered = false;
    private int activeTabIndex = 0;
    
    public EmployeePickListBean(){
        employees = new DualListModel<Employee>(new ArrayList<Employee>(), new ArrayList<Employee>());
    }

    public DualListModel<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(DualListModel<Employee> employees) {
        this.employees = employees;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
    
    public Employee getRespEng() {
        return respEng;
    }

    public void setRespEng(Employee respEng) {
        this.respEng = respEng;
    }

    public void arrangeEmployees(ArrayList<Employee>source, ArrayList<Employee>target, Employee engineer){
        employees = new DualListModel<Employee>(source, target);
        if(target != null & !target.isEmpty()){
            respEng = target.get(0);
        }
        else{respEng = engineer;}
    }

    public int getActiveTabIndex() {
        return activeTabIndex;
    }

    public void setActiveTabIndex(int activeTabIndex) {
        this.activeTabIndex = activeTabIndex;
    }
    public void onTabChange(TabChangeEvent event) {
        String activeTab = event.getTab().getId();
            int i = 0;

            for (UIComponent comp : event.getTab().getParent().getChildren()) {
              if (comp.getId().equals(activeTab)) {
                break;
              }
              ++i;
            }

          setActiveTabIndex(i - 1);
          System.out.println("Setting index to " + i);
      }
    
    public void onTargetChange(ValueChangeEvent event) {
        System.out.println("TargetChange New Value" + ((DualListModel<Employee>)event.getNewValue()).getTarget());
    }

}
