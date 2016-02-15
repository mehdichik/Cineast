package com.MovieVault.mb;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;

import MovieVault.Persistence.CinemaTheater;
import MovieVault.Services.CineTheaterServicesLocal;

@ManagedBean
@ViewScoped
public class TheaterBean {

	@EJB
	private CineTheaterServicesLocal theaterlocal;


	private CinemaTheater cinemaTheater = new CinemaTheater();
	private List<CinemaTheater> cinemaTheaters;
	private List<CinemaTheater> filetredTheater;
	private boolean formDisplayed = false;
	
	public TheaterBean() {
		
	}
	
	@PostConstruct
	public void init(){
		
		cinemaTheaters = theaterlocal.finAllTheater();
		
	}
	public void doSaveOrUpdate(){
		
		AuthenticationBean me = (AuthenticationBean)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		cinemaTheater.setPersonne(me.getAdmin());
	theaterlocal.saveOrUpdatThearter(cinemaTheater);
	cinemaTheaters = theaterlocal.finAllTheater();
		formDisplayed =false;
		
	}
	  
	public List<CinemaTheater> getTheatersbyId(int id_admin) {

		cinemaTheaters  = theaterlocal.listTheaterByAdmin(id_admin);
		return cinemaTheaters;
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
        cinemaTheater.setLongitude((float) latlng.getLng());
        cinemaTheater.setLatitude((float) latlng.getLat());

        
    }  
      
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
	
public void doNew(){
		
	cinemaTheater = new CinemaTheater();
		formDisplayed = true;
		
	}


public void onRowSelect(SelectEvent event){
	formDisplayed= true;
	
}

public void doDelete(){
	
	
	theaterlocal.remove(cinemaTheater);
	cinemaTheaters = theaterlocal.finAllTheater();
	formDisplayed = false;
	
	}



public void doCancel(){
	
	
	cinemaTheater = new CinemaTheater();
	cinemaTheaters = theaterlocal.finAllTheater();
	formDisplayed = false;
	
	
}
	

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}


	
	public List<CinemaTheater> findPlacesByUser(int id){

		cinemaTheaters = theaterlocal.listTheaterByAdmin(id);
        return cinemaTheaters ;
        


	}


	public void handleFileUpload(FileUploadEvent event) {
		cinemaTheater.setImg(event.getFile().getContents());

	}

	public List<CinemaTheater> getFiletredTheater() {
		return filetredTheater;
	}

	public void setFiletredTheater(List<CinemaTheater> filetredTheater) {
		this.filetredTheater = filetredTheater;
	}

	public CinemaTheater getCinemaTheater() {
		return cinemaTheater;
	}

	public void setCinemaTheater(CinemaTheater cinemaTheater) {
		this.cinemaTheater = cinemaTheater;
	}

	public List<CinemaTheater> getCinemaTheaters() {
		return cinemaTheaters;
	}

	public void setCinemaTheaters(List<CinemaTheater> cinemaTheaters) {
		this.cinemaTheaters = cinemaTheaters;
	}
	
	

}
