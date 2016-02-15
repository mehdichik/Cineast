package MovieVault.Persistence;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class MovieCrew implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String Actors;
	private String Directors;
	private String Stars;
	private String MovieMaker;
	private byte[] img;
	private MovieSheet movieSheet = new MovieSheet();
	private Personne personne = new Personne();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MovieCrew() {
		super();
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String actors) {
		Actors = actors;
	}

	public String getDirectors() {
		return Directors;
	}

	public void setDirectors(String directors) {
		Directors = directors;
	}

	public String getStars() {
		return Stars;
	}

	public void setStars(String stars) {
		Stars = stars;
	}

	public String getMovieMaker() {
		return MovieMaker;
	}

	public void setMovieMaker(String movieMaker) {
		MovieMaker = movieMaker;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="id_Movie")	
	public MovieSheet getMovieSheet() {
		return movieSheet;
	}

	public void setMovieSheet(MovieSheet movieSheet) {
		this.movieSheet = movieSheet;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

}