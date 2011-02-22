package comp.is.view.admin;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import comp.is.controller.project.entities.Employee;


public class EmployeeConverter implements Converter{

       List<Employee> EmployeeList;

       public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
           if (submittedValue.trim().equals("")) {
               return null;
           } else {
               try {
                   
                   for (Employee e : EmployeeList) {
                       if (Integer.toString(e.getEmpid()) == submittedValue) {
                           return e;
                       }
                   }
                   
               } catch(NumberFormatException exception) {
                   throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Employee"));
               }
           }

           return null;
       }

       public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
           if (value == null) {
               return null;
           } else {
               return String.valueOf(((Employee) value).getEmpid());
           }
       }
}
