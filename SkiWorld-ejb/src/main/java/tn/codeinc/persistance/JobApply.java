package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: jobApply
 *
 */
@Entity
@Table(name="job_apply")


public class JobApply implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private JobApplyId id;
	private String objet ; 
	private String message ;
	private Date ApplicationDate;
	private boolean isAccepted ;
	
	
	
	@ManyToOne
	@JoinColumn(name = "idClient", referencedColumnName = "id", updatable = false, insertable = false)
	private User client;
	
	@ManyToOne
	@JoinColumn(name = "idOffer", referencedColumnName = "id", updatable = false, insertable = false)
	private JobOffer offer;

	public JobApply(String objet, String message, User client, JobOffer offer) {
		super();
		this.objet = objet;
		this.message = message;
		this.client = client;
		this.offer = offer;
		this.ApplicationDate = new Date();
		this.id = new JobApplyId(client.getId(), offer.getId());
		this.isAccepted = false;
	}

	public JobApply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobApplyId getId() {
		return id;
	}

	public void setId(JobApplyId id) {
		this.id = id;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public JobOffer getOffer() {
		return offer;
	}

	public void setOffer(JobOffer offer) {
		this.offer = offer;
	}

	public Date getApplicationDate() {
		return ApplicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		ApplicationDate = applicationDate;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	
	
	
	
   
}
