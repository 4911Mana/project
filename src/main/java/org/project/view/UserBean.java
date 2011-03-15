package org.project.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;

@Named("user")
@SessionScoped
public class UserBean implements Serializable {
	
	//@Inject
	//private NavigationControls nav;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String password;
	private String userID;
	
	public String getPassword() {
		return password;
	}
	public String getUserID() {
		return userID;
	}
	@Interceptors(LoginInterceptor.class)
	public boolean login() {
		
		System.err.println("UserBean login method invoked");
		//return DirectoryBean.CONTENTLOGIN;
		
		return true;
		
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	//@PostConstruct
	//private void init() {
	//	this.userID = "";
	//	this.password = "";
	//}
	
	
}
