package MovieVault.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import MovieVault.Persistence.Admin;
import MovieVault.Persistence.Personne;

/**
 * Session Bean implementation class AdminServices
 */
@Stateless
public class AdminServices implements AdminServicesLocal {
 
	@PersistenceContext
	private EntityManager em;
	
    public AdminServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createAdmin(Admin admin) {
		em.persist(admin);
		
	}

	@Override
	public List<Admin> findAllAdmis() {
		return em.createQuery("select u from Admin u").getResultList();
	}

	@Override
	public List<Personne> findAllUsers1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin authenticate(String login, String password) {
		Admin found = null;
		String jpql = "select u from Admin u where u.login=:login and u.password=:password";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login );
		query.setParameter("password",password);
		try{
			found = (Admin) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
	}

	@Override
	public boolean loginExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from Admin u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		exists = (Boolean)query.getSingleResult();
		return exists;
	}

	@Override
	public Admin findAdminByLogin(String login) {
		Admin found = null;
		String jpql = "select u from Admin u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		try{
			found = (Admin) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
	}

	@Override
	public byte[] findPictureByPersonneId(int idPersonne) {
		byte[] picture = null;
		Query query = em.createQuery("select p.img from Personne p where p.id=:x");
		query.setParameter("x", idPersonne);
		try{
			picture =  (byte[]) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no picture");
		}
		return picture;
	}

	@Override
	public Admin findAdminById(int id) {
		Admin found = null;
		String jpql = "select u from Admin u where u.id=:x";
		Query query = em.createQuery(jpql);
		query.setParameter("x",id);
		try{
			found = (Admin) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
	}

	@Override
	public List<Personne> findAdminNotMe(int idadmin) {
		Query q = em.createQuery("select a from Personne a");	
		List<Personne> mAll = new ArrayList<Personne>();
		List<Personne> mByid = new ArrayList<Personne>();
		mAll=q.getResultList();
		Iterator<Personne> it = mAll.iterator();

		while (it.hasNext()) {
			Personne m= new Personne();
			m=it.next();
			if(m.getId()!=idadmin){
				mByid.add(m);
						
			}
	 
		}
		 return mByid;
	}

	@Override
	public void updateuser(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int createUser1(Admin admin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateUser(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin getAdminById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
