package comp.is.model.admin;

public enum LabourGrade {
    DS, JS, P1, P2, P3, P4, P5, P6;
    
    public static LabourGrade getGrage(String s){
        if(s.equalsIgnoreCase("P1")) return P1; 
        if(s.equalsIgnoreCase("P2")) return P2;
        if(s.equalsIgnoreCase("P3")) return P3;
        if(s.equalsIgnoreCase("P4")) return P4; 
        if(s.equalsIgnoreCase("P5")) return P5;
        if(s.equalsIgnoreCase("P6")) return P6;
        if(s.equalsIgnoreCase("DS")) return DS;
        if(s.equalsIgnoreCase("JS")) return JS;
        return null; 
    }
}
