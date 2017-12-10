package tn.codeinc.services;
import java.util.List;

import javax.ejb.Local;

import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.JobOffer;
import tn.codeinc.persistance.User;


@Local	
public interface JobOfferManagementLocal {
	public void create(JobOffer jobOffer);
	public List<JobOffer> getAll();
	public JobOffer get(int id)throws ElementNotFoundException;
	public void remove(JobOffer jobOffer)throws ElementNotFoundException;
	public void update(JobOffer jobOffer);
	public List<JobOffer> getByAgent(User agent);
	public List<User> getAppliedUsers(JobOffer jobOffer);
	public List<User> getAcceptedUsers(JobOffer jobOffer);
	
	public List<JobOffer> getNewestJobOffers();
	public List<JobOffer> getJobOffersBycategory(String Category);

	

}
