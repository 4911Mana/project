package comp.is.model.project;

import java.io.Serializable;


public class WorkPackage extends WorkPackageEntity implements Serializable, Comparable<WorkPackage>{
    static final int LENGTH = 6;
    static final char PADDING = ' ';
    private WorkPackage parent;
    
    public WorkPackage(){
        super();
    }
    
    public WorkPackage(WorkPackage childWp) {
        init(childWp);
    }

    public WorkPackage(String wpNum) {
        super(wpNum);
    }

    public static String pad(String num) {
        String padNum = num;
        for (int i = padNum.length(); i < LENGTH; i++) {
            padNum += PADDING;
        }
        return padNum;
    }
    @Override
    public String getNumber(){
        System.out.println("getting view wp" + number);
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
        System.out.println("Getting a mask for " + number);
        String parentNum = parent.getNumber();
        if(parentNum.length() == LENGTH){return parentNum;} // should be exception
        String mask = parentNum + 'a';
        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING;
        }

        return mask;
    }
    
    public String getParentMask() {
        String parentNum = parent.getNumber();
        if(parentNum.length() == LENGTH){return parentNum;} // should be exception
        
        String mask = "a";
        for (int i = 1; i < parentNum.length(); i++) {
            mask += "a";
        }
        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING;
        }

        return mask;
    }

    @Override
    public int compareTo(WorkPackage iWp)  throws ClassCastException {
        if (!(iWp instanceof WorkPackage))
            throw new ClassCastException("A WorkPackage object expected.");
          String iWpNum = ((WorkPackage) iWp).getNumber();
          return iWpNum.compareTo(number);
        }

    public String toString(){
        return super.toString() + ((parent == null)? "null" : parent.getNumber()); 
    }

}
