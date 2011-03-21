package comp.is.view.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import comp.is.model.admin.Employee;

public class EmployeeConverter implements Converter {

    private static Map<String, Employee> employees = new HashMap<String, Employee>();

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object obj) {
        if (obj == null) {
            return null;}
        Employee e = (Employee) obj;
        synchronized (employees) {
            if (!employees.containsKey(e.getStrId())) {
                employees.put(e.getStrId(), e);
            }
            return e.getStrId();
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;}
        return employees.get(submittedValue.trim());
    }

    // @Override
    // public Object getAsObject(FacesContext facesContext, UIComponent
    // component,
    // String submittedValue) {
    // try {
    // pa = (ProjectActionLocal) new
    // InitialContext().lookup("java:global/project/ProjectAction");
    // System.out.println("Employee Converter " + pa);
    // } catch (NamingException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
    //
    // if (submittedValue.trim().equals("")) {
    // return null;
    // } else {
    // try {
    // int id = Integer.parseInt(submittedValue.trim());
    // // Employee e = new Employee();
    // // e.setId(id);
    // // return e;
    // for (Employee e : pa.getWp().getEmployees()) {
    // if (e.getStrId() == submittedValue) {
    // System.out.println("Submitted val " + submittedValue + e);
    // return e;
    // }
    // }
    //
    // } catch (NumberFormatException exception) {
    // throw new ConverterException(new FacesMessage(
    // FacesMessage.SEVERITY_ERROR, "Conversion Error",
    // "Not a valid Employee"));
    // }
    //
    // }
    // return null;
    // }
    //
    // @Override
    // public String getAsString(FacesContext facesContext, UIComponent
    // component,
    // Object value) {
    // if (value == null) {
    // return null;
    // } else {
    // return ((Employee) value).getStrId();
    // }
    // }
}
