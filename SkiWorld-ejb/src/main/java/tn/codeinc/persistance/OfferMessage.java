package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "offer_message",
uniqueConstraints=@UniqueConstraint(columnNames={"user","date","offer"}))


//new
public class OfferMessage {
	@Id
	private Integer id;
	@ManyToOne
	@JoinColumn(name="user")
	private User user;

	private Date date;
	@ManyToOne
	@JoinColumn(name="offer")
	private JobOffer offer;
	private String mContent;
	
	public OfferMessage() {
		this.date = new Date();
	}

}
