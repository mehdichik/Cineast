package MovieVault.Persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BoxOffice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private Personne personne = new Personne();
	private MovieSheet movieSheet = new MovieSheet();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BoxOffice() {
		super();
	}

	@ManyToOne
	@JoinColumn(name = "id_user")
	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@ManyToOne
	@JoinColumn(name = "id_Movie")
	public MovieSheet getMovieSheet() {
		return movieSheet;
	}

	public void setMovieSheet(MovieSheet movieSheet) {
		this.movieSheet = movieSheet;
	}

}
