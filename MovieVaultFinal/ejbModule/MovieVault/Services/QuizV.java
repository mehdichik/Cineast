package MovieVault.Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;




/**
 * Session Bean implementation class QuizV
 */
@Stateless
public class QuizV implements QuizVLocal {

	@PersistenceContext(unitName = "")
	private EntityManager em;

	public QuizV() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(QuizV quizV) {
		em.persist(quizV);

	}

	@Override
	public void update(QuizV quizV) {
		em.merge(quizV);

	}

	@Override
	public void del(int id) {
		em.remove(FindByiD(id));

	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<QuizV> listQuizV(Quiz quiz) {
//
////		return em.createQuery("select u from QuizV u").getResultList();
//		Query query=em.createQuery("select u from QuizV u where u.quiz_id=:q");
//		query.setParameter("q", quiz.getId());
//			return query.getResultList();
//	}

	@Override
	public QuizV FindByiD(int id) {

		return em.find(QuizV.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MovieVault.Persistence.QuizV> listQuizV1() {

		return em.createQuery("select u from QuizV u").getResultList();
//		Query query=em.createQuery("select u from QuizV u where u.quiz_id=:q");
//		query.setParameter("q", quiz);
//			return query.getResultList();
	}
	public void remove(QuizV quiz) {

		em.remove(em.merge(quiz));
		
	}

	public void create(MovieVault.Persistence.QuizV quiz) {
		em.persist(quiz);
	}

	public void remove(MovieVault.Persistence.QuizV quiz) {
		em.remove(em.merge(quiz));
	}

}
