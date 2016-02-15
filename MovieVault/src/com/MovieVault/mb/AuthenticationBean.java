package com.MovieVault.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import MovieVault.Persistence.Admin;
import MovieVault.Services.AdminServicesLocal;




@ManagedBean(name="authBean")
@SessionScoped
public class AuthenticationBean implements Serializable{
	
	private static final long serialVersionUID = -6916676537171647179L;
	
	@EJB	
	private AdminServicesLocal authenticationServiceLocal;
	
	//our model
	private String login;
	private String pwd;
	private Admin admin ;
	private List<Admin> admins;
	private boolean loggedIn;

	//
	
	public AuthenticationBean() {
	}
	
	@PostConstruct
	public void init(){
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
	}
	
	public String doLogin(){
		String navigateTo = null;
		admin = authenticationServiceLocal.authenticate(login, pwd);
		if (admin != null) {
			loggedIn = true;
			System.out.println(admin);
			System.out.println(pwd);
			authenticationServiceLocal.findAdminByLogin(login);
			navigateTo = "/admin/home?faces-redirect=true";
	}
			else {
		FacesContext.getCurrentInstance()
			.addMessage("login_form:login_submit", new FacesMessage("Bad Credentials!"));
		System.out.println(admin);
		System.out.println(pwd);
	}
		return navigateTo;
	}

	public String doLogout(){
		String navigateTo = null;
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		init();
		navigateTo = "/login?faces-redirect=true";
		return navigateTo;
	}
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
		return "/login?faces-redirect=true";
	}
	
	public String doModify(){
		authenticationServiceLocal.updateuser(admin);
		return "/admin/home?faces-redirect=true";
	}
	


	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}



	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
