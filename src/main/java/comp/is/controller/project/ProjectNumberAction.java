package comp.is.controller.project;


import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import comp.is.model.project.entity.ProjectEntity;


@Singleton
//@Stateless
@LocalBean
@PermitAll
public class ProjectNumberAction implements ProjectNumberActionLocal {
    final String PROJ_NUM_SQL = "SELECT p.id FROM PROJECT p";
    final String PROJ_SQL = "SELECT p FROM ProjectEntity p";
    
    @PersistenceContext(unitName="ProjectManager")
    private EntityManager em;
    
   public boolean projectIsUnique(String id){
       
        Query query = em.createNativeQuery(PROJ_NUM_SQL);
        final List<String> result = query.getResultList();
        System.err.println("All projects: " + result);
        return !result.contains(id);
    }
    
    public List<String> getProjNumList() {
        List<String> result = null;
        try{
        Query query = em.createNativeQuery(PROJ_NUM_SQL);
        result = query.getResultList();
        System.err.println("All projects: " + result);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public List<ProjectEntity> getProjList() {
 
        Query query = em.createQuery(PROJ_SQL);
        final List<ProjectEntity> result = query.getResultList();
        return result;
    }
}
