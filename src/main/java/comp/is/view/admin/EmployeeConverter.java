package comp.is.view.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import comp.is.controller.project.ProjectAction;
import comp.is.model.admin.Employee;

public class EmployeeConverter implements Converter {

    private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
   

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object obj) {
        if (obj == null) {
            return null;}
        Employee e = (Employee) obj;
        System.out.println("Converter " + e.getFirstname());
        
                employees.put(e.getId(), e);
                
            return String.valueOf(e.getId());
        
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;}
        System.out.println("Converter as ogj " + submittedValue.trim());
        try {
            int id = Integer.parseInt(submittedValue);
            return employees.get(id);
            
            
        } catch(NumberFormatException exception) {
            throw new ConverterException("Not a valid employee");
        }
        
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
