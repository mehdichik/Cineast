package MovieVault.Services;


import java.util.List;

import javax.ejb.Local;

import MovieVault.Persistence.Admin;
import MovieVault.Persistence.Personne;

@Local
public interface AdminServicesLocal {

	
	void createAdmin(Admin admin);
	List<Admin> findAllAdmis();
	List<Personne> findAllUsers1();

	Admin authenticate(String login, String password);
	boolean loginExists(String login);
	public Admin findAdminByLogin(String login) ;
	public byte[] findPictureByPersonneId(int idPersonne);
	public Admin findAdminById(int id);
	public List<Personne> findAdminNotMe(int idadmin);
	void updateuser(Admin admin) ;
	
	int createUser1(Admin admin);
	void updateUser(Admin admin);

	Admin getAdminById(int id);
}
