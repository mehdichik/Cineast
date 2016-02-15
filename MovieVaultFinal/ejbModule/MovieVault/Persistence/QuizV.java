package MovieVault.Persistence;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: QuizV
 * 
 */
@Entity
public class QuizV implements Serializable {

	private int id;
	private String Question;
	private String Answer1;
	private String Answer2;
	private String AnswerValidation;
	private static final long serialVersionUID = 1L;

	public QuizV() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return this.Question;
	}

	public void setQuestion(String Question) {
		this.Question = Question;
	}

	public String getAnswer1() {
		return this.Answer1;
	}

	public void setAnswer1(String Answer1) {
		this.Answer1 = Answer1;
	}

	public String getAnswer2() {
		return this.Answer2;
	}

	public void setAnswer2(String Answer2) {
		this.Answer2 = Answer2;
	}

	public String getAnswerValidation() {
		return this.AnswerValidation;
	}

	public void setAnswerValidation(String AnswerValidation) {
		this.AnswerValidation = AnswerValidation;
	}

	

}
