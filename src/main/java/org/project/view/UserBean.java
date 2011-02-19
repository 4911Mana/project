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
	
	private String userID;
	private String password;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Interceptors(LoginInterceptor.class)
	public boolean login() {
		
		System.err.println("UserBean login method invoked");
		//return DirectoryBean.CONTENTLOGIN;
		
		return true;
		
	}
	
	//@PostConstruct
	//private void init() {
	//	this.userID = "";
	//	this.password = "";
	//}
	
	
}
