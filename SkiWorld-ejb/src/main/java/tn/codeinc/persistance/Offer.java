package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
public class Offer  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	protected String label;
	protected String description;
	protected Double price;
	protected Integer quantity;
	
	public Offer() {
		super();
	}
	public Offer(int id, String label, String description, Double price, Integer quantity) {
		super();
		this.id = id;
		this.label = label;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	public Offer(String label, String description, Double price, Integer quantity) {
		super();
		this.label = label;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Offer(int id) {
	super();
		this.id = id;	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
