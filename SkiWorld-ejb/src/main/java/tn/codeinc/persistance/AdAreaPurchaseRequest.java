package tn.codeinc.persistance;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_area_purchase_request")

public class AdAreaPurchaseRequest {
	
	@EmbeddedId
	private AdAreaPurchaseRequestId id;
	//@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	
	private Date endDate;

	@JsonIgnore
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id", updatable = false, insertable = false)
	private User user;
	@ManyToOne(targetEntity = AdArea.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "adAreaId", referencedColumnName = "id", updatable = false, insertable = false)
	private AdArea adArea;
	@Enumerated(EnumType.STRING)
	private AdAreaPurchaseRequestStatus confirmation;

	public enum AdAreaPurchaseRequestStatus {
		WAITING, ACCEPTED, REJECTED
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AdArea getAdArea() {
		return adArea;
	}

	public void setAdArea(AdArea adArea) {
		this.adArea = adArea;
	}

	public AdAreaPurchaseRequest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AdAreaPurchaseRequest other = (AdAreaPurchaseRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public AdAreaPurchaseRequest( Date startDate, Date endDate, User user, AdArea adArea) {
		super();
		this.id = new AdAreaPurchaseRequestId(user.getId(), adArea.getId());
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.adArea = adArea;
		this.confirmation = AdAreaPurchaseRequestStatus.WAITING;
	}

	public AdAreaPurchaseRequestId getId() {
		return id;
	}

	public void setId(AdAreaPurchaseRequestId id) {
		this.id = id;
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

	public AdAreaPurchaseRequestStatus getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(AdAreaPurchaseRequestStatus confirmation) {
		this.confirmation = confirmation;
	}


}
