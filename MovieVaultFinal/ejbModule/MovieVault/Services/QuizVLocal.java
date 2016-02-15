package MovieVault.Services;

import java.util.List;

import javax.ejb.Local;

@Local
public interface QuizVLocal {
	public void create(MovieVault.Persistence.QuizV quiz);

	public void update(QuizV quizV);

	public void del(int id);


	public QuizV FindByiD(int id);

	public List<MovieVault.Persistence.QuizV> listQuizV1();

	void remove(MovieVault.Persistence.QuizV quiz);

	void create(QuizV quizV);

}
