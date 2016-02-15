package com.MovieVault.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RateEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.context.RequestContext;

import MovieVault.Persistence.CinemaTheater;
import MovieVault.Services.CineTheaterServicesLocal;



@ManagedBean(name="tableBean")
@ViewScoped

public class TableBean{
	


	@EJB
	private CineTheaterServicesLocal theaterlocal;

	
	private CinemaTheater cinemaTheater = new CinemaTheater();
	private List<CinemaTheater> cinemaTheaters;


	private CinemaTheater selectedTheater;
	AuthenticationBean me = (AuthenticationBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
//	private MapModel advancedModel;  


	public TableBean() {

		
	  



	}
	
//	public MapModel getAdvancedModel() { 
//		  advancedModel = new DefaultMapModel();  
//		    LatLng coord1 = new LatLng(selectedPlace.getLatitude(),selectedPlace.getLongitude());
//		    advancedModel.addOverlay(new Marker(coord1, "Konyaalti"));
//	    return advancedModel;  
//	} 
	@PostConstruct
	public void init(){
		

		cinemaTheaters = theaterlocal.finAllTheater();
		

//		comments=pageServiceLocal.findAllComments();

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

	public CinemaTheater getSelectedTheater() {
		return selectedTheater;
	}

	public void setSelectedTheater(CinemaTheater selectedTheater) {
		this.selectedTheater = selectedTheater;
	}

	public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
 
        FacesContext.getCurrentInstance().addMessage(null, message);
        int rate=(Integer) rateEvent.getRating();
        
        theaterlocal.rateTheater(selectedTheater,rate );
        
    }


    public void handleToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fieldset Toggled", "Visibility:" + event.getVisibility());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	
	
	public int showRate(){
		int a =selectedTheater.getRate();
		int b=selectedTheater.getNb_rate();
	return	a/b;
		
	}


	
	public void handleFileUpload(FileUploadEvent event) {
		AuthenticationBean me = (AuthenticationBean)
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
		me.getAdmin().setImg(event.getFile().getContents());

	}


	
	

	
	
}
		