/**
 * 
 */
package org.project.view;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Aaron
 * 
 */
public class LoginInterceptor implements Serializable{
	
	@Inject
	private NavigationControls nav;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AroundInvoke  
	public Object log(InvocationContext ctx) throws Exception   
	{   
		System.err.println("*** TracingInterceptor intercepting " + ctx.getMethod().getName());   
		
		long start = System.currentTimeMillis();
	    //String param = (String)ctx.getParameters()[0]; 
	    
	    //if (param == null)   
	    //	ctx.setParameters(new String[]{"default"});   
	    nav.setContent(DirectoryBean.CONTENT_HOME);
	    nav.setHeader(DirectoryBean.HEADER_HOME);
		
	    try {   
	    	return ctx.proceed();    
	    } catch(Exception e) {   
	    	throw e;   
	    } finally {   
	    	long time = System.currentTimeMillis() - start;   
	        String method = ctx.getClass().getName();   
	        System.err.println("*** TracingInterceptor invocation of " + method + " took " + time + "ns");   
	    }   
	}
}
