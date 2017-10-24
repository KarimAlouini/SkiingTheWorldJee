package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.Embeddable;


@Embeddable
public class AdAreaPurchaseRequestId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2921839860373861895L;
	
	private Integer user;
	
	private Integer adArea;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adArea == null) ? 0 : adArea.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (adArea == null) {
			if (other.adArea != null)
				return false;
		} else if (!adArea.equals(other.adArea))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	public Integer getAdArea() {
		return adArea;
	}
	
	public Integer getUser() {
		return user;
	}
	
	public void setAdArea(Integer adArea) {
		this.adArea = adArea;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "AdAreaPurchaseRequestId [user=" + user + ", adArea=" + adArea + "]";
	}
	public AdAreaPurchaseRequestId(Integer user, Integer adArea) {
		super();
		this.user = user;
		this.adArea = adArea;
	}
	
	
	public AdAreaPurchaseRequestId() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}