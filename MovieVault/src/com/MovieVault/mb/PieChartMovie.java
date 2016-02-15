package com.MovieVault.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import MovieVault.Services.MovieSheetLocal;

@ManagedBean
@ViewScoped
public class PieChartMovie {
	private PieChartModel model;
	private Number a,b,c;
	@EJB
	MovieSheetLocal mlocal;
	@PostConstruct
	public void init() 
	{
		
		a = mlocal.countMovieFunny();
		b = mlocal.countMovieAction();
		c= mlocal.countMovieScary();
		model = new PieChartModel();
        
        model.set("Funny :"+a, a);
        model.set("Action :"+b, b);
        model.set("Scary :"+c, c);
	}
//    public PieChart() {
//           model = new PieChartModel();
//           model.set("Brand 1", 540);
//           model.set("Brand 2", 325);
//           model.set("Brand 3", 702);
//           model.set("Brand 4", 421);
//}
    public PieChartModel getModel() {
           return model;
}

}
