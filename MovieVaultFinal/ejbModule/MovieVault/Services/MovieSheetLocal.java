package MovieVault.Services;


import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import MovieVault.Persistence.MovieCrew;
import MovieVault.Persistence.MovieSheet;


@Local
public interface MovieSheetLocal {

	public void create(MovieSheet movieSheet);

	public void update(MovieSheet movieSheet);

	public void del(int id);

	public List<MovieSheet> listMovieSheets();

	public MovieSheet FindByiD(int id);
	
	public MovieSheet FindByTitle(String Title);

//	List<MovieCrew> findAllMovieCrewByMovieSheet(
//			tn.edu.esprit.MovieVaultServices.MovieSheet movieSheet);

	List<MovieCrew> findAllMovieCrewByMovieSheet(MovieSheet movieSheet);
	public List<MovieSheet> listMovieSheetInBoxOffice();

	List<MovieSheet> findbytype(String Type);

	boolean remove(MovieSheet movieSheet);

	byte[] findPictureByMovie(int idMovie);
	public Number countMovieFunny();
	public Number countMovieScary();
	public Number countMovieAction();

	List<MovieCrew> getCrewOfMovieSheet(int idp);


	
}
