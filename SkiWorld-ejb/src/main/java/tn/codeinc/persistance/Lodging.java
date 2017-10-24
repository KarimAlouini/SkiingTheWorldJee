package tn.codeinc.persistance;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Lodging extends Offer{
	
	private Date startDate,endDate;
	public Lodging(Integer id, String label, String description, Double price, Integer quantity, 
			Date startDate, Date endDate) {
		super(id, label, description, price, quantity);
		;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	public Lodging() {
		// TODO Auto-generated constructor stub
	}

	public enum LogdingType {
		SUITE,ROOM
	}


	


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	

}
