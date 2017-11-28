package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Seller implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name,email,phoneNumber;
	private Coordinates coordinates;
	
	
	public Seller(String name, String email, String phoneNumber, Coordinates coordinates) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.coordinates = coordinates;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public Seller() {
		// TODO Auto-generated constructor stub
	}
	

}
