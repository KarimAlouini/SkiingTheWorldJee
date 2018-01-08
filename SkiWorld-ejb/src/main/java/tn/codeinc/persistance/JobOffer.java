package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




/**
 * Entity implementation class for Entity: JobOffer
 *
 */
@Entity
@Table(name="job_offer")

public class JobOffer implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer numberOfPlaces;
	private Integer validity;
	
	private Date startDate;
	
	private Date endDate;
	private Double salary;
	private String description;
	private String name;
	private boolean isArchived ;
	
	private Date creationDate;

	@Enumerated(EnumType.STRING)
	private JobOfferCategory jobOfferCategory;
	
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.DETACH)
	private User agent;
	
	@OneToMany(mappedBy = "offer",cascade=CascadeType.REMOVE)
	private List<JobApply> applicationsByClients;
	
	@OneToMany(mappedBy = "jobOffer")
	private List<OfferMessage> offerMessagesOnThisOffer;
	
	
	
	
	

	
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
		JobOffer other = (JobOffer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	public JobOffer(Integer numberOfPlaces, Integer validity, Date startDate, Date endDate, Double salary,
			String description, String name, User agent,boolean isArchived) {
		super();
		this.numberOfPlaces = numberOfPlaces;
		this.validity = validity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.salary = salary;
		this.description = description;
		this.name = name;
		this.agent = agent;
		this.applicationsByClients = new ArrayList<JobApply>();
		this.offerMessagesOnThisOffer = new ArrayList<OfferMessage>();
		this.isArchived= false;
		this.creationDate = new Date();
	}

	


	private static final long serialVersionUID = 1L;

	public JobOffer() {
		this.creationDate = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(Integer numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "JobOffer [id=" + id + ", numberOfPlaces=" + numberOfPlaces + ", validity=" + validity + ", startDate="
				+ startDate + ", endDate=" + endDate + ", salary=" + salary + ", description=" + description
				+ ", agent=" + agent + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public JobOfferCategory getJobOfferCategory() {
		return jobOfferCategory;
	}

	public void setJobOfferCategory(JobOfferCategory jobOfferCategory) {
		this.jobOfferCategory = jobOfferCategory;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	
   
	
}


