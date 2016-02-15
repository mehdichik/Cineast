package com.MovieVault.mb;

import java.util.Date;
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
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;

import MovieVault.Persistence.Event;
import MovieVault.Persistence.Ticket;
import MovieVault.Services.EventServicesLocal;
import MovieVault.Services.TicketServiceLocal;


@ManagedBean
@ViewScoped
public class EventsBean {

	@EJB
	private EventServicesLocal eventServiceLocal;
	private Event event1 = new Event();

	@EJB
	private TicketServiceLocal ticketLocal;
	private Ticket ticket = new Ticket();

	private List<Event> events;
	private List<Event> fileteredPlaces;
	private boolean formDisplayed = false;
	private Mail mail = new Mail();
	private Date date = new Date();
	private Date date2 = new Date();
	private boolean disable = false;
	private boolean disable2 = true;
	
	private boolean block ;

	private String radio;
	private String input1;
	private String input2;

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public EventsBean() {

	}

	@PostConstruct
	public void init() {

		events = eventServiceLocal.finAllEvent();

	}
	
	public void onStateChange(StateChangeEvent event) {  
        LatLngBounds bounds = event.getBounds();  
        int zoomLevel = event.getZoomLevel();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));  
    }  
      
    public void onPointSelect(PointSelectEvent event) {  
        LatLng latlng = event.getLatLng();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng())); 
        event1.setLongitude((float) latlng.getLng());
        event1.setLatitude((float) latlng.getLat());

        
    }  
      
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
	public void handleFileUpload(FileUploadEvent event) {
				
		event1.setImg(event.getFile().getContents());

	}

	public void doSaveOrUpdate() {

		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");
		event1.setPersonne(me.getAdmin());
		if ((event1.getBegin_date().before(event1.getEnd_date()))) {
			
			event1.setBlocked(block);
			event1.setTypeevt(radio);
			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;
		}

		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Alert ", "Verify the Date.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public Event getEvent() {
		return event1;
	}

	public void setEvent(Event event) {
		this.event1 = event;
	}

	public void doNew() {

		event1 = new Event();
		formDisplayed = true;

	}

	public void onRowSelect(SelectEvent event) {
		formDisplayed = true;

		if (isrendred3()) {
			setDisable(true);
			setDisable2(false);
		}

		else {
			setDisable(false);
			setDisable2(true);
		}

	}

	public void doDelete() {

		eventServiceLocal.remove(event1);
		events = eventServiceLocal.finAllEvent();
		formDisplayed = false;

	}

	public void doCancel() {

		event1 = new Event();
		events = eventServiceLocal.finAllEvent();
		formDisplayed = false;

	}

	public void reserver() {

		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		int i = event1.getNbrTicket();

		if ((i > 0) && (event1.getTypeevt().equals("payant") ) && (event1.isBlocked()==false)    ) {

			event1.setNbrTicket(i - 1);
			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;

			mail.setCorp("you are subscrib in event   " + event1.getName()
					+ "  in   " + event1.getAddress());
			mail.setTo(me.getAdmin().getMail());
			mail.send();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation mail", "Participation done with success.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ticket.setEvent(event1);
			ticket.setPersonne(me.getAdmin());
			ticketLocal.addTicket(ticket);

		}

		else if ((i == 0) && (event1.getTypeevt().equals("gratuit"))) {

			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;

			mail.setCorp("you are subscrib in event   " + event1.getName()
					+ "in   " + event1.getAddress());
			mail.setTo(me.getAdmin().getMail());
			mail.send();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation Msg", "Participation done with success.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);

			ticket.setEvent(event1);
			ticket.setPersonne(me.getAdmin());
			ticketLocal.addTicket(ticket);
		}

		else {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation Msg", "Participation denied.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}

	public void Annuler() {

		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		int i = event1.getNbrTicket();
		if ((event1.getTypeevt().equals("payant"))) {
			event1.setNbrTicket(i + 1);
			eventServiceLocal.saveOrUpdateEvent(event1);
			events = eventServiceLocal.finAllEvent();
			formDisplayed = false;

			mail.setCorp("you are annuler your subscrib in event   "
					+ event1.getName() + "  in   " + event1.getAddress());
			mail.setTo(me.getAdmin().getMail());
			mail.send();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Confirmation mail", "Cancel  done with success.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);

		}

	}

	public Boolean isrendred() {

		int i = event1.getNbrTicket();
		return (i > 0) && (event1.getTypeevt().equals("payant"));
	}

	public Boolean isrendred1() {
		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		return eventServiceLocal.listEventByAdmin(me.getAdmin().getId()).size() != 0;
	}

	public Boolean isrendred2() {
		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		return ticketLocal.listTicketByUserEvent(me.getAdmin().getId(),
				event1.getIdEvent()).size() != 0;
	}

	public Boolean isrendred3() {
		AuthenticationBean me = (AuthenticationBean) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("authBean");

		return ticketLocal.listTicketByUserEvent(me.getAdmin().getId(),
				event1.getIdEvent()).size() == 0;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Event> getFileteredPlaces() {
		return fileteredPlaces;
	}

	public void setFileteredPlaces(List<Event> fileteredPlaces) {
		this.fileteredPlaces = fileteredPlaces;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Event> getEventsbyId(int idadmin) {

		events = eventServiceLocal.listEventByAdmin(idadmin);
		return events;
	}

	public List<Event> getEventsNotMe(int idadmin) {

		events = eventServiceLocal.listEventNotMe(idadmin);
		return events;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public boolean isDisable2() {
		return disable2;
	}

	public void setDisable2(boolean disable2) {
		this.disable2 = disable2;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

}
