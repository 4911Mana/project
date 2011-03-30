package comp.is.view.project;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comp.is.controller.project.ProjectActionLocal;

@Named("catalogueView")
@RequestScoped
public class ProjectCatalogueView {
    @Inject
    ProjectActionLocal projectAction;
    
    

}
