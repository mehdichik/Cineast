package MovieVault.Services;

import java.util.List;

import javax.ejb.Local;

import MovieVault.Persistence.CinemaTheater;

@Local
public interface CineTheaterServicesLocal {

	public void addTheater(CinemaTheater cinemaTheater);

	public void remove(CinemaTheater cinemaTheater);

	public void update(CinemaTheater cinemaTheater);

	void saveOrUpdatThearter(CinemaTheater cinemaTheater);

	public CinemaTheater getById(int i);

	public List<CinemaTheater> finAllTheater();

	public List<CinemaTheater> listTheaterByAdmin(int id_admin);

	public List<CinemaTheater> listTheaterNotMe(int id_admin);

	public byte[] findPictureByPlaceId(int idTheater);
	
	void rateTheater(CinemaTheater t,int nbRate);
	
	
	public CinemaTheater findTheterByName(String name);
	
	
}
