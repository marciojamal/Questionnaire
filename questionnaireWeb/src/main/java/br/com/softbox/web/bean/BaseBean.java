package br.com.softbox.web.bean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Control Base
 * 
 * @author Marcio Jamal Resende
 * @version 1.0
 */
public abstract class BaseBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8654654663548L;
	private UserBean userBean;
	
	
	

	public BaseBean() {
		super();
		try {
			userBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(UserBean.MANAGED_BEAN_NAME);
		} catch (NullPointerException e) {
		}
	}
	
	public UserBean getUserBean() {
		if (userBean == null) {
			try {
				userBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(UserBean.MANAGED_BEAN_NAME);
			} catch (NullPointerException e) {
			}
			
		}
		return userBean;
	}

	
	
	/**
	 * <p>
	 * Provide a Map session context.
	 * </p>
	 * 
	 * @return session context
	 */
	public Map<String, Object> getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap();
	}

	/**
	 * <p>
	 * Provide a session context.
	 * </p>
	 * 
	 * @return session context
	 */
	public HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
	}
	
	/**
	 * <p>
	 * Provide a request context.
	 * </p>
	 * 
	 * @return resquest context
	 */
	public HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance()
		.getExternalContext().getRequest();
	}
	
	/**
	 * <p>
	 * Provide a response context.
	 * </p>
	 * 
	 * @return response context
	 */
	public HttpServletResponse getResponse(){
		return (HttpServletResponse) FacesContext.getCurrentInstance()
		.getExternalContext().getResponse();
	}
	
	
	/**
	 * <p>
	 * Get the context.
	 * </p>
	 * 
	 * @return context.
	 */
	public static FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	public ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

	}
	
	
	public ExternalContext getExternalContext(){
		return getContext().getExternalContext();
	}
	
	
	public void setSessionAttribute(String key, Object value){
		getSession().setAttribute(key, value);
	}
	public Object getSessionAttribute(String key){
		return getSession().getAttribute(key);
	}
	public void removeSessionAttribute(String key){
		getSession().removeAttribute(key);
	}
	
	protected void keepMessages(boolean keepMessage){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessage);
    }
	
	protected void displayInfoMessage(String message) {
        FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        
    }
	
	protected void displayErrorMessage(String message) {

        FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        FacesContext.getCurrentInstance().addMessage(null, fMessage);
    }
	
	protected void displayMessage(String id, String message) {
		FacesMessage fMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(id, fMessage);
	}



	


	
}
