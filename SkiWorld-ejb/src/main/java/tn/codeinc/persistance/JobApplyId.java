package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: JobApplyId
 *
 */
@Embeddable

public class JobApplyId implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Integer idClient;
	private Integer idOffer;
	
	public JobApplyId() {
		super();
	}
	
	

	public JobApplyId(Integer idClient, Integer idOffer) {
		super();
		this.idClient = idClient;
		this.idOffer = idOffer;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		JobApplyId other = (JobApplyId) obj;
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

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Integer getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(Integer idOffer) {
		this.idOffer = idOffer;
	}
	
	
   
}
