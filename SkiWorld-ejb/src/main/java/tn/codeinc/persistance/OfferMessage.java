package tn.codeinc.persistance;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

//new
public class OfferMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OfferMessageId id;
	private String objet ; 
	private String message ;
	
	
	@ManyToOne
	@JoinColumn(name = "idClient", referencedColumnName = "id", updatable = false, insertable = false)
	private User client;
	
	@ManyToOne
	@JoinColumn(name = "idAgent", referencedColumnName = "id", updatable = false, insertable = false)
	private User agent;
	
	@ManyToOne
	@JoinColumn(name = "idOffer", referencedColumnName = "id", updatable = false, insertable = false)
	private JobOffer jobOffer;
	
	

	public OfferMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OfferMessage(String objet, String message, User client, User agent, JobOffer jobOffer) {
		super();
		this.objet = objet;
		this.message = message;
		this.client = client;
		this.agent = agent;
		this.id = new OfferMessageId(client.getId(),agent.getId(), jobOffer.getId());
	}

	public OfferMessageId getId() {
		return id;
	}

	public void setId(OfferMessageId id) {
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

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	
	
	

	
}
