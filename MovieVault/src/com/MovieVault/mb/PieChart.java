package com.MovieVault.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import MovieVault.Services.UserServiceLocal;

@ManagedBean
@ViewScoped
public class PieChart {

	private PieChartModel model;
	private Number i;
	private Number j;
	private Number k;
	@EJB
	UserServiceLocal local;
	@PostConstruct
	public void init() 
	{
		
		i = local.countPersonneAdmin();
		j=local.countPersonneCineast();
//		k=local.countPersonneUser();
		System.out.println(i);
		model = new PieChartModel();
        model.set("Administrators :"+i, i);
        model.set("Cineast :"+j,j);
        model.set("Users :"+k, k);
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
