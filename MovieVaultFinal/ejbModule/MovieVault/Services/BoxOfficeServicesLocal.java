package MovieVault.Services;


import java.util.List;

import javax.ejb.Local;

import MovieVault.Persistence.BoxOffice;

@Local
public interface BoxOfficeServicesLocal {
	
	public final String SERVICE_NAME="BoxOfficeServices";
	 public void  addBoxOffices(BoxOffice boxOffice);
	 public List<BoxOffice> findAllBox();
	
	 void removebox(BoxOffice box);
	 public List<BoxOffice> listBoxeByAdmin(int idadmin);
	 	  
	 public List<BoxOffice> listBoxByAdminMovie(int idadmin, int idmovies);

}
