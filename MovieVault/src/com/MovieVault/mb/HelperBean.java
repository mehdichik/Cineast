package com.MovieVault.mb;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

import MovieVault.Persistence.CinemaTheater;
import MovieVault.Services.CineTheaterServicesLocal;

@ManagedBean
@ApplicationScoped
public class HelperBean {

	@EJB
	private CineTheaterServicesLocal theater;

	private byte[] defaultPicture;

	public HelperBean() {
	}

	@PostConstruct
	public void init() {

		InputStream is = FacesContext.getCurrentInstance().getExternalContext()
				.getResourceAsStream("/resources/css/images/nopicture.jpg");

		try {
			defaultPicture = IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CinemaTheater findTheaterByName(String name) {
		return theater.findTheterByName(name);
	}

	public byte[] getDefaultPicture() {
		return defaultPicture;
	}

	public void setDefaultPicture(byte[] defaultPicture) {
		this.defaultPicture = defaultPicture;
	}

}
