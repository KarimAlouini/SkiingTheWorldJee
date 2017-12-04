package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MessageOfferId
 *
 */

@Embeddable
public class OfferMessageId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer idClient;
	private Integer idAgent;
	private Integer idOffer;
	private Date dateOfSendingMessage;

	public OfferMessageId( ) {
		super();
		
	}

	public OfferMessageId(Integer idClient, Integer idAgent,Integer idOffer) {
		super();
		this.idClient = idClient;
		this.idAgent = idAgent;
		this.dateOfSendingMessage = new Date();
		this.idOffer = idOffer;
		}
	

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Integer getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(Integer idAgent) {
		this.idAgent = idAgent;
	}

	public Date getDateOfSendingMessage() {
		return dateOfSendingMessage;
	}

	public void setDateOfSendingMessage(Date dateOfSendingMessage) {
		this.dateOfSendingMessage = dateOfSendingMessage;
	}


	public Integer getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(Integer idOffer) {
		this.idOffer = idOffer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfSendingMessage == null) ? 0 : dateOfSendingMessage.hashCode());
		result = prime * result + ((idAgent == null) ? 0 : idAgent.hashCode());
		result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
		result = prime * result + ((idOffer == null) ? 0 : idOffer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferMessageId other = (OfferMessageId) obj;
		if (dateOfSendingMessage == null) {
			if (other.dateOfSendingMessage != null)
				return false;
		} else if (!dateOfSendingMessage.equals(other.dateOfSendingMessage))
			return false;
		if (idAgent == null) {
			if (other.idAgent != null)
				return false;
		} else if (!idAgent.equals(other.idAgent))
			return false;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		if (idOffer == null) {
			if (other.idOffer != null)
				return false;
		} else if (!idOffer.equals(other.idOffer))
			return false;
		return true;
	}
	
	
   
}
