package comp.is.model.project;

import comp.is.model.admin.LabourGrade;

public class PlannedBudgetEntry {

    private LabourGrade lg;
    private Double currentAmount;
    private Double adjustmentAmount;
    public Double getCurrentAmount() {
        return currentAmount;
    }
    
    public LabourGrade getLg() {
        return lg;
    }

    public void setLg(LabourGrade lg) {
        this.lg = lg;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }
    public Double getAdjustmentAmount() {
        return adjustmentAmount;
    }
    public void setAdjustmentAmount(Double adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }
    
}
