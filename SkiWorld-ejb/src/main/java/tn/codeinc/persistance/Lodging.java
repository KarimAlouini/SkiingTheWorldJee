package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Lodging extends Offer  implements Serializable{
	
	private String typ;
	@Temporal(TemporalType.DATE)
	private java.util.Date startDate;
	@Temporal(TemporalType.DATE)
	private java.util.Date endDate;
	private String etat;
	
	public Lodging() {
		super();
	}

	public Lodging( String label, String description, Double price, Integer quantity, String typ
		,java.util.Date startDate, java.util.Date endDate, String etat) {
		super( label, description, price, quantity);
		
        this.typ = typ;
		this.startDate = startDate;
		this.endDate = endDate;
		this.etat =etat;
	}
	

	public Lodging(int id, String label, String description, Double price, Integer quantity, String typ
			,java.util.Date startDate, java.util.Date endDate, String etat) {
			super( id, label, description, price, quantity);
			
			this.typ = typ;
			this.startDate = startDate;
			this.endDate = endDate;
			this.etat =etat;
		}
	public Lodging(int id,String typ, java.util.Date startDate, java.util.Date endDate, String etat) {
			super( id);
			
			this.typ = typ;
			this.startDate = startDate;
			this.endDate = endDate;
			this.etat =etat;
		}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	public java.util.Date getStartDate() {
		return startDate;
	}


	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}


	public java.util.Date getEndDate() {
		return endDate;
	}


	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	

	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}
	

	

	
}
