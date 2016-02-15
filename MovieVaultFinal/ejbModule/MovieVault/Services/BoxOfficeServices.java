package MovieVault.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import javax.persistence.Query;
import javax.swing.Box;



import MovieVault.Persistence.BoxOffice;

/**
 * Session Bean implementation class BoxOfficeServices
 */
@Stateless(name=BoxOfficeServices.SERVICE_NAME)
public class BoxOfficeServices implements BoxOfficeServicesLocal {


	@PersistenceContext
	EntityManager en;
    
	
    public BoxOfficeServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addBoxOffices(BoxOffice boxOffice) {
		en.persist(boxOffice);
		
	}

	@Override
	public List<BoxOffice> findAllBox() {
		return en.createQuery("select p from BoxOffice p").getResultList();
	}

	@Override
	public void removebox(BoxOffice box) {
		en.remove(en.merge(box));
		
	}

	@Override
	public List<BoxOffice> listBoxeByAdmin(int idadmin) {
		Query q = en.createQuery("select a from BoxOffice a");	
		List<BoxOffice> mAll = new ArrayList<BoxOffice>();
		List<BoxOffice> mByid = new ArrayList<BoxOffice>();
		mAll=q.getResultList();
		Iterator<BoxOffice> it = mAll.iterator();

		while (it.hasNext()) {
			BoxOffice m= new BoxOffice();
			m=it.next();
			if(m.getPersonne().getId()==idadmin){
				mByid.add(m);
				
			}

		 
		}
		 return mByid;
	}

	@Override
	public List<BoxOffice> listBoxByAdminMovie(int idadmin, int idmovies) {
		Query q = en.createQuery("select a from Wishlist a");	
		List<BoxOffice> mAll = new ArrayList<BoxOffice>();
		List<BoxOffice> mByid = new ArrayList<BoxOffice>();
		mAll=q.getResultList();
		Iterator<BoxOffice> it = mAll.iterator();

		while (it.hasNext()) {
			BoxOffice m= new BoxOffice();
			m=it.next();
			if((m.getPersonne().getId()==idadmin) && (m.getMovieSheet().getId_Movie()==idmovies)){
				mByid.add(m);
				
			}

		 
		}
		 return mByid;
		

	}
		

}
