package MovieVault.Persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: MovieSheet
 * 
 */
@Entity
public class MovieSheet implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id_Movie;
	private String Title;
	private String Type;
	private String Description;
	private Date DateOfRelasing;
	private int nb_rate;
	private int rate;
	private String Trailer;
	private int moyRate;
	private byte[] img;
	private List<MovieCrew> crew = new ArrayList<MovieCrew>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId_Movie() {
		return id_Movie;
	}

	public void setId_Movie(int id_Movie) {
		this.id_Movie = id_Movie;
	}

	public MovieSheet() {
		super();
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getType() {
		return this.Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public Date getDateOfRelasing() {
		return this.DateOfRelasing;
	}

	public void setDateOfRelasing(Date DateOfRelasing) {
		this.DateOfRelasing = DateOfRelasing;
	}

	public int getNb_rate() {
		return nb_rate;
	}

	public void setNb_rate(int nb_rate) {
		this.nb_rate = nb_rate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getMoyRate() {
		return moyRate;
	}

	public void setMoyRate(int moyRate) {
		this.moyRate = moyRate;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getTrailer() {
		return Trailer;
	}

	public void setTrailer(String trailer) {
		Trailer = trailer;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private MovieQuiz moviequiz;
	
    public MovieQuiz getMoviequiz() {
		return moviequiz;
	}

	public void setMoviequiz(MovieQuiz moviequiz) {
		this.moviequiz = moviequiz;
	}

	@OneToMany
	public List<MovieCrew> getCrew() {
		return crew;
	}

	public void setCrew(List<MovieCrew> crew) {
		this.crew = crew;
	}


}
