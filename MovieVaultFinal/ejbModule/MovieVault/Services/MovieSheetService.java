package MovieVault.Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import MovieVault.Persistence.BoxOffice;
import MovieVault.Persistence.CinemaTheater;
import MovieVault.Persistence.MovieCrew;
import MovieVault.Persistence.MovieSheet;



/**
 * Session Bean implementation class MovieSheet
 */
@Stateless
@LocalBean
public class MovieSheetService implements MovieSheetLocal {

	@PersistenceContext(unitName = "")
	private EntityManager em;

	public void create(MovieSheet movieSheet) {
		em.persist(movieSheet);
	}

	public void update(MovieSheet movieSheet) {
		em.merge(movieSheet);
	}

	public void del(int id) {
		em.remove(FindByiD(id));
	}
	@Override
	public boolean remove(MovieSheet movieSheet) {
		em.remove(em.merge(movieSheet));
		return true;
	}

	public List<MovieSheet> listMovieSheets() {
		return em.createQuery("Select u from MovieSheet u").getResultList();
	}

	public MovieSheet FindByiD(int id) {
		return em.find(MovieSheet.class, id);
	}

	public MovieSheet FindByTitle(String Title) {
//		Persistence.MovieSheet found = null;
		Query q = em.createQuery("select u from MovieSheet u where u.Title=:p1", MovieSheet.class);
//		String jpql = "select u from MovieSheet u where u.Title=:p1";
		q.setParameter("type", Title);
//		Query query = em.createQuery(jpql);
//		query.setParameter("p1", Title);
		List<MovieSheet> listmovies = q.getResultList();
		return (MovieSheet) listmovies;
	}

	public List<MovieCrew> findAllMovieCrewByMovieSheet(MovieSheet movieSheet) {
		Query query = em
				.createQuery("select distinct c from MovieCrew c where c.movisheet.id=:m");
		// select distinct d from Developpeur d join d.affectations a where
		// a.projet=:p
		query.setParameter("m", movieSheet.getId_Movie());
		// Query
		// query=entityManager.createQuery("select c from Employee e join e.competences c where e=:emp ");
		// query.setParameter("emp", employee);
		return query.getResultList();
	}

	public List<MovieSheet> listMovieSheetInBoxOffice() {
		BoxOffice b = new BoxOffice();
		b.setId(1);
		List<MovieSheet> MovieSheetl;
		String jpql = "select c from MovieSheet c where c.boxOffice=:x ";

		Query query = em.createQuery(jpql);
		query.setParameter("x", b);

		MovieSheetl = (List<MovieSheet>) query.getResultList();

		return MovieSheetl;
	}

	public List<MovieSheet> findbytype(String Type) {
		Query q = em.createQuery("select p from MovieSheet p where p.Type=:type", MovieSheet.class);		
		q.setParameter("type", Type);
		List<MovieSheet> listmovies = q.getResultList();
	    return listmovies;
	}

	public Number countMovieFunny(){
		TypedQuery<Long> query = em.createQuery(
			      "select count(u) from MovieSheet u where Type='Funny'", Long.class);
			  Number personCount = query.getSingleResult();
			  return personCount;
	}
	public Number countMovieScary(){
		TypedQuery<Long> query = em.createQuery(
			      "select count(u) from MovieSheet u where Type='Scary'", Long.class);
			  Number personCount = query.getSingleResult();
			  return personCount;
	}
	public Number countMovieAction(){
		TypedQuery<Long> query = em.createQuery(
			      "select count(u) from MovieSheet u where Type='Action'", Long.class);
			  Number personCount = query.getSingleResult();
			  return personCount;
	}
	
	@Override
	public byte[] findPictureByMovie(int idMovie) {
		byte[] picture = null;
		Query query = em.createQuery("select p.img from MovieSheet p where p.id_Movie=:x");
		query.setParameter("x", idMovie);
		try {
			picture = (byte[]) query.getSingleResult();
		} catch (Exception ex) {
			
		}
		return picture;
	}
	@Override
	public List<MovieCrew> getCrewOfMovieSheet(int idp) {

		Query q = em.createQuery("SELECT so FROM MovieCrew so WHERE so.movieSheet.id_Movie = :idp ");
		q.setParameter("idp",idp);
		
		List<MovieCrew> lp =(List<MovieCrew>) q.getResultList();
		
		return lp;
		
		
		

	}

}
