package com.MovieVault.mb;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import MovieVault.Persistence.BoxOffice;
import MovieVault.Persistence.MovieSheet;
import MovieVault.Services.BoxOfficeServicesLocal;



@ManagedBean
@ViewScoped
public class wishBean {

	
	@EJB
	private BoxOfficeServicesLocal boxX;
	
	
	private BoxOffice boxOffice = new BoxOffice() ;
	private List<BoxOffice> boxOffices;
	
	
	private List<BoxOffice> fileteredBoxs;
	private boolean formDisplayed = false;
	private boolean Displayed = false;
	private  MovieSheet selectedMovie;
	public wishBean() {
		// TODO Auto-generated constructor stub
	}
	public void doDeleteBoxOffice(BoxOffice boxOffice) {

		boxX.removebox(boxOffice);
	
		
	}
	


	public BoxOfficeServicesLocal getBoxX() {
		return boxX;
	}
	public void setBoxX(BoxOfficeServicesLocal boxX) {
		this.boxX = boxX;
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
	public List<BoxOffice> getFileteredBoxs() {
		return fileteredBoxs;
	}
	public void setFileteredBoxs(List<BoxOffice> fileteredBoxs) {
		this.fileteredBoxs = fileteredBoxs;
	}
	public MovieSheet getSelectedMovie() {
		return selectedMovie;
	}
	public void setSelectedMovie(MovieSheet selectedMovie) {
		this.selectedMovie = selectedMovie;
	}
	@PostConstruct
public void init(){
		
		boxOffices = boxX.findAllBox();
		System.out.println(boxOffices.size()+"azeaz");
		
	}
	
	
	
	public List<BoxOffice> getBoxbyId(int idadmin) {

		boxOffices = boxX.listBoxeByAdmin(idadmin);
		return boxOffices;
	}

	
	
	
	
	
	public void onRowSelect(SelectEvent event){
		setDisplayed(true);
		
	}
	
	
	
	
	
	
	public boolean isDisplayed() {
		return Displayed;
	}
	public void setDisplayed(boolean displayed) {
		Displayed = displayed;
	}

}
