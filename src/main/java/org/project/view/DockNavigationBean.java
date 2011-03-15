package org.project.view;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
@Named("dockNav")
public class DockNavigationBean {
    
    @Inject
    private NavigationControls nav;
    
	public void logout() {
	    nav.setContent(DirectoryBean.CONTENT_LOGIN);
        nav.setOptions(DirectoryBean.OPTIONS_LOGIN);
        nav.setHeader(DirectoryBean.HEADER_LOGIN);
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .invalidateSession();
	}
	public void setupChangePassword() {
	    nav.setContent(DirectoryBean.CONTENT_CHANGE_PASSWORD);
        nav.setOptions(DirectoryBean.OPTIONS_LOGIN);
	}
	public void setupHome() {
	    nav.setContent(DirectoryBean.CONTENT_HOME);
        nav.setOptions(DirectoryBean.OPTIONS_LOGIN);
	}
	public void setupHRAdmin() {
	    nav.setContent(DirectoryBean.CONTENT_HR_ADMIN);
        nav.setOptions(DirectoryBean.OPTIONS_LOGIN);
	}
	public void setupProjectView() {
	    nav.setContent(DirectoryBean.CONTENT_PROJECT_VIEW);
        nav.setOptions(DirectoryBean.OPTIONS_PROJECT_VIEW);
	}
	public void setupSystemAdmin() {
	    nav.setContent(DirectoryBean.CONTENT_TIMESHEET);
        nav.setOptions(DirectoryBean.OPTIONS_LOGIN);
	}
	public void setupTimeSheet() {
		nav.setContent(DirectoryBean.CONTENT_TIMESHEET);
		nav.setOptions(DirectoryBean.OPTIONS_TIMESHEET);
	}
}
