package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Establishment implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	private String name;
	private String description;
	
	@Embedded
	private Coordinates type;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="agency",nullable=true)
	protected Agency agency;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Coordinates getType() {
		return type;
	}
	public void setType(Coordinates type) {
		this.type = type;
	}
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
	public Establishment() {
	
	}
	public Establishment(String name, String description, Coordinates type) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
	
	}
	
	public Establishment(int id, String name, String description, Coordinates type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
	
	}
	
}