package tn.codeinc.persistance;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer reference;
	private String description;
	private String marque;
	private String modele;
	private String name;
	private String photo;
	private Integer quantity;
	private String color;
	private String size;
	private Double price;
	@ManyToOne
	private SousCategorie sousCategorie;
	private static final long serialVersionUID = 1L;
	
	private int userId;

	public Product() {
		super();
	}   
	public Integer getReference() {
		return this.reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getMarque() {
		return this.marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}   
	public String getModele() {
		return this.modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}   
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}   
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}   
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}   
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
   
}
