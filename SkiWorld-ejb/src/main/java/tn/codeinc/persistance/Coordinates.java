package tn.codeinc.persistance;
import java.io.Serializable;
import javax.persistence.Embeddable;




@Embeddable
public class Coordinates implements Serializable{

	private Double longitude;
	private Double latidue;

	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatidue() {
		return latidue;
	}
	public void setLatidue(Double latidue) {
		this.latidue = latidue;
	}
	
	
	public Coordinates() {
	
	}
	public Coordinates(Double longitude, Double latidue) {
	
		this.longitude = longitude;
		this.latidue = latidue;


	}
	
	
	
}