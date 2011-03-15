/**
 * 
 */
package org.project.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("nav")
@SessionScoped
/**
 * This class will house and control any navigation controls needed. Typically
 * used to hold string names for destinations and action results.
 * @author Aaron Beatty
 *	@version 1.0
 */
public class NavigationControls implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String content;
	private String footer;
	private String header;
	private String options;
	
	/** */
	public NavigationControls() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 
	 * @return
	 */
	public String getFooter() {
		return footer;
	}
	/**
	 * 
	 * @return
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * 
	 * @return
	 */
	public String getOptions() {
		return options;
	}
	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		content = DirectoryBean.CONTENT_LOGIN;
		header = DirectoryBean.HEADER_LOGIN;
		options = DirectoryBean.OPTIONS_LOGIN;
		footer = DirectoryBean.FOOTER_LOGIN;
	}
	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 
	 * @param footer
	 */
	public void setFooter(String footer) {
		this.footer = footer;
	}
	/**
	 * 
	 * @param header
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * 
	 * @param options
	 */
	public void setOptions(String options) {
		this.options = options;
	}
}
