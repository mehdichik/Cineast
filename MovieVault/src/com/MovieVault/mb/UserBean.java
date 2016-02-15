package com.MovieVault.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import MovieVault.Services.EventServicesLocal;
import MovieVault.Persistence.Event;
import MovieVault.Persistence.Personne;
import MovieVault.Persistence.User;
import MovieVault.Services.UserServiceLocal;

@ManagedBean
@ViewScoped
public class UserBean {

	@EJB
	private UserServiceLocal userlocal;

	private EventServicesLocal eventServiceLocal;
	private Personne pers = new Personne();
	private List<Personne> personnes;
	private List<Personne> fileteredUsers;
	private boolean formDisplayed = false;
	private Mail mail = new Mail();
	private Event event = new Event();
	private List<Event> events;
	private User user = new User();
    private List<User> users;
	
	public UserBean() {

	}

	@PostConstruct
	public void init() {

		users = userlocal.findAllUsers();

	}

	public void doNew() {

		user = new User();
		formDisplayed = true;

	}
	
	

	public String  doDelete() {

		userlocal.remove(user);
		
		init();
		return null;

	}

	
	public String doSaveOrUpdate(){
		userlocal.createUser1(user);
		init();
		return null;
			
		}
	
	public List<Personne> getEventsNotMe(int idadmin) {

		personnes = userlocal.findUsersNotMe(idadmin);
		return personnes;
	}

	public void envoiMail() {

		
		
	
			mail.setCorp("hi , " + pers.getNom() + " " + pers.getPrenom()
					+ " Ask you if you like to participate in his new Event ");
		mail.setTo(pers.getMail());
		mail.send();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Confirmation Msg", "Invitation sent.");
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	

	public void onRowSelect(SelectEvent event) {

		formDisplayed = true;
	}

	
	public Personne getPers() {
		return pers;
	}

	public void setPers(Personne pers) {
		this.pers = pers;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Personne> getFileteredUsers() {
		return fileteredUsers;
	}

	public void setFileteredUsers(List<Personne> fileteredUsers) {
		this.fileteredUsers = fileteredUsers;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public List<Event> getEvents() {

		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public String activer() {
		user.setValide(true);
		userlocal.createUser(user);
		init();
		return null;
	}

	public String deActiver() {
		user.setValide(false);
		userlocal.createUser(user);
		init();
		return null;
	}


public void handleFileUpload(FileUploadEvent event) {
	AuthenticationBean me = (AuthenticationBean)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
	me.getAdmin().setImg(event.getFile().getContents());

}

public Event getEvent() {
	return event;
}

public void setEvent(Event event) {
	this.event = event;
}

public EventServicesLocal getEventServiceLocal() {
	return eventServiceLocal;
}

public void setEventServiceLocal(EventServicesLocal eventServiceLocal) {
	this.eventServiceLocal = eventServiceLocal;
}

public List<User> getUsers() {
	return users;
}

public void setUsers(List<User> users) {
	this.users = users;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}
	
	
	
	

}
