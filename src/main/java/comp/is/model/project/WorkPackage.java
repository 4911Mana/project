package comp.is.model.project;

import java.io.Serializable;
import java.util.Date;

import comp.is.model.project.entity.WorkpackageEntity;


public class WorkPackage extends WorkpackageEntity implements Serializable, Comparable<WorkPackage>{
    static final int LENGTH = 6;
    static final char PADDING = '0';
    private boolean candidate = false;
    private boolean openForCharges = false;
    private Date startDate;
    
    public WorkPackage(){
        super();
    }
    
    public WorkPackage(WorkPackage wp) {
        init(wp);
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
    
    public boolean isOpenForCharges() {
        return openForCharges;
    }

    public void setOpenForCharges(boolean openForCharges) {
        this.openForCharges = openForCharges;
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @CurrentWp
    @ChildWp
    public String getNumber(){
        String id = getId().getWpid();
        if(candidate) return id;
        return pad(id); 
    }
    
    public void setNumber(String num){
        System.out.println("setting view wp to" + num);
        getId().setWpid(unpad(num));
        getId().setProjid(getWpParent().getId().getProjid());
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
        for (int i = 0; i < getId().getWpid().length() + 1; i++) {
            mask += '*';
        }
        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING; //make configurable, ?
        }
        System.out.println("Mask " + mask);
        return mask;
    }
    
//    public String getParentMask() {
//        String parentNum = getWpParent().getNumber();
//        if(parentNum.length() == LENGTH){return parentNum;} // should be exception
//        
//        String mask = "*";
//        for (int i = 1; i < parentNum.length(); i++) {
//            mask += "*";
//        }
//        for (int i = mask.length(); i < LENGTH; i++) {
//            mask += PADDING;
//        }
//
//        return mask;
//    }
    
    public boolean validLengthNum() {
        return (getId().getWpid().length() == getWpParent().getId().getWpid().length() + 1);

    }
    
    public boolean validPrefixNum(){
        return getId().getWpid().matches(getWpParent().getId().getWpid() + "\\w");
    }
    
    @Override
    public int compareTo(WorkPackage iWp)  throws ClassCastException {
        if (!(iWp instanceof WorkPackage))
            throw new ClassCastException("A WorkPackage object expected.");
          String iWpNum = (iWp).getNumber();
          return getId().getWpid().compareTo(iWpNum);
        }
    
    public void init(WorkPackage wp){
        super.init(wp);
    }
    
    public String toString(){
        return getNumber(); 
    }
    public String getDetails(){
        return getNumber() + " Parent: " + ((getWpParent() == null)? "null" : getWpParent().getId().toString()); 
    }
}
