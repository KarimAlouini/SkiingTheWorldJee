package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: JobOffer
 *
 */
@Entity
@Table(name="job_offer")

public class JobOffer implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer numberOfPlaces;
	private Integer validity;
	private Date startDate;
	private Date endDate;
	private Double salary;
	private String description;
	@ManyToOne(fetch=FetchType.EAGER)
	private Agency agency;
	
	
	
	
	

	
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
			String description, Agency agency) {
		super();
		this.numberOfPlaces = numberOfPlaces;
		this.validity = validity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.salary = salary;
		this.description = description;
		this.agency = agency;
	}

	private static final long serialVersionUID = 1L;

	public JobOffer() {
		super();
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

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	@Override
	public String toString() {
		return "JobOffer [id=" + id + ", numberOfPlaces=" + numberOfPlaces + ", validity=" + validity + ", startDate="
				+ startDate + ", endDate=" + endDate + ", salary=" + salary + ", description=" + description
				+ ", agency=" + agency + "]";
	}
   
	
}
