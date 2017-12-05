package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;


@Embeddable
public class AdAreaPurchaseRequestId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2921839860373861895L;
	
	private Integer userId;
	
	private Integer adAreaId;

	public AdAreaPurchaseRequestId(Integer userId, Integer adAreaId) {
		super();
		this.userId = userId;
		this.adAreaId = adAreaId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAdAreaId() {
		return adAreaId;
	}

	public void setAdAreaId(Integer adAreaId) {
		this.adAreaId = adAreaId;
	}

	public AdAreaPurchaseRequestId() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adAreaId == null) ? 0 : adAreaId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		AdAreaPurchaseRequestId other = (AdAreaPurchaseRequestId) obj;
		if (adAreaId == null) {
			if (other.adAreaId != null)
				return false;
		} else if (!adAreaId.equals(other.adAreaId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	

	
	
	
	
	
	
	
}