
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
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=User.class)
	private User user;


	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Offer.class)
	private Offer offer;


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





	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}



	public Reservation() {
		super();

	}


	public Reservation(Integer id,User user ,Offer offer) {
		super();
		this.id = id;
        this.user = user;
		this.offer = offer;

	}

	public Reservation(Integer id) {
		super();
		this.id = id;

	}


}


