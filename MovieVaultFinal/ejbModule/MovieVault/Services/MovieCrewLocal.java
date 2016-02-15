package MovieVault.Services;

import java.util.List;

import javax.ejb.Local;

import MovieVault.Persistence.MovieCrew;

@Local
public interface MovieCrewLocal {
	
	public void create(MovieCrew movieCrew);

	public void update(MovieCrew movieCrew);

	public void del(int id);

	public List<MovieCrew> listMovieCrews();

	public MovieCrew FindByiD(int id);

	List<MovieCrew> listCrewsBySheet(int idsheet);

}
