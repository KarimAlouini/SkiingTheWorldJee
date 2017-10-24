package tn.codeinc.persistance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_area")
public class AdArea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer number;
	private Double price;

	@JsonIgnore
	@OneToMany(targetEntity = AdAreaPurchaseRequest.class, mappedBy = "adArea", fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
	private List<AdAreaPurchaseRequest> purchaseRequests = new ArrayList<>();

	public AdArea() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<AdAreaPurchaseRequest> getPurchaseRequests() {
		return purchaseRequests;
	}

	@Override
	public String toString() {
		return "AdArea [id=" + id + ", number=" + number + ", price=" + price + ", purchaseRequests=" + purchaseRequests
				+ "]";
	}

	
}
