package comp.is.model.project;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import comp.is.model.admin.LabourGrade;

public class Budget extends Hashtable<LabourGrade, Map<String,Double>>{
//    public Double get(String key){
//        return get(LabourGrade.getGrage(key));
//    }
    public Budget(){
        Map<String, Double> p1map = new HashMap<String, Double>();
        p1map.put("available", 12D);
        p1map.put("planned", 7D);
        p1map.put("accumulated", 1D);
        put(LabourGrade.P1, p1map);
        Map<String, Double> p3map = new HashMap<String, Double>();
        p3map.put("available", 123D);
        p3map.put("accumulated", 34D);
        p3map.put("planned", 0D);
        put(LabourGrade.P3, p3map);
        System.out.println(this.entrySet());

    }
    
    public void add(LabourGrade lg, String costType, Double amount) {
        if (get(lg) != null) {
            get(lg).put(costType, amount);
        }
        else{
            Map<String, Double> value = new Hashtable<String, Double>();
            value.put(costType, amount);
            put(lg, value);
        }
       
    }
    public void addToAccumulated(LabourGrade lg, Double amount) {
        if (get(lg) != null) {
            Double prevAmount = get(lg).get("accumulated");
            get(lg).put("accumulated", (prevAmount == null) ? amount : amount + prevAmount);
            System.out.println("accumulated " + prevAmount);
        }
        else{
            Map<String, Double> value = new Hashtable<String, Double>();
            value.put("accumulated", amount);
            put(lg, value);
        }
       
    }
}
