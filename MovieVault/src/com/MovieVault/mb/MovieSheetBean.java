package com.MovieVault.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import MovieVault.Persistence.BoxOffice;
import MovieVault.Persistence.CinemaTheater;
import MovieVault.Persistence.MovieCrew;
import MovieVault.Persistence.MovieSheet;
import MovieVault.Persistence.Personne;
import MovieVault.Services.BoxOfficeServicesLocal;
import MovieVault.Services.MovieCrewLocal;
import MovieVault.Services.MovieSheetLocal;
import MovieVault.Services.UserServiceLocal;

@ManagedBean
@ViewScoped
public class MovieSheetBean {
	private List<MovieSheet> sheet = new ArrayList<MovieSheet>();
	private List<MovieCrew> lsCrew;
	private boolean form = false;
	private MovieSheet selectedMovie;
	private MovieSheet movie = new MovieSheet();
	private List<Personne> p = new ArrayList<Personne>();
	private int j;
	private MovieCrew selectedCrew;
	private List<MovieSheet>  filtredMovie;
	
	
	
	
	@EJB
	MovieSheetLocal local;
	@EJB
	UserServiceLocal ulocal;
//	@EJB
//	MovieCrewLocal crewLocal;
	
	
	@EJB
	private BoxOfficeServicesLocal boxX;
	

	
	private BoxOffice boxOffice = new BoxOffice() ;
	private List<BoxOffice> boxOffices;
	
	public MovieSheet getMovie() {
		return movie;
	}


	public void setMovie(MovieSheet movie) {
		this.movie = movie;
	}


	public List<MovieSheet> getSheet() {
		return sheet;
	}


	public void setSheet(List<MovieSheet> sheet) {
		this.sheet = sheet;
	}


	public boolean isForm() {
		return form;
	}


	public void setForm(boolean form) {
		this.form = form;
	}

	@PostConstruct
	public void init() 
	{
		sheet = local.listMovieSheets();
		Number i = ulocal.countPersonneAdmin();
		
//		System.out.println(selectedMovie.getId_Movie());
	}

//	public List<MovieCrew> listCrewBySheet(int idsheet)
//	{
//		idsheet=selectedMovie.getId_Movie();
//		crew = crewLocal.listCrewsBySheet(idsheet);
//		return crew;
//	}

	public MovieSheet getSelectedMovie() {
		return selectedMovie;
	}


	public void setSelectedMovie(MovieSheet selectedMovie) {
		this.selectedMovie = selectedMovie;
	}


public String CrewOfMovie(int idp){
		
		lsCrew = local.getCrewOfMovieSheet(idp);
		return null;
	}



	public List<MovieCrew> getLsCrew() {
		return lsCrew;
	}


	public void setLsCrew(List<MovieCrew> lsCrew) {
		this.lsCrew = lsCrew;
	}


	public MovieCrew getSelectedCrew() {
		return selectedCrew;
	}


	public void setSelectedCrew(MovieCrew selectedCrew) {
		this.selectedCrew = selectedCrew;
	}
	
public void doSaveOrUpdate(){
		
		
		local.update(movie);
		sheet = local.listMovieSheets();
		form =false;
		
	}
//	public String Validate()
//	{
//		local.update(movie);;
//		init();
//		movie = new MovieSheet();
//		form = false;
//		return null;
//	}
public void doNew(){
	
	movie = new MovieSheet();
		form = true;
		
	}


public void onRowSelect(SelectEvent event){
	form= true;
	
}

public void doDelete(){
	
	
	local.remove(movie);
	sheet = local.listMovieSheets();
	form = false;
	
	}



public void doCancel(){
	
	
	movie = new MovieSheet();
	sheet = local.listMovieSheets();
	form = false;
	
	
}
public void handleFileUpload(FileUploadEvent event) {
	movie.setImg(event.getFile().getContents());

}


public void doAddBox(){
	

	

	AuthenticationBean me = (AuthenticationBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
	
	boxOffice.setPersonne(me.getAdmin());
	boxOffice.setMovieSheet(selectedMovie);
	boxX.addBoxOffices(boxOffice);
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "boxOffice", isrendred3());
    RequestContext.getCurrentInstance().showMessageInDialog(message);
  

      
	}

public String isrendred3() {
	
	AuthenticationBean me = (AuthenticationBean) FacesContext
			.getCurrentInstance().getExternalContext().getSessionMap()
			.get("authBean");
	
	boxOffice.setPersonne(me.getAdmin());
	boxOffice.setMovieSheet(selectedMovie);

	if( boxX.listBoxByAdminMovie(boxOffice.getPersonne().getId(), boxOffice.getMovieSheet().getId_Movie()).size() == 0)
	{
		return "aa";
	}
	else {return "Movie added to Box with success";}		
}


public String delete() {
	if(	local.remove(movie)){
		init();
		FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Success","");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
		
		return null;
	}


public BoxOffice getBoxOffice() {
	return boxOffice;
}


public void setBoxOffice(BoxOffice boxOffice) {
	this.boxOffice = boxOffice;
}


public List<BoxOffice> getBoxOffices() {
	return boxOffices;
}


public void setBoxOffices(List<BoxOffice> boxOffices) {
	this.boxOffices = boxOffices;
}
	

public void changelanguagelistener(ActionEvent event ){

	 String message= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("message");
	 setLanguage(message);		 
	 
	 
}
public String getLanguage() {
	return language;
}


public void setLanguage(String language) {
	this.language = language;
}
public List<MovieSheet> getFiltredMovie() {
	return filtredMovie;
}


public void setFiltredMovie(List<MovieSheet> filtredMovie) {
	this.filtredMovie = filtredMovie;
}
private String language ="message_en";
}
