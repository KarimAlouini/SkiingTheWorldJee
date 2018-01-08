package tn.codeinc.services;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.JobOffer;
import tn.codeinc.persistance.JobOfferCategory;
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
		
//		 File file = new File(jobOffer.getImage().toString());
//	        byte[] bFile = new byte[(int) file.length()];
//	 
//	        try {
//	            FileInputStream fileInputStream = new FileInputStream(file);
//	            fileInputStream.read(bFile);
//	            fileInputStream.close();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        JobOffer j = new JobOffer();
//	        j=jobOffer;
//	        j.setName(jobOffer.getName());	        
//	        j.setImage(jobOffer.getImage());
	        
		em.merge(jobOffer);
		
	}

	@Override
	public List<JobOffer> getAll() {
	
	     return em.createQuery("SELECT jo FROM JobOffer jo",JobOffer.class).getResultList();

	}

	@Override
	public JobOffer get(int id) throws ElementNotFoundException {
		JobOffer jo = em.find(JobOffer.class, id);
		if (jo == null)
			throw new ElementNotFoundException("Job Offer Not found");
		else
			return jo;
	}
	

	@Override
	public void remove(JobOffer jobOffer) throws ElementNotFoundException {
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

	@Override
	public List<JobOffer> getNewestJobOffers() {
		try{
			return em.createQuery("SELECT jo FROM JobOffer jo "				
					+ " ORDER BY jo.creationDate DESC", JobOffer.class)
					.setMaxResults(6).getResultList();
			}catch (NoResultException e){
				return null;
			}
		
		
	}

	@Override
	public List<JobOffer> getJobOffersBycategory(String Category) {
		try{
			return em.createQuery("SELECT jo FROM JobOffer jo WHERE jo.jobOfferCategory ='" + JobOfferCategory.valueOf(Category).name() +"' ORDER BY jo.creationDate DESC", JobOffer.class).setMaxResults(4).getResultList();
			}catch (NoResultException e){
				return null;
			}
	}

	
}
