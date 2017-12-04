package tn.codeinc.services;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.JobOffer;
import tn.codeinc.persistance.User;

@Stateless
public class JobOfferManagement implements JobOfferManagementLocal,JobOfferManagementRemote{

		
	@PersistenceContext
	EntityManager em;
	
	public JobOfferManagement() {
		super();
	}

	@Override
	public void create(JobOffer jobOffer) {
		em.merge(jobOffer);
		
	}

	@Override
	public List<JobOffer> getAll() {
	
	     return em.createQuery("SELECT jo FROM JobOffer jo",JobOffer.class).getResultList();

	}

	@Override
	public JobOffer get(int id) {
		return em.find(JobOffer.class, id);
	}

	@Override
	public void remove(JobOffer jobOffer) {
		jobOffer=get(jobOffer.getId());
		em.remove(jobOffer);
		
	}

	@Override
	public void update(JobOffer jobOffer) {
		//jobOffer=get(jobOffer.getId());
		em.merge(jobOffer);
		
	}

	
	@Override

	public List<JobOffer> getByAgent(User agent){
		try{
			return em.createQuery("SELECT jo FROM JobOffer jo WHERE jo.agent = :agent", JobOffer.class)
					.setParameter("agent", agent).getResultList();
			}catch (NoResultException e){
				return null;
			}
		
	}

	@Override
	public List<User> getAppliedUsers(JobOffer jobOffer) {
		try{
			return em.createQuery("SELECT ja.client FROM JobOffer jo , JobApply ja WHERE jo.id = ja.offer.id and ja.offer = :offer", User.class).setParameter("offer", jobOffer).getResultList();
			}catch (NoResultException e){
				return null;
			}
	}

	@Override
	public List<User> getAcceptedUsers(JobOffer jobOffer) {
		try{
			return em.createQuery("SELECT ja.client FROM JobOffer jo , JobApply ja WHERE jo.id = ja.offer.id and ja.isAccepted =  true and ja.offer = :offer", User.class).setParameter("offer", jobOffer).getResultList();
			}catch (NoResultException e){
				return null;
			}
	}

	
}
