package tn.codeinc.persistance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	private Offer offer;
	private String image;

	@JsonIgnore
	@OneToMany(targetEntity = AdAreaPurchaseRequest.class, mappedBy = "adArea", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<AdAreaPurchaseRequest> purchaseRequests = new ArrayList<>();

	public AdArea() {
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

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public AdArea(Integer number, Double price, Offer offer, String image) {
		super();
		this.number = number;
		this.price = price;
		this.offer = offer;
		this.image = image;
		
	}

	@Override
	public String toString() {
		return "AdArea [id=" + id + ", number=" + number + ", price=" + price + ", offer=" + offer + ", image=" + image
				+ ", purchaseRequests=" + purchaseRequests + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
