package comp.is.model.project;

import java.io.Serializable;


public class WorkPackage extends WorkPackageEntity implements Serializable, Comparable<WorkPackage>{
    static final int LENGTH = 6;
    static final char PADDING = '0';
    private WorkPackage parent;
    boolean candidate = false;
    
    public WorkPackage(){
        super();
    }
    
    public WorkPackage(WorkPackage wp) {
        init(wp);
        parent = wp.parent;
    }
    //has to have a parent!!
    public WorkPackage(String wpNum) {
        super(wpNum);
    }
    
    public boolean isCandidate() {
        return candidate;
    }

    public void setCandidate(boolean candidate) {
        this.candidate = candidate;
    }

    public static String pad(String num) {
        String padNum = num;
        for (int i = padNum.length(); i < LENGTH; i++) {
            padNum += PADDING;
        }
        return padNum;
    }
    @Override
    @CurrentWp
    @ChildWp
    public String getNumber(){
        if(candidate) return number;
        return pad(number); 
    }
    @Override
    public void setNumber(String num){
        System.out.println("setting view wp to" + num);
        number = unpad(num);
    }
    
    
    public WorkPackage getParent() {
        return parent;
    }

    public void setParent(WorkPackage parent) {
        this.parent = parent;
    }

    public static String unpad(String num) {
        int numEnd = num.indexOf(PADDING);
        if (numEnd == -1) {
            return num;
        }
        String padNum = num.substring(0, num.indexOf(PADDING));

        return padNum;
    }
    
    public String getChildMask() {
        String mask = "";
        for (int i = 0; i < number.length() + 1; i++) {
            mask += '*';
        }
        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING; //make configurable, ?
        }
        System.out.println("Mask " + mask);
        return mask;
    }
    
    public String getParentMask() {
        String parentNum = parent.getNumber();
        if(parentNum.length() == LENGTH){return parentNum;} // should be exception
        
        String mask = "*";
        for (int i = 1; i < parentNum.length(); i++) {
            mask += "*";
        }
        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING;
        }

        return mask;
    }
    
    public boolean validLengthNum() {
        return (number.length() == parent.number.length() + 1);

    }
    
    public boolean validPrefixNum(){
        return number.matches(parent.number + "\\w");
    }
    
    @Override
    public int compareTo(WorkPackage iWp)  throws ClassCastException {
        if (!(iWp instanceof WorkPackage))
            throw new ClassCastException("A WorkPackage object expected.");
          String iWpNum = ((WorkPackage) iWp).getNumber();
          return number.compareTo(iWpNum);
        }
    public void init(WorkPackage wp){
        super.init(wp);
        parent = wp.parent;
    }
    
    public String toString(){
        return getNumber(); 
    }
    public String getDetails(){
        return super.getDetails() + " Parent: " + ((parent == null)? "null" : parent.getNumber()); 
    }
}
