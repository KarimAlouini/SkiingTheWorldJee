package tn.codeinc.persistance;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ad_area_purchase_request")

public class AdAreaPurchaseRequest {

	@EmbeddedId
	private AdAreaPurchaseRequestId id;
	@Column(name = "start_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date endDate;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.DETACH)
	@JoinColumn(name = "user", referencedColumnName = "id", updatable = false, insertable = false)
	private User user;
	@ManyToOne(targetEntity = AdArea.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name = "adArea", referencedColumnName = "id", updatable = false, insertable = false)
	private AdArea adArea;

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

	@Override
	public String toString() {
		return "AdAreaPurchaseRequest [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", user="
				+ user + ", adArea=" + adArea + "]";
	}

}
