package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
public class OfferMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private java.util.Date date;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=User.class)
	private User user;


	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Offer.class)
	private Offer offer;
	private String mContent;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}



	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}






	public String getmContent() {
		return mContent;
	}



	public void setmContent(String mContent) {
		this.mContent = mContent;
	}


	public OfferMessage() {
		super();

	}


	public OfferMessage(Integer id, Date date,User user ,Offer offer, String mContent) {
		super();
		this.id = id;
		this.date = date;
        this.user = user;
		this.offer = offer;
		this.mContent = mContent;
	}

	public OfferMessage(Integer id, Date date, String mContent) {
		super();
		this.id = id;
		this.date = date;

		this.mContent = mContent;
	}


}
