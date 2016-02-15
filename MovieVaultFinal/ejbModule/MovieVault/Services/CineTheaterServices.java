package MovieVault.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import MovieVault.Persistence.CinemaTheater;

/**
 * Session Bean implementation class CineTheaterServices
 */
@Stateless
public class CineTheaterServices implements CineTheaterServicesLocal {

	@PersistenceContext
    EntityManager en;
    public CineTheaterServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addTheater(CinemaTheater cinemaTheater) {
		en.persist(cinemaTheater);
		
	}

	@Override
	public void remove(CinemaTheater cinemaTheater) {
		en.remove(en.merge(cinemaTheater));
	}

	@Override
	public void update(CinemaTheater cinemaTheater) {
		en.merge(cinemaTheater);
		
	}

	@Override
	public void saveOrUpdatThearter(CinemaTheater cinemaTheater) {
		en.merge(cinemaTheater);
		
	}

	@Override
	public CinemaTheater getById(int i) {
		return en.find(CinemaTheater.class, i);	
	}

	@Override
	public List<CinemaTheater> finAllTheater() {
		Query q = en.createQuery("select e from CinemaTheater e ");		
		return q.getResultList();
	}

	@Override
	public List<CinemaTheater> listTheaterByAdmin(int id_admin) {
		Query q = en.createQuery("select a from CinemaTheater a");	
		List<CinemaTheater> mAll = new ArrayList<CinemaTheater>();
		List<CinemaTheater> mByid = new ArrayList<CinemaTheater>();
		mAll=q.getResultList();
		Iterator<CinemaTheater> it = mAll.iterator();

		while (it.hasNext()) {
			CinemaTheater m= new CinemaTheater();
			m=it.next();
			if(m.getPersonne().getId()==id_admin){
				mByid.add(m);
				
			}

		 
		}
		 return mByid;

		
			
		
		
	}




	@Override
	public List<CinemaTheater> listTheaterNotMe(int id_admin) {
		Query q = en.createQuery("select a from Event a");
		List<CinemaTheater> mAll = new ArrayList<CinemaTheater>();
		List<CinemaTheater> mByid = new ArrayList<CinemaTheater>();
		mAll = q.getResultList();
		Iterator<CinemaTheater> it = mAll.iterator();

		while (it.hasNext()) {
			CinemaTheater m = new CinemaTheater();
			m = it.next();
			if (m.getPersonne().getId() != id_admin) {
				mByid.add(m);

			}

		}
		return mByid;
	}

	@Override
	public byte[] findPictureByPlaceId(int idTheater) {
		byte[] picture = null;
		Query query = en.createQuery("select p.img from CinemaTheater p where p.id_Theater=:x");
		query.setParameter("x", idTheater);
		try {
			picture = (byte[]) query.getSingleResult();
		} catch (Exception ex) {
			
		}
		return picture;
	}

	@Override
	public CinemaTheater findTheterByName(String name) {
		return (CinemaTheater) en.createQuery("select l from CinemaTheate l where l.nom=:c").setParameter("c",name).getSingleResult();
	}

	@Override
	public void rateTheater(CinemaTheater t, int rate) {
		int a=t.getNb_rate()+1;
		int b =t.getRate()+rate;
		
        t.setNb_rate(a);
		t.setRate(b);
		t.setMoyRate(b/a);
		en.merge(t);

}


	
}