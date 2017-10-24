package tn.codeinc.persistance;

import java.util.Date;
import javax.persistence.Entity;

@Entity

public class ResortToken extends Offer{
	
	private ResortDifficulty difficulty;
	private Date startDate,endDate;
	public ResortToken(ResortDifficulty difficulty, Date startDate, Date endDate) {
		super();
		this.difficulty = difficulty;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public ResortToken() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ResortDifficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(ResortDifficulty difficulty) {
		this.difficulty = difficulty;
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
