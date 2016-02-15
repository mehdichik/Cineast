package com.MovieVault.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import MovieVault.Persistence.Personne;
import MovieVault.Services.UserServiceLocal;
 
@ManagedBean
@ViewScoped
public class ChartView {
	
	private CartesianChartModel model;
//	private UserServiceLocal local;
//	private List<Personne> personnes;
//	private int u=0;
	private Number i;
	@EJB
	UserServiceLocal local;
	@PostConstruct
	public void init() 
	{
		
		i = local.countPersonneAdmin();
		System.out.println(i);
		model = new CartesianChartModel();
        ChartSeries boys = new ChartSeries();
//        String u = local.countPersonne();
//        personnes=local.findAllUsers1();
        boys.setLabel("Boys");
        boys.set("2004", i);
        System.out.println(i);
        boys.set("2005", 100);
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        model.addSeries(boys);
        model.addSeries(girls);
	}
//    public ChartView() {
//           model = new CartesianChartModel();
//           ChartSeries boys = new ChartSeries();
////           String u = local.countPersonne();
////           personnes=local.findAllUsers1();
//           boys.setLabel("Boys");
//           boys.set("2004", i);
//           boys.set("2005", 100);
//           ChartSeries girls = new ChartSeries();
//           girls.setLabel("Girls");
//           girls.set("2004", 52);
//           girls.set("2005", 60);
//           model.addSeries(boys);
//           model.addSeries(girls);
////           System.out.println(personnes);
//    }
	
    public CartesianChartModel getModel() { return model; }
} 