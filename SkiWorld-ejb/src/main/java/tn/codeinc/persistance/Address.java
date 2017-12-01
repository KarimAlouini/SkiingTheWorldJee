package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address implements Serializable{
	
	private static final long serialVersionUID = -629986286934434988L;

	private String street,city,country;
	
	
	private Integer zipCode;
	
	public Address(){
		
	}

	public Address(String street, String city, String country, Integer zipCode) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", country=" + country + ", zipCode=" + zipCode + "]";
	}
	
	
	
	

}
