package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: jobApply
 *
 */
@Entity
@Table(name="job_apply")


public class JobApply implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@ManyToOne(fetch=FetchType.EAGER)
	private JobOffer offer;
	private String message;
	private Date applicationDate;
	
	

	
	public JobApply(User user, JobOffer offer, String message, Date applicationDate) {
		super();
		this.user = user;
		this.offer = offer;
		this.message = message;
		this.applicationDate = applicationDate;
	}
	

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

	public JobOffer getOffer() {
		return offer;
	}

	public void setOffer(JobOffer offer) {
		this.offer = offer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	


	@Override
	public String toString() {
		return "JobApply [id=" + id + ", user=" + user + ", offer=" + offer + ", message=" + message
				+ ", applicationDate=" + applicationDate + "]";
	}



	private static final long serialVersionUID = 1L;

	public JobApply() {
		super();
	}
   
}
