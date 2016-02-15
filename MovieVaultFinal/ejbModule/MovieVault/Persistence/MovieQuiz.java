package MovieVault.Persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: MovieQuiz
 * 
 */

@Entity
public class MovieQuiz implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idmovQuiz;
	private String descripion;

	@OneToMany(fetch = FetchType.EAGER)
	private List<MovieSheet> movieSheet;

	@OneToMany
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getDescripion() {
		return descripion;
	}

	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}

	private static final long serialVersionUID = 1L;

	public MovieQuiz() {

	}

	public int getIdmovQuiz() {
		return this.idmovQuiz;
	}

	public void setIdmovQuiz(int idmovQuiz) {
		this.idmovQuiz = idmovQuiz;
	}

	public List<MovieSheet> getMovieSheet() {
		return movieSheet;
	}

	public void setMovieSheet(List<MovieSheet> movieSheet) {
		this.movieSheet = movieSheet;
	}

}
