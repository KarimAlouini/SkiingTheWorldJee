package tn.codeinc.persistance;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="recharging_coupon")
public class RechargingCoupon {
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer amount;
	
	private Date dateGenerated;
	
	@ManyToOne(targetEntity=Seller.class,fetch=FetchType.LAZY)
	private Seller seller;
	
	private Boolean isUsed ;
	@Column(name="code",unique=true)
	private String code;
	
	public RechargingCoupon() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDateGenerated() {
		return dateGenerated;
	}

	public void setDateGenerated(Date dateGenerated) {
		this.dateGenerated = dateGenerated;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	

}
