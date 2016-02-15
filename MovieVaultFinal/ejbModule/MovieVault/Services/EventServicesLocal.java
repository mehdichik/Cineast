package MovieVault.Services;


import java.util.List;

import javax.ejb.Local;

import MovieVault.Persistence.Event;

@Local
public interface EventServicesLocal {
	 public void addEvent(Event event);
		public  void remove(Event event);
		public void update(Event event) ;
		void saveOrUpdateEvent(Event event);
		public Event getById(int i);
		public List<Event> finAllEvent();
		public List<Event> listEventByAdmin(int idadmin);
		public List<Event> listEventNotMe(int idadmin);
		public byte[] findPictureByPlaceId(int idEvent) ;
	 
}
