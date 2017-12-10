package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.JobOffer;
import tn.codeinc.persistance.User;


@Remote
public interface JobOfferManagementRemote {
	
	public void create(JobOffer jobOffer);
	public List<JobOffer> getAll();
	public JobOffer get(int id)throws ElementNotFoundException;
	public void remove(JobOffer jobOffer)throws ElementNotFoundException;
	public void update(JobOffer jobOffer);
	public List<JobOffer> getByAgent(User agent);

}
