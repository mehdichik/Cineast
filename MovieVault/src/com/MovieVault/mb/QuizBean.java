package com.MovieVault.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import MovieVault.Persistence.QuizV;
import MovieVault.Services.QuizVLocal;

@ManagedBean
@ViewScoped
public class QuizBean {

	@EJB
	private QuizVLocal local;
	
	private boolean formDisplayed = true;
	private QuizV quiz = new QuizV();
    private List<QuizV> quizs;
    
    public QuizBean() {

	}

	@PostConstruct
	public void init() {

		quizs = local.listQuizV1();

	}
	public void doNew() {

		quiz = new QuizV();
		formDisplayed = true;

	}
	
	

	public String  doDelete() {

		local.remove(quiz);;
		
		init();
		return null;

	}

	
	public String doSaveOrUpdate(){
		
		local.create(quiz);
		init();
		return null;
			
		}

	public List<QuizV> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<QuizV> quizs) {
		this.quizs = quizs;
	}
	public void onRowSelect(SelectEvent event) {

		formDisplayed = true;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public QuizV getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizV quiz) {
		this.quiz = quiz;
	}
	
}
