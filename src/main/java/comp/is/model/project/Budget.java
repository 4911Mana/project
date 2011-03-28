package comp.is.model.project;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import comp.is.model.admin.LabourGrade;

public class Budget extends Hashtable<LabourGrade, Map<String, Double>> {
    private boolean plannedCostSet = false;

    public Budget() {
        add(LabourGrade.P1, "available", 34D);
        add(LabourGrade.P1, "planned", 0D);

    }
    
    public boolean isPlannedCostSet() {
        return plannedCostSet;
    }

    public void add(LabourGrade lg, String costType, Double amount) {
        if (!costType.equalsIgnoreCase("accumulated")
                & !costType.equalsIgnoreCase("planned")
                & !costType.equalsIgnoreCase("tocomplete")
                & !costType.equalsIgnoreCase("available")) {
            System.err.println("Not a valid cost type: " + costType);
            return;
        }
        if (get(lg) != null) {
            get(lg).put(costType, amount);
        } else {
            Map<String, Double> value = new Hashtable<String, Double>();
            value.put(costType, amount);
            put(lg, value);
            if (!plannedCostSet & costType.equalsIgnoreCase("planned") & amount > 0) {
                plannedCostSet = true;
            }
        }

    }

    public void addToAccumulated(LabourGrade lg, Double amount) {
        if (get(lg) != null) {
            Double prevAmount = get(lg).get("accumulated");
            get(lg).put("accumulated",
                    (prevAmount == null) ? amount : amount + prevAmount);
            System.out.println("accumulated " + prevAmount);
        } else {
            Map<String, Double> value = new Hashtable<String, Double>();
            value.put("accumulated", amount);
            put(lg, value);
        }
    }
}
