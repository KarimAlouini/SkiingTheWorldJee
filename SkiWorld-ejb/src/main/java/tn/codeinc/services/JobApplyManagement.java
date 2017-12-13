package tn.codeinc.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.exceptions.JobApplicationDuplicationException;
import tn.codeinc.persistance.JobApply;
import tn.codeinc.persistance.JobApplyId;
import tn.codeinc.persistance.JobOffer;
import tn.codeinc.persistance.User;

@Stateless
public class JobApplyManagement implements JobApplyManagemenLocal, JobApplyManagemenRemote {

	@PersistenceContext
	EntityManager em;
	@Inject
	CurrentUserLocal currentUser;

	@Inject
	JobOfferManagementLocal jobOfferManagement;

	@Override
	public void create(JobApply jobApply) throws JobApplicationDuplicationException, ElementNotFoundException {

		JobOffer jo = jobOfferManagement.get(jobApply.getOffer().getId());

		if (jo == null)
			throw new ElementNotFoundException("Offer doesn't exist");

		JobApply j = get(jobApply.getId());
		if (j != null)
			throw new JobApplicationDuplicationException("You have already applied for this offer");
		em.persist(jobApply);
	}

	@Override
	public List<JobApply> getAll() {
		return em.createQuery("SELECT ja FROM JobApply ja", JobApply.class).getResultList();
	}

	@Override
	public JobApply get(JobApplyId id) {
		return em.find(JobApply.class, id);

	}

	@Override
	public void remove(JobApply jobApply) throws ElementNotFoundException, AuthorizationException {
		JobApply ja = get(jobApply.getId());
		if (ja == null)
			throw new ElementNotFoundException("Job offer doesn't exist");
		if (!ja.getClient().equals(currentUser.get()))
			throw new AuthorizationException();

		if (!em.contains(ja))
			em.merge(ja);
		
		em.remove(ja);
	}

	@Override
	public void update(JobApply jobApply) throws ElementNotFoundException, AuthorizationException{
		JobApply ja = get(jobApply.getId());
		if (ja == null){
			System.out.println("JobApplyManagement.update() here");
			throw new ElementNotFoundException("Job offer doesn't exist");
		}
		
		if (!ja.getClient().equals(currentUser.get()))
			throw new AuthorizationException();
        em.merge(jobApply);

	}

	@Override
	public JobApply getByDate(Date jobApplyDate) {
		try {
			return em.createQuery("SELECT ja FROM JobApply ja WHERE mo.jobApplyDate = :jobApplyDate", JobApply.class)
					.setParameter("jobApplyDate", jobApplyDate).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	@Override

	public List<JobApply> getByAgent(User agent){
		try{
			return em.createQuery("SELECT jo FROM JobApply jo WHERE jo.offer.agent = :agent", JobApply.class)
					.setParameter("agent", agent).getResultList();
			}catch (NoResultException e){
				return null;
			}
		
	}
}