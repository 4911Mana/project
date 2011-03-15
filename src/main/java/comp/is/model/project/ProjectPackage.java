package comp.is.model.project;

import comp.is.model.project.entity.Package;

public class ProjectPackage extends Package {
    final WorkPackage rootFlag;
    public ProjectPackage(Package root) {
        init(root);
        rootFlag = new WorkPackage();
        rootFlag.setId(".");
    }


    public String getChildMask() {
        String mask = "*";
       
        for (int i = mask.length(); i < LENGTH; i++) {
            mask += PADDING; // make configurable, ?
        }
        System.out.println("Mask " + mask);
        return mask;
    }
    
    public String getNumber() {
        return id;
    }


    public WorkPackage getRootFlag() {
        return rootFlag;
    }
    
}
