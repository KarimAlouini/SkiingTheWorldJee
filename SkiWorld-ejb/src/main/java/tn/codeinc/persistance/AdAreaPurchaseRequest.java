package tn.codeinc.persistance;

import java.util.Date;
import java.util.Random;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ad_area_purchase_request")

public class AdAreaPurchaseRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@EmbeddedId
//	private AdAreaPurchaseRequestId id;
	//@Column(name = "start_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JoinColumn(name = "start_date", referencedColumnName = "id", updatable = false, insertable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date endDate;

	@JsonIgnore
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JoinColumn(name = "user", referencedColumnName = "id", updatable = false, insertable = false)
	private User user;
	@ManyToOne(targetEntity = AdArea.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinColumn(name = "adArea", referencedColumnName = "id", updatable = false, insertable = false)
	private AdArea adArea;
	@Column(columnDefinition = "int default 0")
	private AdAreaPurchaseRequestConfirmation confirmation;

	public enum AdAreaPurchaseRequestConfirmation {
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

	

	public AdAreaPurchaseRequest(Date startDate, Date endDate, AdArea adArea, User user) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.adArea = adArea;
		
	}

//	public void generateId() {
//		this.id = new AdAreaPurchaseRequestId(this.user.getId(), this.adArea.getId(), this.startDate);
//		if (this.generatedValue == null)
//			this.generatedValue = String.valueOf(new Random().nextInt());
//	}
//
//	public String getGeneratedValue() {
//		return generatedValue;
//	}
//
//	public void setGeneratedValue(String generatedValue) {
//		this.generatedValue = generatedValue;
//	}

	public AdAreaPurchaseRequestConfirmation getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(AdAreaPurchaseRequestConfirmation confirmation) {
		this.confirmation = confirmation;
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
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public AdAreaPurchaseRequest(Date startDate, Date endDate, User user, AdArea adArea,
			AdAreaPurchaseRequestConfirmation confirmation) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.adArea = adArea;
		this.confirmation = confirmation;
	}

	public AdAreaPurchaseRequest(Integer id, Date startDate, Date endDate, User user, AdArea adArea,
			AdAreaPurchaseRequestConfirmation confirmation) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.adArea = adArea;
		this.confirmation = confirmation;
	}
	
	

}
