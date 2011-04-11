package comp.is.model.project;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import comp.is.model.admin.LabourGrade;
import comp.is.model.project.entity.WorkPackageBudgetEntity;

public class Budget extends
        Hashtable<LabourGrade, Hashtable<String, Budget.RateAmountPair>> {
    private boolean plannedCostSet = false;
    

    public Budget() {
        try {
            reinitType("accumulated");
       
        reinitType("planned");
        reinitType("tocomplete");
        reinitType("initplanned");
        } catch (BudgetTypeMismatchException e) {
            System.err.println(e.toString());
        }

    }

    public boolean isPlannedCostSet() {
        return plannedCostSet;
    }

    public void add(LabourGrade lg, String costType, Double hrsAmount, Double dolAmount) throws BudgetTypeMismatchException {
        if (!costType.equalsIgnoreCase("accumulated")
                & !costType.equalsIgnoreCase("planned")
                & !costType.equalsIgnoreCase("tocomplete")
                & !costType.equalsIgnoreCase("available")
                & !costType.equalsIgnoreCase("initplanned")) {
            throw new BudgetTypeMismatchException("Not a valid cost type: " + costType);
        }
        if (get(lg) != null) {
            get(lg).put(costType, new Budget.RateAmountPair(hrsAmount, dolAmount));
        } else {
            Hashtable<String, Budget.RateAmountPair> value = new Hashtable<String, Budget.RateAmountPair>();
            value.put(costType, new Budget.RateAmountPair(hrsAmount, dolAmount));
            put(lg, value);
            if (!plannedCostSet & costType.equalsIgnoreCase("planned")
                    & hrsAmount > 0) {
                plannedCostSet = true;
            }
        }
    }

//    public void addToAccumulated(LabourGrade lg, Double amount) {
//        Budget.RateAmountPair pair = new Budget.RateAmountPair();
//        if (get(lg) != null) {
//            Double prevAmount = get(lg).get("accumulated").hrsValue;
//
//            if ((prevAmount == null)) {
//                pair.setHrsVal(amount);
//            } else {
//                pair.setHrsVal(prevAmount + amount);
//            }
//            get(lg).put("accumulated", pair);
//            System.out.println("accumulated " + prevAmount);
//        } else {
//            Hashtable<String, Budget.RateAmountPair> value = new Hashtable<String, Budget.RateAmountPair>();
//            pair.setHrsVal(amount);
//            value.put("accumulated", pair);
//            put(lg, value);
//        }
//    }

    public void addToSumType(String type, LabourGrade lg, Double hrsAmount, Double dolAmount) throws BudgetTypeMismatchException {
        if (!type.equalsIgnoreCase("accumulated")
                & !type.equalsIgnoreCase("planned")
                & !type.equalsIgnoreCase("tocomplete")
                & !type.equalsIgnoreCase("available")
                & !type.equalsIgnoreCase("initplanned")) {
            throw new BudgetTypeMismatchException("Not a valid cost type: " + type);
        }
        if(lg == null || type == null ){return;}
        if (get(lg) != null) {
            Double prevHrsAmount = get(lg).get(type).getHrsVal();
            Double prevDolAmount = get(lg).get(type).getDolVal();
            get(lg).put(
                    type,
                    (prevHrsAmount == null) ? new Budget.RateAmountPair(hrsAmount, dolAmount)
                            : new Budget.RateAmountPair(hrsAmount + prevHrsAmount, dolAmount + prevDolAmount));
            System.out.println("Add all to sum type " + type + prevHrsAmount + "/ adding "+ hrsAmount);
        } else {
            Hashtable<String, Budget.RateAmountPair> value = new Hashtable<String, Budget.RateAmountPair>();
            value.put(type, new Budget.RateAmountPair(hrsAmount, dolAmount));
            put(lg, value);
        }
    }

    public Double getPannedForGrade(LabourGrade grade) {
        return this.get(grade).get("planned").hrsValue;
    }

    public void reinitType(String type) throws BudgetTypeMismatchException {
        for (LabourGrade grade : LabourGrade.values()) {
            add(grade, type, new Double(0), new Double(0));
        }
    }

    public void addAllToSumType( String type,
            Hashtable<LabourGrade, Budget.RateAmountPair> init) throws BudgetTypeMismatchException {
        for (Map.Entry<LabourGrade, Budget.RateAmountPair> e : init.entrySet())
            addToSumType(type, e.getKey(), e.getValue().getHrsVal(), e.getValue().getDolVal());
    }

    public Hashtable<LabourGrade, Budget.RateAmountPair> getBudgetForType(
            String type) throws BudgetTypeMismatchException {
        if (!type.equalsIgnoreCase("accumulated")
                & !type.equalsIgnoreCase("planned")
                & !type.equalsIgnoreCase("tocomplete")
                & !type.equalsIgnoreCase("available")
                & !type.equalsIgnoreCase("initplanned")) {
            throw new BudgetTypeMismatchException("Not a valid cost type: " + type);
        }
        System.out.println("Getting budget for type : " + type);
        Hashtable<LabourGrade, Budget.RateAmountPair> initP = new Hashtable<LabourGrade, Budget.RateAmountPair>();
        
        for (LabourGrade grade : LabourGrade.values() ) {
            if(get(grade).get(type) == null){ reinitType(type);}
            System.out.println("Getting budget for type : " + this.get(grade).get(type));
            System.out.println("Getting budget for type : " + this.get(grade).get(type).getDolVal());
            initP.put(grade, get(grade).get(type));
//            if(e.getValue().get(type) != null){
//            initP.put(e.getKey(), e.getValue().get(type));
//            }
        }
        if(initP.isEmpty()){ System.out.println("returning empty budget for type " + type);}
        return initP;
    }

    public class RateAmountPair {
        private Double hrsValue;
        private Double dolValue;

        RateAmountPair() {
            hrsValue = new Double(0);
            dolValue = new Double(0);
        }

        RateAmountPair(Double hrs, Double d) {
            this.hrsValue = hrs;
            this.dolValue = d;
        }

        public Double getHrsVal() {
            return hrsValue;
        }

        public void setHrsVal(Double amount) {
            this.hrsValue = amount;
        }

        public Double getDolVal() {
            return dolValue;
        }

        public void setDolVal(Double rate) {
            this.dolValue = rate;
        }

    }
    
    public void print(){
        for (LabourGrade grade : LabourGrade.values() ) {     
                System.out.println(grade + " accumulated dol:" + this.get(grade).get("accumulated").getDolVal());
                System.out.println(grade + " accumulated hrs:" + this.get(grade).get("accumulated").getHrsVal());
                System.out.println(grade + " tocomplete dol:" + this.get(grade).get("tocomplete").getDolVal());
                System.out.println(grade + " tocomplete hrs:" + this.get(grade).get("tocomplete").getDolVal());
                System.out.println(grade + " planned dol:" + this.get(grade).get("initplanned").getDolVal());
                System.out.println(grade + " planned dol:" + this.get(grade).get("initplanned").getDolVal());
           
        }
    }
}
