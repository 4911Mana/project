package comp.is.model.project;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import comp.is.model.admin.LabourGrade;

public class PlannedBudgetList extends
        Hashtable<LabourGrade, PlannedBudgetEntry> {

    public PlannedBudgetList(){
        init();
    }
    public void addToPlanned(LabourGrade lg, PlannedBudgetEntry entry) {
        Double prevAmount = get(lg).getCurrentAmount();
        PlannedBudgetEntry newEntry = new PlannedBudgetEntry();
        if (prevAmount == null) {
            newEntry.setCurrentAmount(entry.getCurrentAmount());
        } else {
            newEntry.setCurrentAmount(entry.getCurrentAmount() + prevAmount);
        }
        put(lg, newEntry);
    }

    public void addAllToPlanned(Hashtable<LabourGrade, PlannedBudgetEntry> init) {
        for (Map.Entry<LabourGrade, PlannedBudgetEntry> e : init.entrySet())
            addToPlanned(e.getKey(), e.getValue());

    }

    public void init() {
        for (LabourGrade grade : LabourGrade.values()) {
            PlannedBudgetEntry newEntry = new PlannedBudgetEntry(grade);
            put(grade, newEntry);
        }
    }
}
