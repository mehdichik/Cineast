package MovieVault.Persistence;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class CinemaTheater implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id_Theater;
	private String name;
	private String address;
	private String Description;
	private Personne personne = new Personne();
	private int nb_rate;
	private int rate;
	private int moyRate;

	private float longitude;
	private float latitude;

	private byte[] img;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId_Theater() {
		return id_Theater;
	}

	public void setId_Theater(int id_Theater) {
		this.id_Theater = id_Theater;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public int getNb_rate() {
		return nb_rate;
	}

	public void setNb_rate(int nb_rate) {
		this.nb_rate = nb_rate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getMoyRate() {
		return moyRate;
	}

	public void setMoyRate(int moyRate) {
		this.moyRate = moyRate;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	@ManyToOne
	@JoinColumn(name="id_admin")
	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}



}
