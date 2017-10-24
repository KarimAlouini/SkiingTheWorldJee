package tn.codeinc.persistance;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {
	private Float longitude,latitude;

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Coordinates(Float longitude, Float latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Coordinates() {
		// TODO Auto-generated constructor stub
	}
	
	

}
