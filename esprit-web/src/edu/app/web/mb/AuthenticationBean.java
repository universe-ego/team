package edu.app.web.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.app.business.AuthenticationServiceLocal;
import edu.app.persistence.User;


@ManagedBean(name="authBean")
@SessionScoped
public class AuthenticationBean implements Serializable{
	
	@EJB
	private AuthenticationServiceLocal authenticationServiceLocal;
	
	private User user = new User();
	private boolean loggedIn = false; 

	private static final long serialVersionUID = 6710404278650523921L;
	public AuthenticationBean() {
	}
	
	public String login(){
		String navigateTo = null;
		User found = authenticationServiceLocal.authenticate(user.getLogin(), user.getPassword());
		if (found != null) {
			user = found;
			loggedIn = true;
			navigateTo = "/pages/admin/home";
		} else {
			FacesMessage message = new FacesMessage("Bad credentials!");
			FacesContext.getCurrentInstance().addMessage("login_form:login_submit", message);
			loggedIn = false;
			navigateTo = null;
		}
		return navigateTo;
	}
	
	public String logout(){
		String navigateTo = null;
		loggedIn = false;
		user = new User();
		navigateTo = "/welcome";
		return navigateTo;
	}

	public AuthenticationServiceLocal getAuthenticationServiceLocal() {
		return authenticationServiceLocal;
	}

	public void setAuthenticationServiceLocal(
			AuthenticationServiceLocal authenticationServiceLocal) {
		this.authenticationServiceLocal = authenticationServiceLocal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
	
	

}
