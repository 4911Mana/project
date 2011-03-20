package comp.is.view.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import comp.is.controller.project.ProjectActionLocal;
import comp.is.model.admin.Employee;
import comp.is.view.project.EmployeePickListBean;

public class EmployeeConverter implements Converter {

    private ProjectActionLocal pa;
    private static Map<Object, String> entities = new WeakHashMap<Object, String>();

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);
                return uuid;
            } else {
                return entities.get(entity);
            }
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
        for (Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component,
//            String submittedValue) {
//        try {
//            pa = (ProjectActionLocal) new InitialContext().lookup("java:global/project/ProjectAction");
//            System.out.println("Employee Converter " + pa);
//        } catch (NamingException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        
//        if (submittedValue.trim().equals("")) {
//            return null;
//        } else {
//            try {
//                int id = Integer.parseInt(submittedValue.trim());
////                Employee e = new Employee();
////                e.setId(id);
////                return e;
//                for (Employee e : pa.getWp().getEmployees()) {
//                    if (e.getStrId() == submittedValue) {
//                        System.out.println("Submitted val " + submittedValue + e);
//                        return e;
//                    }
//                }
//
//            } catch (NumberFormatException exception) {
//                throw new ConverterException(new FacesMessage(
//                        FacesMessage.SEVERITY_ERROR, "Conversion Error",
//                        "Not a valid Employee"));
//            }
//            
//        }
//        return null;
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent component,
//            Object value) {
//        if (value == null) {
//            return null;
//        } else {
//            return ((Employee) value).getStrId();
//        }
//    }
}
