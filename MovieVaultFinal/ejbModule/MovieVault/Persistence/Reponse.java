package MovieVault.Persistence;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reponse
 *
 */
@Entity

public class Reponse implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_rep;
	private String rep_1;
	private String rep_2;
	private String rep_3;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Question question;

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Reponse() {
		super();
	}   
	public int getId_rep() {
		return this.id_rep;
	}

	public void setId_rep(int id_rep) {
		this.id_rep = id_rep;
	}   
	public String getRep_1() {
		return this.rep_1;
	}

	public void setRep_1(String rep_1) {
		this.rep_1 = rep_1;
	}   
	public String getRep_2() {
		return this.rep_2;
	}

	public void setRep_2(String rep_2) {
		this.rep_2 = rep_2;
	}   
	public String getRep_3() {
		return this.rep_3;
	}

	public void setRep_3(String rep_3) {
		this.rep_3 = rep_3;
	}
   
}
