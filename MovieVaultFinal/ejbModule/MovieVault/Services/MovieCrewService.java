package MovieVault.Services;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import MovieVault.Persistence.MovieCrew;


/**
 * Session Bean implementation class MovieCrew
 */
@Stateless
@LocalBean
public class MovieCrewService implements MovieCrewLocal {

	@PersistenceContext(unitName = "")
	private EntityManager em;

	public MovieCrewService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(MovieCrew movieCrew) {
		em.persist(movieCrew);
	}

	@Override
	public void update(MovieCrew movieCrew) {
		em.merge(movieCrew);
	}

	@Override
	public void del(int moviecrewPk) {
		em.remove(FindByiD(moviecrewPk));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieCrew> listMovieCrews() {
	
		 return em.createQuery("Select u from MovieCrew u").getResultList();
	}

	@Override
	public MovieCrew FindByiD(int id) {
		
		return em.find(MovieCrew.class, id);
	}

	
	@Override
	public List<MovieCrew> listCrewsBySheet(int idsheet) {
	
		 return em.createQuery("Select u from MovieCrew u where u.id_Movie=:x").setParameter("x", idsheet).getResultList();
	}

	

}

