package MovieVault.Services;

import java.util.List;

import javax.ejb.Local;

import MovieVault.Persistence.Admin;
import MovieVault.Persistence.Personne;
import MovieVault.Persistence.User;
@Local
public interface UserServiceLocal {
	void createUser(User user);
	List<User> findAllUsers();
	List<Personne> findAllUsers1();

	Number  countPersonneAdmin();
	Number  countPersonneCineast();
	
	User authenticate(String login, String password);
	boolean loginExists(String login);
	public User findUserByLogin(String login) ;
	public byte[] findPictureByPersonneId(int idPersonne);
	public User findUserById(int id);
	public List<Personne> findUsersNotMe(int idadmin);
	void updateuser(Admin admin) ;
	
	int createUser1(User user);
	void updateUser(User user);

	User getUserById(int id);
	List<User> listeUsers();
	void remove(User user);



}
